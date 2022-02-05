package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.ExternalStorage;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ExternalStorageDAOImpl implements ComponentDAO<ExternalStorage> {

    @Autowired
    private List<ExternalStorage> externalStorageList;

    private static final String tablename = "external_storage";
    private static final String pk = "id_external_storage";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public ExternalStorageDAOImpl() {
        externalStorageList = new ArrayList<>();
    }

    @Override
    public List<ExternalStorage> getAll() {
        return Q2ObjList.fromSelect(ExternalStorage.class, sql);
    }

    @Override
    public List<ExternalStorage> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(ExternalStorage.class, sql + clause);
    }

    @Override
    public List<ExternalStorage> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(ExternalStorage.class, sql + clause);
    }

    @Override
    public ExternalStorage getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(ExternalStorage.class, sql + clause, id);
    }

    @Override
    public ExternalStorage getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(ExternalStorage.class, sql + clause, id);
    }

    @Override
    public boolean save(ExternalStorage externalStorage) {
        externalStorage = Q2Obj.insert(externalStorage);
        int affectedRows = setState(externalStorage, externalStorage.getState().getId());
        return externalStorage != null && externalStorage.getId() != 0 && affectedRows > 0;
    }

    @Override
    public int setState(ExternalStorage c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id,
                c.getId());
    }

    @Override
    public boolean delete(int id) {
        ExternalStorage c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(ExternalStorage c) {
        ExternalStorage newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public List<ExternalStorage> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(ExternalStorage.class, sql_not_visible + clause);
    }
}
