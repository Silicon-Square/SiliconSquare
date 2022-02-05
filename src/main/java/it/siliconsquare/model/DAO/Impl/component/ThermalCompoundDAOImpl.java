package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.ThermalCompound;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ThermalCompoundDAOImpl implements ComponentDAO<ThermalCompound> {

    @Autowired
    private List<ThermalCompound> thermalCompoundList;

    private static final String tableName = "thermal_compound";
    private static final String pk = "id_thermal_compound";

    private static String SELECT = "SELECT *";
    private static String VISIBLE = " AND is_visible = true ";
    private static String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tableName + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tableName + ", state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tableName + ", public.state " + WHERE + NOT_VISIBLE;

    public ThermalCompoundDAOImpl() {
        thermalCompoundList = new ArrayList<>();
    }

    @Override
    public List<ThermalCompound> getAll() {
        return Q2ObjList.fromSelect(ThermalCompound.class, sql);
    }

    @Override
    public ThermalCompound getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(ThermalCompound.class, sql + clause, id);
    }

    @Override
    public ThermalCompound getPublishedById(int id) {
        String clause = " AND " + pk + " = ? AND state.id_state = 1";
        return Q2Obj.fromSelect(ThermalCompound.class, sql + clause, id);
    }

    @Override
    public List<ThermalCompound> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(ThermalCompound.class, sql + clause);
    }

    @Override
    public List<ThermalCompound> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(ThermalCompound.class, sql + clause);
    }

    @Override
    public boolean save(ThermalCompound c) {
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        ThermalCompound c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(ThermalCompound c) {
        ThermalCompound newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(ThermalCompound c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tableName + " SET id_state = ? WHERE " + pk + " = ?", id,
                c.getId());
    }

    @Override
    public List<ThermalCompound> getFields() {
        String clause = "AND " + pk + " = 1";
        return Q2ObjList.fromSelect(ThermalCompound.class, sql_not_visible + clause);
    }
}
