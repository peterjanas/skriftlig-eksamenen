package app.config;

import app.controllers.ExceptionController;
import app.entities.Message;
import app.exceptions.ApiException;
import app.routes.Routes;
import app.security.controllers.AccessController;
import app.security.routes.SecurityRoutes;
import app.utils.ApiProps;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = lombok.AccessLevel.PRIVATE)
public class AppConfig {

    private static final Routes routes = new Routes();
    private static final ExceptionController exceptionController = new ExceptionController();
    private static final AccessController accessController = new AccessController();

    private static void configuration(JavalinConfig config) {

        config.router.contextPath = ApiProps.API_CONTEXT;
        config.bundledPlugins.enableRouteOverview("/routes");
        config.bundledPlugins.enableDevLogging();
        config.router.apiBuilder(routes.getApiRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecuredRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecurityRoutes());

    }

    private static void exceptionContext(Javalin app) {

        app.exception(ApiException.class, exceptionController::apiExceptionHandler);
        app.exception(Exception.class, exceptionController::exceptionHandler);


    }

    public static void startServer() {
        var app = io.javalin.Javalin.create(AppConfig::configuration);
        exceptionContext(app);
        app.beforeMatched(accessController::accessHandler);
        app.get("*", ctx -> {throw new ApiException(404, "Resource not found");});
        app.error(500, ctx -> {ctx.json(new Message(500, "Internal server error"));});
        app.start(ApiProps.PORT);
    }

    public static void stopServer(Javalin app) {
        app.stop();
    }
}
