package it.siliconsquare.model.DAO.component;

import java.util.List;

public interface ComponentDAO<T> {

    List<T> getAll();

    T getById(int id);

    List<T> getAllPublished();
    List<T> getAllUnpublished();

    T getPublishedById(int id);

    boolean save(T component);

    boolean delete(int id);

    boolean update(T component);

    int setState(T c, int id);

    List<T> getFields();

}