package lk.ijse.dep.fx.dao;

import lk.ijse.dep.fx.entity.SuperEntity;

import java.util.List;
import java.util.Optional;

public interface CrudDAO< T extends SuperEntity,ID> extends SuperDAO {
    Optional<T> find(ID key) throws Exception;

    Optional<List<T>> findAll() throws Exception;

    boolean save(T entity) throws Exception;

    boolean update(T entity) throws Exception;

    boolean delete(ID key) throws Exception;
}
