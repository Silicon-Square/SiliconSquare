package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.Fan;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class FanDAOImpl implements ComponentDAO<Fan> {
    @Autowired
    private List<Fan> fanList;

    private static final String tablename = "fan";
    private static final String pk = "id_fan";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public FanDAOImpl() {
        fanList = new ArrayList<>();
    }

    @Override
    public List<Fan> getAll() {
        return Q2ObjList.fromSelect(Fan.class, sql);
    }

    @Override
    public List<Fan> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(Fan.class, sql + clause);
    }

    @Override
    public List<Fan> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(Fan.class, sql + clause);
    }

    @Override
    public Fan getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(Fan.class, sql + clause, id);
    }

    @Override
    public Fan getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(Fan.class, sql + clause, id);
    }

    @Override
    public boolean save(Fan fan) {
        fan = Q2Obj.insert(fan);
        int affectedRows = setState(fan, fan.getState().getId());
        return fan != null && fan.getId() != 0 && affectedRows > 0;
    }

    @Override
    public int setState(Fan c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public boolean delete(int id) {
        Fan c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(Fan c) {
        Fan newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public List<Fan> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(Fan.class, sql_not_visible + clause);
    }

}
