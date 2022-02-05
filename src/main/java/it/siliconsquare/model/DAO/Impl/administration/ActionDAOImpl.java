package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.ActionDAO;
import it.siliconsquare.model.administration.Action;
import it.siliconsquare.model.configuration.Configuration;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ActionDAOImpl implements ActionDAO {
    @Autowired
    private List<Configuration> configurationList;
    private String sql = "SELECT * FROM action ";

    public ActionDAOImpl() {
    }

    @Override
    public List<Action> getAllAction() {
        return Q2ObjList.fromSelect(Action.class, sql);
    }

    @Override
    public Action getActionById(int id) {
        return Q2Obj.fromSelect(Action.class, sql + " WHERE id_action = ?", id);
    }

    @Override
    public void saveAction(Action action) {
        Q2Obj.insert(action);
    }

    @Override
    public void deleteAction(Action action) {
        Q2Obj.delete(action);
    }

    @Override
    public void updateAction(Action action) {
        Q2Obj.update(action);
    }
}
