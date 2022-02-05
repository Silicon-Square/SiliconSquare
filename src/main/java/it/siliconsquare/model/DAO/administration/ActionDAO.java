package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Action;

import java.util.List;

public interface ActionDAO {
    public List<Action> getAllAction();

    Action getActionById(int id);

    void saveAction(Action action);

    void deleteAction(Action action);

    void updateAction(Action action);
}
