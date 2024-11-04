package app.config;

import app.entities.Guide;
import app.entities.Trip;
import app.enums.Category;
import io.javalin.http.Context;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Populate {

    private final EntityManagerFactory emf;

    public Populate(EntityManagerFactory emf) {
        this.emf = emf;
    }


    public void populateDbFromHttp(Context ctx) {
        List<Guide> guides = new ArrayList<>();
        Set<Trip> tripsForGuide1 = getTripsForGuideforhttp();
        Set<Trip> tripsForGuide2 = getTripsForGuideforhttp();

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Creating Guide entities
            Guide guide1 = new Guide("Alice", "Walker", "alice.walker@example.com", 123456789, 10);
            Guide guide2 = new Guide("Bob", "Thompson", "bob.thompson@example.com", 987654321, 8);

            // Associate trips with guides
            tripsForGuide1.forEach(trip -> trip.setGuide(guide1));
            tripsForGuide2.forEach(trip -> trip.setGuide(guide2));

            guide1.getTrips().addAll(tripsForGuide1);
            guide2.getTrips().addAll(tripsForGuide2);

            // Persist guides and their trips
            guides.add(guide1);
            guides.add(guide2);
            em.persist(guide1);
            em.persist(guide2);

            em.getTransaction().commit();
            ctx.status(201).result("Database populated successfully");
        } catch (Exception e) {
            ctx.status(500).result("Failed to populate database: " + e.getMessage());
            e.printStackTrace();
        }
    }
    private Set<Trip> getTripsForGuideforhttp() {
        Trip trip1 = new Trip(
                LocalDateTime.of(2024, 6, 1, 8, 0),
                LocalDateTime.of(2024, 6, 1, 18, 0),
                "Mountain Base",
                "Mountain Excursion",
                150,
                Category.ADVENTURE
        );

        Trip trip2 = new Trip(
                LocalDateTime.of(2024, 7, 15, 9, 0),
                LocalDateTime.of(2024, 7, 15, 17, 0),
                "Forest Trail",
                "Forest Exploration",
                200,
                Category.NATURE
        );

        Trip trip3 = new Trip(
                LocalDateTime.of(2024, 8, 20, 10, 0),
                LocalDateTime.of(2024, 8, 20, 16, 0),
                "Historic Village",
                "Historic Tour",
                180,
                Category.HISTORICAL
        );

        return new HashSet<>(List.of(trip1, trip2, trip3));
    }


    public static List<Guide> populateDB(EntityManagerFactory emf) {
        List<Guide> guides = new ArrayList<>();

        Set<Trip> tripsForGuide1 = getTripsForGuide();
        Set<Trip> tripsForGuide2 = getTripsForGuide();

        try (var em = emf.createEntityManager()) {
            em.getTransaction().begin();

            // Creating Guide entities
            Guide guide1 = new Guide("Alice", "Walker", "alice.walker@example.com", 123456789, 10);
            Guide guide2 = new Guide("Bob", "Thompson", "bob.thompson@example.com", 987654321, 8);

            // Associate trips with guides
            tripsForGuide1.forEach(trip -> trip.setGuide(guide1));
            tripsForGuide2.forEach(trip -> trip.setGuide(guide2));

            guide1.getTrips().addAll(tripsForGuide1);
            guide2.getTrips().addAll(tripsForGuide2);

            // Persist guides and their trips
            guides.add(guide1);
            guides.add(guide2);
            em.persist(guide1);
            em.persist(guide2);

            em.getTransaction().commit();
        }
        return guides;
    }

    private static Set<Trip> getTripsForGuide() {
        Trip trip1 = new Trip(
                LocalDateTime.of(2024, 6, 1, 8, 0),
                LocalDateTime.of(2024, 6, 1, 18, 0),
                "Mountain Base",
                "Mountain Excursion",
                150,
                Category.ADVENTURE
        );

        Trip trip2 = new Trip(
                LocalDateTime.of(2024, 7, 15, 9, 0),
                LocalDateTime.of(2024, 7, 15, 17, 0),
                "Forest Trail",
                "Forest Exploration",
                200,
                Category.NATURE
        );

        Trip trip3 = new Trip(
                LocalDateTime.of(2024, 8, 20, 10, 0),
                LocalDateTime.of(2024, 8, 20, 16, 0),
                "Historic Village",
                "Historic Tour",
                180,
                Category.HISTORICAL
        );

        return new HashSet<>(List.of(trip1, trip2, trip3));
    }


    public static void cleanDB(EntityManagerFactory emf) {
        try (EntityManager em = emf.createEntityManager()) {
            em.getTransaction().begin();
            em.createQuery("DELETE FROM Trip").executeUpdate();
            em.createQuery("DELETE FROM Guide").executeUpdate();
            em.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
