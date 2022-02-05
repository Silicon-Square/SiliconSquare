package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.Motherboard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MotherboardDAOImpl implements ComponentDAO<Motherboard> {

    @Autowired
    private List<Motherboard> motherboardList;

    private static final String tablename = "motherboard";
    private static final String pk = "id_motherboard";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public MotherboardDAOImpl() {
        motherboardList = new ArrayList<>();
    }

    @Override
    public List<Motherboard> getAll() {
        return Q2ObjList.fromSelect(Motherboard.class, sql);
    }

    @Override
    public List<Motherboard> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(Motherboard.class, sql + clause);
    }

    @Override
    public List<Motherboard> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(Motherboard.class, sql + clause);
    }

    @Override
    public Motherboard getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(Motherboard.class, sql + clause, id);
    }

    @Override
    public Motherboard getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(Motherboard.class, sql + clause, id);
    }

    @Override
    public boolean save(Motherboard c) {
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        Motherboard c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(Motherboard c) {
        Motherboard newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(Motherboard c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public List<Motherboard> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(Motherboard.class, sql_not_visible + clause);
    }
}
