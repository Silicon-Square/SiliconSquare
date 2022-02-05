package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.BannedUserDAO;
import it.siliconsquare.model.administration.BannedUser;
import it.siliconsquare.model.component.Case;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class BannedUserDAOImpl implements BannedUserDAO {
    @Autowired
    private List<Case> bannedUserList;
    private String sql = "SELECT * FROM banned_user, public.user WHERE banned_user.id_user = public.user.id_user ";

    public BannedUserDAOImpl() {
        bannedUserList = new ArrayList<>();
    }

    public List<BannedUser> getAllBannedUser() {
        return Q2ObjList.fromSelect(BannedUser.class, sql);
    }

    @Override
    public BannedUser getBannedUserByUserId(int id) {
        BannedUser b = Q2Obj.fromSelect(BannedUser.class, sql + " AND banned_user.id_user = ?", id);

        return b;
    }

    @Override
    public void saveBannedUser(BannedUser b) {
        Q2Obj.insert(b);
    }

    @Override
    public void deleteBannedUser(BannedUser b) {
        Q2Obj.delete(b);
    }

    @Override
    public void updateBannedUser(BannedUser b) {
        Q2Obj.update(b);
    }
}