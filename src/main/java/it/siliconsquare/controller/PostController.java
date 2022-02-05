package it.siliconsquare.controller;

import com.google.gson.Gson;
import it.siliconsquare.common.redirect.ResourcePath;
import it.siliconsquare.connection.ftp.FtpManager;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.administration.Like;
import it.siliconsquare.model.administration.PollChoice;
import it.siliconsquare.model.administration.Post;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.provider.Database;
import net.bytebuddy.asm.Advice.Return;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.sentry.ITransaction;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@RestController
public class PostController {

    @RequestMapping(value = "/publishPost", method = RequestMethod.POST)
    public static boolean publishPost(@RequestParam("picture") MultipartFile picture, HttpServletRequest req,
            HttpServletResponse resp) {

        boolean success = false;
        Post newPost = new Post();
        int user = Integer.parseInt(req.getParameter("user"));

        if (user != 0) {

            newPost.setIdUser(user);
            newPost.setPostTitle(req.getParameter("new-post-title"));
            newPost.setPostDescription(req.getParameter("new-post-description"));

            newPost.setPollOptionOne(req.getParameter("poll-opt1"));
            newPost.setPollOptionTwo(req.getParameter("poll-opt2"));

            newPost.setPostPicture(uploadPostPicture(picture));

            success = Database.getInstance().getPostDAO().publishPost(newPost);
        }
        return success;
    }

    @GetMapping("/loadUserPosts/{id_user}")
    public static String loadUserPosts(@PathVariable int id_user, HttpServletRequest req, HttpServletResponse resp) {

        if (((User) req.getSession().getAttribute("user")).getIdUser() != id_user)
            return null;

        List<Post> userPosts = Database.getInstance().getPostDAO().getAllUserPosts(id_user);

        String jsonUserPosts = new Gson().toJson(userPosts);

        return jsonUserPosts;

    }

    @GetMapping("/nextUserPosts/{id_user}/{actualPost}")
    public static String nextUserPosts(@PathVariable int id_user, @PathVariable int actualPost, HttpServletRequest req, HttpServletResponse resp) {

        if (((User) req.getSession().getAttribute("user")).getIdUser() != id_user)
            return null;

        List<Post> userPosts = Database.getInstance().getPostDAO().getAllUserPosts(id_user, actualPost);

        String jsonUserPosts = new Gson().toJson(userPosts);

        return jsonUserPosts;

    }


    @GetMapping("/otherUsersPosts")
    public static String otherUsersPosts(HttpServletRequest req, HttpServletResponse resp) {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null)
            return null;

        List<Post> posts = Database.getInstance().getPostDAO().getOtherUsersPosts(u);

        String jsonPosts = new Gson().toJson(posts);

        return jsonPosts;
    }
    @GetMapping("/nextOtherUsersPosts/{actualPost}")
    public static String nextOtherUsersPosts(@PathVariable int actualPost, HttpServletRequest req, HttpServletResponse resp) {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null)
            return null;

        List<Post> posts = Database.getInstance().getPostDAO().getOtherUsersPosts(u, actualPost);

        String jsonPosts = new Gson().toJson(posts);

        return jsonPosts;
    }

    @GetMapping("/loadUserPost/{id_post}")
    public static String loadUserPost(@PathVariable int id_post, HttpServletRequest req, HttpServletResponse resp) {

        Post p = checkMyPost(id_post, req);
        if (p == null)
            return null;

        String jsonUserPost = new Gson().toJson(p);
        return jsonUserPost;
    }

    @PostMapping("/modifyPost/{id_post}")
    public static boolean modifyPost(@RequestParam("picture") MultipartFile picture, @PathVariable int id_post,
            HttpServletRequest req, HttpServletResponse resp) {
        Post p = checkMyPost(id_post, req);
        if (p == null)
            return false;

        String newTitle = req.getParameter("new-post-title");
        if (p.getPostTitle() != newTitle)
            p.setPostTitle(newTitle);

        String newDescription = req.getParameter("new-post-description");
        if (p.getPostDescription() != newDescription)
            p.setPostDescription(newDescription);

        String poll1 = req.getParameter("poll-opt1");
        String poll2 = req.getParameter("poll-opt2");
        if (p.getPollOptionOne() != poll1)
            p.setPollOptionOne(poll1);
        if (p.getPollOptionTwo() != poll2)
            p.setPollOptionTwo(poll2);

        String newPicture = req.getParameter("picture-to-edit");
        if (newPicture == null || newPicture.equals("")) {
            newPicture = uploadPostPicture(picture);
            p.setPostPicture(newPicture);

        }
        return Database.getInstance().getPostDAO().updateExistingPost(p);
    }

    @GetMapping("/deletePost/{id_post}")
    public static boolean deletePost(@PathVariable int id_post, HttpServletRequest req, HttpServletResponse resp) {

        Post p = checkMyPost(id_post, req);
        if (p == null)
            return false;

        FtpManager ftp = new FtpManager();
        String picturePath = p.getPostPicture();

        if (picturePath != null && picturePath != "")
            ftp.deleteFile(picturePath);

        return Database.getInstance().getPostDAO().deletePost(p);
    }

    @PostMapping("/toggleLike/{id_post}")
    public static boolean toggleLike (@PathVariable int id_post, HttpServletRequest req, HttpServletResponse resp) {
        User u = (User)req.getSession().getAttribute("user");
        if(u == null)
            return false;
        
        Like newLike = new Like();
        newLike.setUser(u);
        newLike.setPost(id_post);

        ITransaction transaction = Logger.getInstance().startTransaction("methodlike", "hitting like");

        transaction.finish();
        Logger.getInstance().closeTransaction(transaction);
        
        return Database.getInstance().getLikeDAO().hitLike(newLike);
    }

    @PostMapping("/chooseToPoll/{id_post}")
    public static boolean chooseToPoll(@PathVariable int id_post, HttpServletRequest req, HttpServletResponse resp) {
        User u = (User)req.getSession().getAttribute("user");
        if(u == null)
            return false;

        Post p = Database.getInstance().getPostDAO().getPostById(id_post);
        if(p == null)
            return false;
        
        PollChoice newChoice = new PollChoice();
        newChoice.setUser(u);
        newChoice.setPost(p);

        String choice = req.getParameter("choice");
        
        if(Integer.valueOf(choice) == PollChoice.OPTION1)
            newChoice.setChoice(PollChoice.OPTION1);
        else
            newChoice.setChoice(PollChoice.OPTION2);


        if(Database.getInstance().getPollChoiceDAO().getExistingChoice(u.getIdUser(), id_post) != PollChoice.NOCHOICE)
            return false;
        
        Database.getInstance().getPollChoiceDAO().choice(newChoice);
        return true;
    }

    private static Post checkMyPost(int id_post, HttpServletRequest req) {
        User u = (User) req.getSession().getAttribute("user");
        if (u == null)
            return null;

        Post post = Database.getInstance().getPostDAO().getPostById(id_post);
        if (post.getIdUser() != u.getIdUser())
            return null;

        return post;
    }

    private static String uploadPostPicture(MultipartFile picture) {
        if (picture == null || picture.equals(""))
            return null;

        FtpManager ftp = new FtpManager();
        String path = ftp.uploadFile(ResourcePath.USER_POST, picture);
        if (path == null || path.equals(""))
            return null;

        return path;
    }

}
