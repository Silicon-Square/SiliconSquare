package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.Cpu;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CPUDAOImpl implements ComponentDAO<Cpu> {
    @Autowired
    private List<Cpu> cpuList;

    private static final String tablename = "cpu";
    private static final String pk = "id_cpu";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public CPUDAOImpl() {
        this.cpuList = new ArrayList<>();
    }

    @Override
    public List<Cpu> getAll() {
        return Q2ObjList.fromSelect(Cpu.class, sql);
    }

    @Override
    public List<Cpu> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(Cpu.class, sql + clause);
    }

    @Override
    public List<Cpu> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(Cpu.class, sql + clause);
    }

    @Override
    public Cpu getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(Cpu.class, sql + clause, id);
    }

    @Override
    public Cpu getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(Cpu.class, sql + clause, id);
    }

    @Override
    public boolean save(Cpu cpu) {
        cpu = Q2Obj.insert(cpu);
        int affectedRows = setState(cpu, cpu.getState().getId());
        return cpu != null && cpu.getId() != 0 && affectedRows > 0;
    }

    @Override
    public int setState(Cpu c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public boolean delete(int id) {
        Cpu c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(Cpu c) {
        Cpu newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public List<Cpu> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(Cpu.class, sql_not_visible + clause);
    }
}
