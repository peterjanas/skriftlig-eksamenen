package app.controllers;

import app.entities.Message;
import app.exceptions.ApiException;
import io.javalin.http.Context;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

public class ExceptionController {

    private final Logger log = LoggerFactory.getLogger(ExceptionController.class);

    public void apiExceptionHandler(ApiException e, Context ctx) {
        ctx.status(e.getStatus());  // Set the status in the response
        log.error("Error {}: {}", e.getStatus(), e.getMessage());  // Log the correct status from ApiException
        ctx.json(new Message(e.getStatus(), e.getMessage()));
    }

    public void exceptionHandler(Exception e, Context ctx) {
        ctx.status(500);  // Set the response status to 500 for generic exceptions
        log.error("Unexpected error {}: {}", 500, e.getMessage());
        ctx.json(new Message(500, "An unexpected error occurred"));
    }
}
