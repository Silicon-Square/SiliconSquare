package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.Memory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class MemoryDAOImpl implements ComponentDAO<Memory> {

    @Autowired
    private List<Memory> memoryList;

    private static final String tablename = "memory";
    private static final String pk = "id_memory";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public MemoryDAOImpl() {
        memoryList = new ArrayList<>();
    }

    @Override
    public List<Memory> getAll() {
        return Q2ObjList.fromSelect(Memory.class, sql);
    }

    @Override
    public List<Memory> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(Memory.class, sql + clause);
    }

    @Override
    public List<Memory> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(Memory.class, sql + clause);
    }

    @Override
    public Memory getById(int id) {
        String clause = " AND " + pk + " = ?";
        Memory mem = Q2Obj.fromSelect(Memory.class, sql + clause, id);
        System.out.println("MEMORY DB: " + mem);
        return mem;
    }

    @Override
    public Memory getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(Memory.class, sql + clause, id);
    }

    @Override
    public boolean save(Memory c) {
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        Memory c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(Memory c) {
        Memory newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(Memory c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public List<Memory> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(Memory.class, sql_not_visible + clause);
    }
}
