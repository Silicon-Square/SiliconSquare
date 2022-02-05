package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.State;

import java.util.List;

public interface StateDAO {
    List<State> getAllStates();

    State getStateByName(String state);

    State getStateById(int id);

}
