package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.SoundCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class SoundCardDAOImpl implements ComponentDAO<SoundCard> {
    @Autowired
    private List<SoundCard> soundCardList;

    private static final String tablename = "sound_card";
    private static final String pk = "id_sound_card";
    private static String SELECT = "SELECT *";
    private static final String VISIBLE = " AND is_visible = true ";
    private static final String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tablename + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tablename + ", public.state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tablename + ", public.state " + WHERE + NOT_VISIBLE;

    public SoundCardDAOImpl() {
        soundCardList = getAll();
    }

    @Override
    public List<SoundCard> getAll() {
        return Q2ObjList.fromSelect(SoundCard.class, sql);
    }

    @Override
    public List<SoundCard> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(SoundCard.class, sql + clause);
    }

    @Override
    public List<SoundCard> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(SoundCard.class, sql + clause);
    }

    @Override
    public SoundCard getById(int id) {
        String clause = " AND " + pk + " = ?";
        return Q2Obj.fromSelect(SoundCard.class, sql + clause, id);
    }

    @Override
    public SoundCard getPublishedById(int id) {
        String clause = " AND state.id_state = 1 AND " + pk + " = ?";
        return Q2Obj.fromSelect(SoundCard.class, sql + clause, id);
    }

    @Override
    public boolean save(SoundCard c) {
        c = Q2Obj.insert(c);
        int affectedRows = setState(c, c.getState().getId());
        return c != null && c.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        SoundCard c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(SoundCard c) {
        SoundCard newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(SoundCard c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tablename + " SET id_state = ? WHERE " + pk + " = ?", id, c.getId());
    }

    @Override
    public List<SoundCard> getFields() {
        String clause = " AND " + pk + " = 1";
        return Q2ObjList.fromSelect(SoundCard.class, sql_not_visible + clause);
    }

}