package org.example.repository;;

import org.example.domain.Entity;
import org.example.domain.Movie;

import java.util.List;

public interface IRepository <T extends Entity>{
    /**
     *
     * @param entity
     * @throws Exception
     */
    void create (T entity) throws Exception;

    /**
     *
     * @param
     * @throws Exception
     */
    void update (T entity) throws Exception;

    T readOne(int id) throws Exception;
    List<T> read ();
    void delete (Integer entityId) throws Exception;

}
