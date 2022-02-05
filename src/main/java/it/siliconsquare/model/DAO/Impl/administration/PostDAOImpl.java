package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.administration.PostDAO;
import it.siliconsquare.model.administration.Post;
import it.siliconsquare.model.administration.User;
import it.siliconsquare.model.configuration.Configuration;
import it.siliconsquare.provider.Database;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PostDAOImpl implements PostDAO {
    @Autowired
    private List<Post> postList;
    private String sql = "SELECT * FROM post, public.user WHERE public.user.id_user = post.id_user ";
    private String progressiveOrder = " ORDER BY id_post";

    private String joinSql = "SELECT post.*, u.* FROM public.post post, public.user u WHERE u.id_user  = post.id_user ";

    public PostDAOImpl() {
    }

    @Override
    public List<Post> getAllPost() {

        Connection con = Database.getInstance().getConnection();
        String query = joinSql + "ORDER BY post.id_post";

        List<Post> postsList = new ArrayList<>();
        try {
            PreparedStatement p = con.prepareStatement(query);
            ResultSet rs = p.executeQuery();

            postsList = loadPostList(rs);

            rs.close();
            p.close();
            con.close();
            
        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get all post");
        }

        return postsList;
    }

    @Override
    public List<Post> getOtherUsersPosts(User u) {
        String query = "SELECT post.*, u.* FROM public.post post, public.user u WHERE u.id_user = post.id_user AND u.id_user != ? ORDER BY id_post DESC LIMIT 2";
        List<Post> postsList = new ArrayList<>();

        Connection con = Database.getInstance().getConnection();
        PreparedStatement p;
        ResultSet rs;
        try {
            p = con.prepareStatement(query);
            p.setInt(1, u.getIdUser());
            rs = p.executeQuery();
            
            postsList = loadPostList(rs, u.getIdUser());

            rs.close();
            p.close();
            con.close();

        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get all other user post [" + u.getIdUser() + "]");
        }

        return postsList;
    }

    @Override
    public List<Post> getOtherUsersPosts(User u, int actualPost) {
        String query = "SELECT post.*, u.* FROM public.post post, public.user u WHERE u.id_user  = post.id_user AND u.id_user != ? AND post.id_post < ? ORDER BY id_post DESC LIMIT 2";
        List<Post> postsList = new ArrayList<>();

        Connection con = Database.getInstance().getConnection();
        PreparedStatement p;
        ResultSet rs;
        try {
            p = con.prepareStatement(query);
            p.setInt(1, u.getIdUser());
            p.setInt(2, actualPost);
            rs = p.executeQuery();
            
            postsList = loadPostList(rs, u.getIdUser());

            rs.close();
            p.close();
            con.close();

        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get all other user post [" + u.getIdUser() + "]");
        }

        return postsList;
    }

    @Override
    public List<Post> getAllUserPosts(int user) {
        String query = "SELECT post.*, u.* FROM public.post post, public.user u WHERE u.id_user = post.id_user AND u.id_user = ? ORDER BY id_post DESC LIMIT 2";
        List<Post> postsList = new ArrayList<>();

        Connection con = Database.getInstance().getConnection();
        PreparedStatement p;
        ResultSet rs;
        try {
            p = con.prepareStatement(query);
            p.setInt(1, user);
            rs = p.executeQuery();
            
            postsList = loadPostList(rs, user);

            rs.close();
            p.close();
            con.close();

        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get all post user [" + user + "]");
        }

        return postsList;
    }

    @Override
    public List<Post> getAllUserPosts(int user, int actualPost) {
        String query = "SELECT post.*, u.* FROM public.post post, public.user u WHERE u.id_user  = post.id_user AND u.id_user = ? AND post.id_post < ? ORDER BY id_post DESC LIMIT 2";
        List<Post> postsList = new ArrayList<>();

        Connection con = Database.getInstance().getConnection();
        PreparedStatement p;
        ResultSet rs;
        try {
            p = con.prepareStatement(query);
            p.setInt(1, user);
            p.setInt(2, actualPost);
            rs = p.executeQuery();
            
            postsList = loadPostList(rs, user);

            rs.close();
            p.close();
            con.close();

        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get all post user [" + user + "]");
        }

        return postsList;
    }

    @Override
    public Post getPostById(int id) {
        String query = joinSql + " AND post.id_post = ? ";
        List<Post> postsList = new ArrayList<>();

        Connection con = Database.getInstance().getConnection();
        PreparedStatement p;
        ResultSet rs;
        try {
            p = con.prepareStatement(query);
            p.setInt(1, id);
            rs = p.executeQuery();
            
            postsList = loadPostList(rs);

            rs.close();
            p.close();
            con.close();

            if(postsList.size() > 0)
                return postsList.get(0);

        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get post [" + id + "]");
        }

        return null;
    }

    @Override
    public Post getLastPostOfUser(User user) {
        String query = joinSql + " AND post.id_user = ? " + "ORDER BY post.id_post DESC LIMIT 1;";
        List<Post> postsList = new ArrayList<>();

        Connection con = Database.getInstance().getConnection();
        PreparedStatement p;
        ResultSet rs;
        try {
            p = con.prepareStatement(query);
            p.setInt(1, user.getIdUser());
            rs = p.executeQuery();
            
            postsList = loadPostList(rs);

            rs.close();
            p.close();
            con.close();

        } catch (SQLException e) {
            Logger.getInstance().captureException(e, "Couldn't get last post of user [" + user.getIdUser() + "]");
        }

        if(postsList.size() > 0)
            return postsList.get(0);

        return null;
    }

    @Override
    public boolean savePost(Post post) {
        post = Q2Obj.insert(post);

        return post != null && post.getIdPost() != 0;
    }

    @Override
    public boolean publishPost(Post post) {
        String query = "INSERT INTO public.post ( id_user ,  post_title ,  post_description, poll_option_one, poll_option_two, post_picture) VALUES(?,?,?,?,?,?);";

        int affectedRows = Q2Sql.executeUpdate(query, post.getIdUser(), post.getPostTitle(), post.getPostDescription(),
                post.getPollOptionOne(), post.getPollOptionTwo(), post.getPostPicture());

        String logMessage = "New post [Id user, post_title] - " + post.getIdUser() + "," + post.getPostTitle();

        if (affectedRows == 0)
            logMessage = "Attempt to publish post [Id user, post_title] - " + post.getIdUser() + ","
                    + post.getPostTitle();

        Logger.getInstance().captureMessage(logMessage);

        if (affectedRows > 0) {
            return true;
        }

        return false;
    }

    @Override
    public boolean deletePost(Post post) {
        return (Q2Obj.delete(post) > 0);
    }

    @Override
    public boolean updatePost(Post post) {
        return Q2Obj.update(post) == null;
    }

    @Override
    public boolean updateExistingPost(Post p) {
        String sqlModifyPost = "UPDATE post SET post_title = ?, post_description = ?, poll_option_one = ?, poll_option_two = ?, poll_option_one_choices = 0, poll_option_two_choices = 0, post_picture = ? WHERE post.id_post = ?;";
        int affectedRows = Q2Sql.executeUpdate(sqlModifyPost, p.getPostTitle(), p.getPostDescription(),
                p.getPollOptionOne(), p.getPollOptionTwo(), p.getPostPicture(), p.getIdPost());

        String logMessage = "Post modification [Id post, post_title] - " + p.getIdPost() + "," + p.getPostTitle();

        if (affectedRows == 0)
            logMessage = "Attempt to update post [Id post, post_title] - " + p.getIdPost() + "," + p.getPostTitle();

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    private List<Post> loadPostList(ResultSet rs) throws SQLException{
        return loadPostList(rs, -1);
    }

    private List<Post> loadPostList(ResultSet rs, int interested_user) throws SQLException{
        List<Post> postsList = new ArrayList<>();

        while (rs.next()) {
            int idPost = rs.getInt("id_post");
            int idUser = rs.getInt("id_user");
            String postTitle = rs.getString("post_title");
            String postDescription = rs.getString("post_description");
            String pollOptionOne = rs.getString("poll_option_one");
            String pollOptionTwo = rs.getString("poll_option_two");
            int pollOptionOneChoice = rs.getInt("poll_option_one_choices");
            int pollOptionTwoChoice = rs.getInt("poll_option_two_choices");
            String postPicture = rs.getString("post_picture");
            int postConfiguration = rs.getInt("post_configuration");
            int likeToPostCount = rs.getInt("like_count");
            String name = rs.getString("name");
            String surname = rs.getString("surname");
            String email = rs.getString("email");
            String username = rs.getString("username");
            String avatar = rs.getString("avatar");
            int experience = rs.getInt("experience");

            User user = new User();
            user.setIdUser(idUser);
            user.setName(name);
            user.setSurname(surname);
            user.setEmail(email);
            user.setUsername(username);
            user.setAvatar(avatar);
            user.setExperience(experience);

            Configuration configuration = new Configuration();
            configuration.setIdConfiguration(postConfiguration);

            Post post = new Post(idPost, postTitle, postDescription, pollOptionOne, pollOptionTwo,
                    pollOptionOneChoice,
                    pollOptionTwoChoice, postPicture, configuration, idUser, user, likeToPostCount);
            fetchMissingParameters(post, interested_user);
            postsList.add(post);
        }

        return postsList;
    }

    public void fetchMissingParameters(Post post, int id_user) {
        post.setLike(Database.getInstance().getLikeDAO().getExistingLike(id_user, post.getIdPost()));
        post.setChoice(Database.getInstance().getPollChoiceDAO().getExistingChoice(id_user, post.getIdPost()));
    }
}
