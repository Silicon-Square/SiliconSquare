package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.Case;
import it.siliconsquare.provider.Database;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class CaseDAOImpl implements ComponentDAO<Case> {
    @Autowired
    private List<Case> caseList;
    // private String sql = "SELECT * FROM public.case, public.state WHERE
    // is_visible = true AND public.case.id_state = state.id_state";

    private static final String tablename = "public.case";
    private static final String pk = "id_case";

    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    /*
     * private String sql = "SELECT * FROM public.case, public.state " + WHERE
     * + " WHERE is_visible = true AND public.case.id_state = state.id_state";
     */
    public CaseDAOImpl() {
        caseList = new ArrayList<>();
    }

    @Override
    public List<Case> getAll() {
        return Q2ObjList.fromSelect(Case.class, sql);
    }

    @Override
    public List<Case> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(Case.class, sql + clause);
    }
    
    @Override
    public List<Case> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(Case.class, sql + clause);
    }

    @Override
    public Case getById(int id) {
        String clause = " AND " + pk + " = ?";
        System.out.println(sql);
        return Q2Obj.fromSelect(Case.class, sql + clause, id);
    }

    @Override
    public Case getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        System.out.println(sql);
        return Q2Obj.fromSelect(Case.class, sql + clause, id);
    }

    @Override
    public boolean save(Case c) {
        System.out.println(c.getState().getName());
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public int setState(Case c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public boolean delete(int id) {
        Case c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(Case c) {
        Case newCase = Q2Obj.update(c);
        newCase.setState(c.getState());
        return newCase != null && newCase.getId() != 0;
    }

    @Override
    public List<Case> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(Case.class, sql_not_visible + clause);
    }
}