package app.config;

import app.controllers.ExceptionController;
import app.entities.Message;
import app.exceptions.ApiException;
import app.routes.Routes;
import app.security.controllers.AccessController;
import app.security.routes.SecurityRoutes;
import io.javalin.Javalin;
import io.javalin.config.JavalinConfig;

import com.fasterxml.jackson.databind.ObjectMapper;
import app.security.controllers.SecurityController;
import app.security.enums.Role;
import app.utils.Utils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationConfig
{
    private static Routes routes = new Routes();
    private static final ExceptionController exceptionController = new ExceptionController();
    private static ObjectMapper jsonMapper = new Utils().getObjectMapper();
    private static SecurityController securityController = SecurityController.getInstance();
    private static AccessController accessController = new AccessController();
    private static Logger logger = LoggerFactory.getLogger(ApplicationConfig.class);

    private static Javalin app;

    public static void configuration(JavalinConfig config)
    {
        config.showJavalinBanner = false;
        config.bundledPlugins.enableRouteOverview("/routes", Role.ANYONE);
        config.router.contextPath = "/api"; // base path for all endpoints
        config.router.apiBuilder(routes.getApiRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecuredRoutes());
        config.router.apiBuilder(SecurityRoutes.getSecurityRoutes());
    }

    private static void exceptionContext(Javalin app) {

        app.exception(ApiException.class, exceptionController::apiExceptionHandler);
        app.exception(Exception.class, exceptionController::exceptionHandler);


    }

    public static Javalin startServer(int port)
    {
        Javalin app = Javalin.create(ApplicationConfig::configuration);
        exceptionContext(app);
        app.beforeMatched(accessController::accessHandler);
        app.get("*", ctx -> {throw new ApiException(404, "Resource not found");});
        app.error(500, ctx -> {ctx.json(new Message(500, "Internal server error"));});
        app.start(port);
        return app;
    }

    public static void stopServer(Javalin app)
    {
        app.stop();
    }


/*
    private static void generalExceptionHandler(Exception e, Context ctx)
    {
        logger.error("An unhandled exception occurred", e.getMessage());
        ctx.json(Utils.convertToJsonMessage(ctx, "error", e.getMessage()));
    }

    public static void apiExceptionHandler(ApiException e, Context ctx)
    {
        ctx.status(e.getCode());
        logger.warn("An API exception occurred: Code: {}, Message: {}", e.getCode(), e.getMessage());
        ctx.json(Utils.convertToJsonMessage(ctx, "warning", e.getMessage()));
    }

 */
}