package app.daos;

import app.exceptions.ApiException;

import java.util.List;

public interface IDAO<T, I>
{
    T create(T t)throws ApiException;
    List<T> getAll()throws ApiException;
    T getById(I i)throws ApiException;
    T update(I i, T t)throws ApiException;
    void delete(I i)throws ApiException;
}



