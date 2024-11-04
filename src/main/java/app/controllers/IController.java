package app.controllers;

import app.exceptions.ApiException;
import io.javalin.http.Context;

public interface IController {

    public void read(Context ctx) throws ApiException;
    public void readAll(Context ctx) throws ApiException;
    public void create(Context ctx) throws ApiException;
    public void update(Context ctx) throws ApiException;
    void delete(Context ctx) throws ApiException;
}
