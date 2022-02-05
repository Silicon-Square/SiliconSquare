package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Notification;

import java.util.List;

public interface NotificationDAO {
    List<Notification> getAllNotification();

    Notification getNotificationById(int id);

    void saveNotification(Notification notification);

    void deleteNotification(Notification notification);

    void updateNotification(Notification notification);
}
