package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.NotificationDAO;
import it.siliconsquare.model.administration.Notification;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class NotificationDAOImpl implements NotificationDAO {

    @Autowired
    private List<Notification> notificationList;
    private String sql = "SELECT * FROM notification , user  WHERE  Notification.id_user = user.id_user ";

    public NotificationDAOImpl() {
        notificationList = new ArrayList<>();
    }

    @Override
    public List<Notification> getAllNotification() {
        return Q2ObjList.fromSelect(Notification.class, sql);
    }

    @Override
    public Notification getNotificationById(int id) {
        return Q2Obj.fromSelect(Notification.class, sql + "AND id_notification = ?", id);
    }

    @Override
    public void saveNotification(Notification notification) {
        Q2Obj.insert(notification);
    }

    @Override
    public void deleteNotification(Notification notification) {
        Q2Obj.delete(notification);
    }

    @Override
    public void updateNotification(Notification notification) {
        Q2Obj.update(notification);
    }
}
