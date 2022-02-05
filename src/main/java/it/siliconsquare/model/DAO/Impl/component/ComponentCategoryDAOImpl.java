package it.siliconsquare.model.DAO.Impl.component;

import com.zaxxer.q2o.Q2Obj;
import com.zaxxer.q2o.Q2ObjList;
import it.siliconsquare.model.DAO.component.ComponentCategoryDAO;
import it.siliconsquare.model.component.ComponentCategory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ComponentCategoryDAOImpl implements ComponentCategoryDAO {
    @Autowired
    List<ComponentCategory> componentCategories = new ArrayList<>();
    private String sql = "SELECT * FROM public.component_category";

    public ComponentCategoryDAOImpl() {
    }

    @Override
    public List<ComponentCategory> getAllComponentCategory() {
        componentCategories = Q2ObjList.fromSelect(ComponentCategory.class, sql + ";");
        return componentCategories;
    }

    @Override
    public ComponentCategory getComponentCategoryById(int id) {
        return Q2Obj.fromSelect(ComponentCategory.class, sql + " WHERE id_component_category = ?", id);
    }

    @Override
    public ComponentCategory getByPath(String path) {
        String query = "SELECT * FROM public.component_category WHERE path = ?";
        return Q2Obj.fromSelect(ComponentCategory.class, query, path);
    }
}
