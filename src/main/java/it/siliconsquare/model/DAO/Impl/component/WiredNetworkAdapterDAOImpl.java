package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.WiredNetworkAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class WiredNetworkAdapterDAOImpl implements ComponentDAO<WiredNetworkAdapter> {

    @Autowired
    private List<WiredNetworkAdapter> wiredNetworkAdapterList;

    private static final String tableName = "wired_network_adapter";
    private static final String pk = "id_wired_network_adapter";

    private static String SELECT = "SELECT *";
    private static String VISIBLE = " AND is_visible = true ";
    private static String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tableName + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tableName + ", state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tableName + ", public.state " + WHERE + NOT_VISIBLE;

    @Override
    public List<WiredNetworkAdapter> getAll() {
        return Q2ObjList.fromSelect(WiredNetworkAdapter.class, sql);
    }

    @Override
    public WiredNetworkAdapter getById(int id) {
        String clause = "AND " + pk + " = ?";
        return Q2Obj.fromSelect(WiredNetworkAdapter.class, sql + clause, id);
    }

    @Override
    public WiredNetworkAdapter getPublishedById(int id) {
        String clause = "AND " + pk + " = ? AND state.id_state = 1";
        return Q2Obj.fromSelect(WiredNetworkAdapter.class, sql + clause, id);
    }

    @Override
    public List<WiredNetworkAdapter> getAllPublished() {
        return Q2ObjList.fromSelect(WiredNetworkAdapter.class, sql + " AND state.id_state = 1");
    }

    @Override
    public List<WiredNetworkAdapter> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(WiredNetworkAdapter.class, sql + clause);
    }

    @Override
    public boolean save(WiredNetworkAdapter c) {
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        WiredNetworkAdapter c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(WiredNetworkAdapter c) {
        WiredNetworkAdapter newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(WiredNetworkAdapter c, int id) {
        return Q2Sql.executeUpdate(
                "UPDATE " + tableName + " SET id_state = ? WHERE " + pk + " = ?", id,
                c.getId());
    }

    @Override
    public List<WiredNetworkAdapter> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(WiredNetworkAdapter.class, sql_not_visible + clause);
    }

}
