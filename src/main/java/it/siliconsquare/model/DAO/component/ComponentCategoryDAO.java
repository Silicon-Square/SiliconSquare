package it.siliconsquare.model.DAO.component;

import it.siliconsquare.model.component.ComponentCategory;

import java.util.List;

public interface ComponentCategoryDAO {
    List<ComponentCategory> getAllComponentCategory();

    ComponentCategory getComponentCategoryById(int id);

    ComponentCategory getByPath(String path);

}
