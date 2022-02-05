package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Permission;

import java.util.List;

public interface PermissionDAO {
    List<Permission> getAllPermission();

    Permission getPermissionById(int id);

    void savePermission(Permission permission);

    void deletePermission(Permission permission);

    void updatePermission(Permission permission);
}
