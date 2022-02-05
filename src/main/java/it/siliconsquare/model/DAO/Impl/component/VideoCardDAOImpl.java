package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import com.zaxxer.q2o.Q2Sql;
import it.siliconsquare.model.DAO.component.ComponentDAO;
import it.siliconsquare.model.component.VideoCard;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class VideoCardDAOImpl implements ComponentDAO<VideoCard> {
    @Autowired
    private List<VideoCard> videoCardList;
    // private String sql = "SELECT * from video_card, state WHERE is_visible = true
    // AND video_card.id_state = state.id_state ";

    private static final String tableName = "video_card";
    private static final String pk = "id_video_card";

    private static String SELECT = "SELECT *";
    private static String VISIBLE = " AND is_visible = true ";
    private static String NOT_VISIBLE = " AND is_visible = false ";
    private static String WHERE = " WHERE " + tableName + ".id_state = state.id_state ";

    private static String sql = SELECT + " FROM " + tableName + ", state " + WHERE + VISIBLE;
    private static String sql_not_visible = SELECT + " FROM " + tableName + ", public.state " + WHERE + NOT_VISIBLE;

    public VideoCardDAOImpl() {
        videoCardList = new ArrayList<>();
    }

    public List<VideoCard> getAll() {
        return Q2ObjList.fromSelect(VideoCard.class, sql);
    }

    @Override
    public VideoCard getById(int id) {
        String clause = "AND " + pk + " = ?";
        return Q2Obj.fromSelect(VideoCard.class, sql + clause, id);
    }

    @Override
    public VideoCard getPublishedById(int id) {
        String clause = "AND " + pk + " = ? AND state.id_state = 1";
        return Q2Obj.fromSelect(VideoCard.class, sql + clause, id);
    }

    @Override
    public List<VideoCard> getAllPublished() {
        String clause = " AND state.id_state = 1";
        return Q2ObjList.fromSelect(VideoCard.class, sql + clause);
    }

    @Override
    public List<VideoCard> getAllUnpublished() {
        String clause = " AND state.id_state != 1";
        return Q2ObjList.fromSelect(VideoCard.class, sql + clause);
    }

    @Override
    public boolean save(VideoCard v) {
        v = Q2Obj.insert(v);
        int affectedRows = setState(v, v.getState().getId());
        return v != null && v.getId() != 0 && affectedRows > 0;
    }

    @Override
    public boolean delete(int id) {
        VideoCard c = getById(id);
        c.setVisible(false);
        c = Q2Obj.update(c);
        return c != null && !c.isVisible();
    }

    @Override
    public boolean update(VideoCard c) {
        VideoCard newC = Q2Obj.update(c);
        newC.setState(c.getState());
        return newC != null && newC.getId() != 0;
    }

    @Override
    public int setState(VideoCard c, int id) {
        return Q2Sql.executeUpdate("UPDATE " + tableName + " SET id_state = ? WHERE " + pk + " = ?", id,
                c.getId());
    }

    @Override
    public List<VideoCard> getFields() {
        String clause = "AND " + pk + " = 1";
        return Q2ObjList.fromSelect(VideoCard.class, sql_not_visible + clause);
    }
}