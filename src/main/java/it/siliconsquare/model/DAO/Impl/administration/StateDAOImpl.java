package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.StateDAO;
import it.siliconsquare.model.administration.State;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class StateDAOImpl implements StateDAO {
    @Autowired
    private List<State> statesList;
    private String sql = "SELECT * FROM public.state";

    public StateDAOImpl() {
        statesList = getStates();
    }

    private List<State> getStates() {
        return Q2ObjList.fromSelect(State.class, sql);
    }

    @Override
    public List<State> getAllStates() {
        return statesList;
    }

    @Override
    public State getStateByName(String name) {
        return Q2Obj.fromSelect(State.class, sql + " WHERE name = ?", name);
    }

    @Override
    public State getStateById(int id) {
        return Q2Obj.fromSelect(State.class, sql + " WHERE id_state = ?", id);
    }

}
