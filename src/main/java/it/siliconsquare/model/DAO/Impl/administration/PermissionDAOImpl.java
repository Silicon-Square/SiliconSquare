package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.PermissionDAO;
import it.siliconsquare.model.administration.Permission;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class PermissionDAOImpl implements PermissionDAO {

    @Autowired
    private List<Permission> permissionList;
    private String sql = "SELECT * FROM permission  WHERE permission.id_role = role.id_role and permission.id_action = action.id_action ";

    public PermissionDAOImpl() {
        permissionList = new ArrayList<>();
    }

    @Override
    public List<Permission> getAllPermission() {
        return Q2ObjList.fromSelect(Permission.class, sql);
    }

    @Override
    public Permission getPermissionById(int id) {
        return Q2Obj.fromSelect(Permission.class, sql + "AND id_permission = ?", id);
    }

    @Override
    public void savePermission(Permission permission) {
        Q2Obj.insert(permission);
    }

    @Override
    public void deletePermission(Permission permission) {
        Q2Obj.delete(permission);
    }

    @Override
    public void updatePermission(Permission permission) {
        Q2Obj.update(permission);
    }
}
