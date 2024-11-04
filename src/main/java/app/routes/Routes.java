package app.routes;

import app.entities.Trip;
import io.javalin.apibuilder.EndpointGroup;

import static io.javalin.apibuilder.ApiBuilder.path;

public class Routes {



   private final TripRoutes tripRoutes = new TripRoutes();

    public EndpointGroup getApiRoutes() {
        return () ->
        {
            path("/", tripRoutes.getTripeRoutes());


        };
    }


}
