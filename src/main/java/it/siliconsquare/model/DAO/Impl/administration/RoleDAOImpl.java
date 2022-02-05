package it.siliconsquare.model.DAO.Impl.administration;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.administration.RoleDAO;
import it.siliconsquare.model.administration.Role;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class RoleDAOImpl implements RoleDAO {
    @Autowired
    private List<Role> roleList;
    private String sql = "SELECT * FROM role ";

    public RoleDAOImpl() {
        roleList = getAllRoles();
    }

    public List<Role> getAllRole() {
        return roleList;
    }

    private List<Role> getAllRoles() {
        return Q2ObjList.fromSelect(Role.class, sql);
    }

    @Override
    public Role getRoleById(int id) {
        return Q2Obj.fromSelect(Role.class, sql + " WHERE id_role = ?", id);
    }
}