package it.siliconsquare.model.DAO.Impl.administration;

import java.util.List;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2Sql;

import org.springframework.beans.factory.annotation.Autowired;

import it.siliconsquare.logger.Logger;
import it.siliconsquare.model.DAO.administration.PollChoiceDAO;
import it.siliconsquare.model.administration.PollChoice;
import it.siliconsquare.model.administration.Post;

public class PollChoiceDAOimpl implements PollChoiceDAO {
    @Autowired

    private List<PollChoice> choiceList;
    private String sql = "SELECT * FROM poll_choice, public.user WHERE poll_choice.id_user = public.user.id_user ";

    @Override
    public int getExistingChoice(int id_user, int id_post) {
        PollChoice c = Q2Obj.fromSelect(PollChoice.class,
                sql + "AND poll_choice.id_user = ? AND poll_choice.id_post = ?;", id_user, id_post);

        if (c != null)
            return c.getChoice();

        return -1;
    }

    @Override
    public boolean choice(PollChoice choice) {
        String query = "INSERT INTO poll_choice (id_post, id_user, choice) VALUES(?,?,?);";

        int affectedRows = Q2Sql.executeUpdate(query, choice.getIdPost(), choice.getIdUser(), choice.getChoice());

        String logMessage = "Somebody has choose to a poll [id post, id user, choice n.] - " + choice.getIdPost() + ","
                + choice.getIdUser() + "," + choice.getChoice();

        if (affectedRows == 0)
            logMessage = "Attempt to choose to a poll [id post, id user, choice n.] - " + choice.getIdPost() + ","
                    + choice.getIdUser() + "," + choice.getChoice();

        Logger.getInstance().captureMessage(logMessage);

        if (affectedRows > 0) {
            updatePollCount(choice.getIdPost());
            return true;
        }

        return false;
    }

    public boolean updatePollCount(int id_post){
        String sqlModifyPost = "UPDATE public.post as p1 SET " +
                                "poll_option_one_choices = (SELECT COALESCE(count(*), 0) FROM poll_choice WHERE choice = 1 and id_post = ?)," +
                                "poll_option_two_choices = (SELECT COALESCE(count(*), 0) FROM poll_choice WHERE choice = 2 and id_post = ?)" + 
                                "WHERE p1.id_post = ?;";

        int affectedRows = Q2Sql.executeUpdate(sqlModifyPost, id_post, id_post, id_post);

        String logMessage = "Poll recalculate [Id post] - " + id_post;

        if (affectedRows == 0)
            logMessage = "Attempt to recalculate poll [Id post] - " + id_post;

        Logger.getInstance().captureMessage(logMessage);

        return affectedRows > 0;
    }

    @Override
    public int choicesToPost(Post post, int choice) {
        String query = "SELECT count(*) FROM poll_choice WHERE poll_choice.id_post = ? AND poll_choice.choice = ?;";

        if (post == null)
            return 0;

        return Q2Sql.numberFromSql(query, post.getIdPost(), choice).intValue();
    }

}
