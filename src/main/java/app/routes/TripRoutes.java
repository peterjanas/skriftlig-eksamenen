package app.routes;

import app.config.HibernateConfig;
import app.config.Populate;
import app.controllers.TripController;
import app.daos.impl.GuideDAO;
import app.daos.impl.TripDAO;
import app.security.enums.Role;
import io.javalin.apibuilder.EndpointGroup;
import jakarta.persistence.EntityManagerFactory;

import static io.javalin.apibuilder.ApiBuilder.*;
import static io.javalin.apibuilder.ApiBuilder.put;

public class TripRoutes
{
    private final EntityManagerFactory emf = HibernateConfig.getEntityManagerFactory();

    private final TripDAO tripDao = new TripDAO(emf);
    private final GuideDAO guideDao = new GuideDAO(emf);
    private final TripController tripController = new TripController(tripDao, guideDao);
    private final Populate populate = new Populate(emf);



    public EndpointGroup getTripeRoutes() {
        return () ->
        {
            get("trips/{id}", tripController::read, Role.ANYONE);
            get("trips/", tripController::readAll, Role.USER );
            get("trips/guides/totalprice", tripController::getTotalPriceByGuide, Role.ANYONE);
            get("trips/guides/{guideId}", tripController::getTripsByGuide, Role.ANYONE);
            get("trips/category/{category}", tripController::getTripsByCategory, Role.ANYONE);
            post("trips/", tripController::create, Role.ANYONE);
            put("trips/{id}", tripController::update, Role.ANYONE);
            delete("trips/{id}", tripController::delete, Role.ANYONE);
            put("trips/{tripId}/guides/{guideId}", tripController::addGuideToTrip, Role.ANYONE);
            post("trips/populate", populate::populateDbFromHttp,Role.ANYONE);
        };
    }
}
