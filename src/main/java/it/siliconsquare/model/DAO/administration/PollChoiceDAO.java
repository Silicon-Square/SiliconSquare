package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.PollChoice;
import it.siliconsquare.model.administration.Post;

public interface PollChoiceDAO {

    int getExistingChoice(int id_user, int id_post);

    boolean choice(PollChoice choice);

    int choicesToPost(Post post, int choice);
} 