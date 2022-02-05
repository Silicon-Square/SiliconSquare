package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.CpuCooler;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CpuCoolerDAOImpl implements ComponentDAO<CpuCooler> {

    @Autowired
    private List<CpuCooler> cpuCoolerList;

    private static final String tablename = "cpu_cooler";
    private static final String pk = "id_cpu_cooler";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public CpuCoolerDAOImpl() {
        cpuCoolerList = new ArrayList<>();
    }

    @Override
    public List<CpuCooler> getAll() {
        return Q2ObjList.fromSelect(CpuCooler.class, sql);
    }

    @Override
    public List<CpuCooler> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(CpuCooler.class, sql + clause);
    }

    @Override
    public List<CpuCooler> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(CpuCooler.class, sql + clause);
    }

    @Override
    public CpuCooler getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(CpuCooler.class, sql + clause, id);
    }

    @Override
    public CpuCooler getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + tablename + "." + pk + " = ?";
        return Q2Obj.fromSelect(CpuCooler.class, sql + clause, id);
    }

    @Override
    public boolean save(CpuCooler cpuCooler) {

        cpuCooler = Q2Obj.insert(cpuCooler);
        int affectedRows = setState(cpuCooler, cpuCooler.getState().getId());
        return cpuCooler != null && cpuCooler.getId() != 0 && affectedRows > 0;
    }

    @Override
    public int setState(CpuCooler c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public boolean delete(int id) {
        CpuCooler c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(CpuCooler c) {
        CpuCooler newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public List<CpuCooler> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(CpuCooler.class, sql_not_visible + clause);
    }

}
