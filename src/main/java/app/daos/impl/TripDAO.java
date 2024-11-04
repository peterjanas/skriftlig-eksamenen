package app.daos.impl;

import app.daos.IDAO;
import app.daos.ITripGuideDAO;
import app.dtos.GuideTotalPriceDTO;
import app.entities.Guide;
import app.entities.Trip;
import app.enums.Category;
import app.exceptions.ApiException;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TripDAO implements IDAO<Trip, Integer>, ITripGuideDAO<Trip>
{
    private final EntityManagerFactory emf;

    public TripDAO(EntityManagerFactory emf)
    {
        this.emf = emf;
    }


    @Override
    public Trip create(Trip trip) throws ApiException
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            em.persist(trip);
            em.getTransaction().commit();
            return trip;
        } catch (PersistenceException e)
        {
            throw new ApiException(400, "Trip already exists or something else went wrong");
        }
    }

    @Override
    public List<Trip> getAll() throws ApiException
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Trip> query = em.createQuery("SELECT t FROM Trip t", Trip.class);
            List<Trip> trips = query.getResultList();

            if (trips.isEmpty())
            {
                throw new ApiException(404, "No trips found");
            }

            return trips;
        } catch (PersistenceException e)
        {
            throw new ApiException(400, "Something went wrong during getAll trips");
        }
    }


    public Trip getById(Integer id) throws ApiException
    {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Trip> query = em.createQuery(
                    "SELECT t FROM Trip t JOIN FETCH t.guide WHERE t.id = :id", Trip.class);
            query.setParameter("id", id);
            Trip trip = query.getSingleResult();
            return trip;

        } catch (NoResultException e)
        {
            // Throw ApiException with 404 status if no trip is found
            throw new ApiException(404, "Trip not found");
        } catch (PersistenceException e)
        {
            // Handle other persistence-related issues
            throw new ApiException(500, "An error occurred while retrieving the trip");
        }
    }


    @Override
    public Trip update(Integer integer, Trip trip) throws ApiException
    {
        try (EntityManager em = emf.createEntityManager())
        {
            em.getTransaction().begin();
            Trip existingTrip = em.find(Trip.class, integer);
            if (existingTrip == null)
            {
                throw new ApiException(404, "Trip not found");
            }
            existingTrip.setStartTime(trip.getStartTime());
            existingTrip.setEndTime(trip.getEndTime());
            existingTrip.setStartLocation(trip.getStartLocation());
            existingTrip.setName(trip.getName());
            existingTrip.setPrice(trip.getPrice());
            existingTrip.setCategory(trip.getCategory());

            Trip mergedTrip = em.merge(existingTrip);


            em.getTransaction().commit();
            return mergedTrip;
        } catch (PersistenceException e)
        {
            throw new ApiException(400, "Trip not found or something else went wrong during update");
        }
    }

    @Override
    public void delete(Integer integer) throws ApiException
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();
            Trip trip = em.find(Trip.class, integer);
            if (trip == null)
            {
                em.getTransaction().rollback();
                throw new ApiException(404, "Trip not found");
            }
            em.remove(trip);
            em.getTransaction().commit();

        } catch (PersistenceException e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new ApiException(500, "An error occurred while deleting the trip");
        } finally
        {
            em.close();
        }
    }


    @Override
    public void addGuideToTrip(int tripId, int guideId) throws ApiException
    {
        EntityManager em = emf.createEntityManager();
        try
        {
            em.getTransaction().begin();

            // Find the Trip entity
            Trip trip = em.find(Trip.class, tripId);
            if (trip == null)
            {
                throw new ApiException(404, "Trip not found");
            }

            // Find the Guide entity
            Guide guide = em.find(Guide.class, guideId);
            if (guide == null)
            {
                throw new ApiException(404, "Guide not found");
            }

            // Assign the Guide to the Trip
            trip.setGuide(guide);

            // Persist changes
            em.merge(trip);
            em.getTransaction().commit();
        } catch (PersistenceException e)
        {
            if (em.getTransaction().isActive())
            {
                em.getTransaction().rollback();
            }
            throw new ApiException(400, "An error occurred while adding the guide to the trip");
        } finally
        {
            em.close();
        }
    }


    @Override
    public Set<Trip> getTripsByGuide(int guideId) throws ApiException {
        try (EntityManager em = emf.createEntityManager())
        {
            TypedQuery<Trip> query = em.createQuery(
                    "SELECT t FROM Trip t JOIN t.guide g WHERE g.id = :guideId", Trip.class);
            query.setParameter("guideId", guideId);

            List<Trip> trips = query.getResultList();
            if (trips.isEmpty())
            {
                throw new ApiException(404, "No trips found for the specified guide");
            }

            return new HashSet<>(trips);  // Convert List to Set
        } catch (PersistenceException e)
        {
            throw new ApiException(400, "An error occurred while retrieving trips by guide");
        }
    }

    public List<Trip> getTripsByCategory(Category category) throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<Trip> query = em.createQuery(
                    "SELECT t FROM Trip t WHERE t.category = :category", Trip.class);
            query.setParameter("category", category);

            List<Trip> trips = query.getResultList();

            // Check if the list is empty to throw a 404 exception
            if (trips.isEmpty()) {
                throw new ApiException(404, "No trips found for the specified category");
            }

            return trips;
        } catch (PersistenceException e) {
            throw new ApiException(400, "An error occurred while retrieving trips by category");
        }
    }

    public List<GuideTotalPriceDTO> getTotalPriceByGuide() throws ApiException {
        try (EntityManager em = emf.createEntityManager()) {
            TypedQuery<GuideTotalPriceDTO> query = em.createQuery(
                    "SELECT new app.dtos.GuideTotalPriceDTO(g.id, CAST(SUM(t.price) AS int)) " +
                            "FROM Trip t JOIN t.guide g GROUP BY g.id",
                    GuideTotalPriceDTO.class);

            return query.getResultList();
        } catch (PersistenceException e) {
            throw new ApiException(400, "An error occurred while calculating total price by guide");
        }
    }






}
