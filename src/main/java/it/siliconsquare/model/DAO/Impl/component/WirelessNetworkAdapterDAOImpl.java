package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.WirelessNetworkAdapter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class WirelessNetworkAdapterDAOImpl implements ComponentDAO<WirelessNetworkAdapter> {
    @Autowired
    private List<WirelessNetworkAdapter> wirelessNetworkAdapterList;

    private static final String tableName = "wireless_network_adapter";
    private static final String pk = "id_wireless_network_adapter";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tableName + ".id_state=state.id_state";

    private static String sql = SELECT + " FROM " + tableName + ", state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tableName + ", public.state " + WHERE + NOT_VISIBLE;

    public WirelessNetworkAdapterDAOImpl() {
        wirelessNetworkAdapterList = new ArrayList<>();
    }

    public List<WirelessNetworkAdapter> getAll() {
        return Q2ObjList.fromSelect(WirelessNetworkAdapter.class, sql);
    }

    @Override
    public List<WirelessNetworkAdapter> getAllPublished() {
        String clause = "AND state.id_state = 1";
        return Q2ObjList.fromSelect(WirelessNetworkAdapter.class, sql + clause);
    }

    @Override
    public List<WirelessNetworkAdapter> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(WirelessNetworkAdapter.class, sql + clause);
    }

    @Override
    public WirelessNetworkAdapter getPublishedById(int id) {
        String clause = "AND " + pk + " = ? AND state.id_state = 1";
        return Q2Obj.fromSelect(WirelessNetworkAdapter.class, sql + clause, id);
    }

    @Override
    public WirelessNetworkAdapter getById(int id) {
        String clause = "AND " + pk + " = ?";
        return Q2Obj.fromSelect(WirelessNetworkAdapter.class, sql + clause, id);
    }

    @Override
    public boolean save(WirelessNetworkAdapter w) {
        w = Q2Obj.insert(w);
        int affectedRows = setState(w, w.getState().getId());
        return w != null && w.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        WirelessNetworkAdapter c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(WirelessNetworkAdapter c) {
        WirelessNetworkAdapter newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(WirelessNetworkAdapter c, int id) {
        return Q2Sql.executeUpdate(
                "UPDATE " + tableName + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public List<WirelessNetworkAdapter> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(WirelessNetworkAdapter.class, sql_not_visible + clause);
    }

}