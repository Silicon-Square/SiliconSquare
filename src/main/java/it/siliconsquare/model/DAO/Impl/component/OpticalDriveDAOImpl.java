package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.OpticalDrive;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class OpticalDriveDAOImpl implements ComponentDAO<OpticalDrive> {

    @Autowired
    private List<OpticalDrive> opticalDriveList;

    private static final String tablename = "optical_drive";
    private static final String pk = "id_optical_drive";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public OpticalDriveDAOImpl() {
        opticalDriveList = new ArrayList<>();
    }

    @Override
    public List<OpticalDrive> getAll() {
        return Q2ObjList.fromSelect(OpticalDrive.class, sql);
    }

    @Override
    public List<OpticalDrive> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(OpticalDrive.class, sql + clause);
    }

    @Override
    public OpticalDrive getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(OpticalDrive.class, sql + clause, id);
    }

    @Override
    public OpticalDrive getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(OpticalDrive.class, sql + clause, id);
    }

    @Override
    public List<OpticalDrive> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(OpticalDrive.class, sql + clause);
    }

    @Override
    public boolean save(OpticalDrive c) {
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        OpticalDrive c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(OpticalDrive c) {
        OpticalDrive newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(OpticalDrive c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public List<OpticalDrive> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(OpticalDrive.class, sql_not_visible + clause);
    }
}
