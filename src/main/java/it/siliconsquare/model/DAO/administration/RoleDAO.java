package it.siliconsquare.model.DAO.administration;

import it.siliconsquare.model.administration.Role;

import java.util.List;

public interface RoleDAO {
    public List<Role> getAllRole();

    Role getRoleById(int id);
}