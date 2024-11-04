package app.daos.impl;

import app.daos.IDAO;
import app.entities.Guide;
import app.entities.Trip;
import app.exceptions.ApiException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.util.List;

public class GuideDAO implements IDAO<Guide, Integer>
{
    private final EntityManagerFactory emf;

    public GuideDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }

    @Override
    public Guide create(Guide guide)
    {
        return null;
    }

    @Override
    public List<Guide> getAll()
    {
        return null;
    }

    @Override
    public Guide getById(Integer integer)
    {
        return null;
    }

    @Override
    public Guide update(Integer integer, Guide guide)
    {
        return null;
    }

    @Override
    public void delete(Integer integer)
    {

    }
}
