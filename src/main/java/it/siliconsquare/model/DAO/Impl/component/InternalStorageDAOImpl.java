package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.InternalStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class InternalStorageDAOImpl implements ComponentDAO<InternalStorage> {
    @Autowired
    private List<InternalStorage> internalStorageList;

    private static final String tablename = "internal_storage";
    private static final String pk = "id_internal_storage";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public InternalStorageDAOImpl() {
        internalStorageList = new ArrayList<>();
    }

    @Override
    public List<InternalStorage> getAll() {
        return Q2ObjList.fromSelect(InternalStorage.class, sql);
    }

    @Override
    public List<InternalStorage> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(InternalStorage.class, sql + clause);
    }

    @Override
    public List<InternalStorage> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(InternalStorage.class, sql + clause);
    }

    @Override
    public InternalStorage getById(int id) {
        String clause = " AND " + pk + "= ?";
        return Q2Obj.fromSelect(InternalStorage.class, sql + clause, id);
    }

    @Override
    public InternalStorage getPublishedById(int id) {
        return Q2Obj.fromSelect(InternalStorage.class, sql + " AND state.id_state = 1 AND " + pk + " = ?", id);
    }

    @Override
    public boolean save(InternalStorage internalStorage) {
        internalStorage = Q2Obj.insert(internalStorage);
        int affectedRows = setState(internalStorage, internalStorage.getState().getId());
        return internalStorage != null && internalStorage.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        InternalStorage c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();

    }

    @Override
    public boolean update(InternalStorage c) {
        InternalStorage newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;

    }

    @Override
    public int setState(InternalStorage c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id,
                c.getId());
    }

    @Override
    public List<InternalStorage> getFields() {

        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(InternalStorage.class, sql_not_visible + clause);
    }
}
