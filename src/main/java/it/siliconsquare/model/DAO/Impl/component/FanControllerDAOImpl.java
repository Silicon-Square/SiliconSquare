package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.FanController;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FanControllerDAOImpl implements ComponentDAO<FanController> {
    @Autowired
    private List<FanController> fanControllerList;
    private static final String tablename = "fan_controller";
    private static final String pk = "id_fan_controller";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public FanControllerDAOImpl() {
        fanControllerList = new ArrayList<>();
    }

    @Override
    public List<FanController> getAll() {
        return Q2ObjList.fromSelect(FanController.class, sql);
    }

    @Override
    public List<FanController> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(FanController.class, sql + clause);
    }

    @Override
    public List<FanController> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(FanController.class, sql + clause);
    }

    @Override
    public FanController getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(FanController.class, sql + clause, id);
    }

    @Override
    public FanController getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(FanController.class, sql + clause, id);
    }

    @Override
    public boolean save(FanController fanController) {
        fanController = Q2Obj.insert(fanController);
        int affectedRows = setState(fanController, fanController.getState().getId());
        return fanController != null && fanController.getId() != 0 && affectedRows > 0;
    }

    @Override
    public int setState(FanController c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id,
                c.getId());
    }

    @Override
    public boolean delete(int id) {
        FanController c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(FanController c) {
        FanController newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public List<FanController> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(FanController.class, sql_not_visible + clause);
    }
}
