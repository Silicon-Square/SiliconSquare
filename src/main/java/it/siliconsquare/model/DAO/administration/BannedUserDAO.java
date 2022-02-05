package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.BannedUser;

import java.util.List;

public interface BannedUserDAO {
    public List<BannedUser> getAllBannedUser();

    BannedUser getBannedUserByUserId(int id);
    //boolean stillBanned(BannedUser b); 

    void saveBannedUser(BannedUser b);

    void deleteBannedUser(BannedUser b);

    void updateBannedUser(BannedUser b);
}