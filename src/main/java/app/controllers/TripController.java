package app.controllers;

import app.daos.impl.GuideDAO;
import app.daos.impl.TripDAO;
import app.dtos.GuideTotalPriceDTO;
import app.dtos.TripDTO;
import app.entities.Trip;
import app.enums.Category;
import app.exceptions.ApiException;
import io.javalin.http.Context;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class TripController implements IController
{
    private final TripDAO tripDao;
    private final GuideDAO guideDao;

    public TripController(TripDAO tripDao, GuideDAO guideDao)
    {
        this.tripDao = tripDao;
        this.guideDao = guideDao;
    }


    @Override
    public void read(Context ctx) throws ApiException
    {
        try
        {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            Trip trip = tripDao.getById(id);
            TripDTO tripDTO = new TripDTO(trip);
            ctx.res().setStatus(200);
            ctx.json(tripDTO, TripDTO.class);
        } catch (NumberFormatException e)
        {
            throw new ApiException(400, "Invalid trip ID format");
        }
    }

    @Override
    public void readAll(Context ctx) throws ApiException
    {
        List<Trip> trips = tripDao.getAll();
        List<TripDTO> tripDTOs = TripDTO.toTripDTOList(trips);
        ctx.res().setStatus(200);
        ctx.json(tripDTOs, TripDTO.class);
    }

    public void getTripsByGuide(Context ctx) throws ApiException {
        try {
            Integer guideId = Integer.parseInt(ctx.pathParam("guideId"));
            Set<Trip> trips = tripDao.getTripsByGuide(guideId);


            List<TripDTO> tripDtos = TripDTO.toTripDTOList(new ArrayList<>(trips));

            ctx.res().setStatus(200);
            ctx.json(tripDtos);
        } catch (NumberFormatException e) {
            throw new ApiException(400, "Invalid guide ID format");
        }
    }

    public void getTripsByCategory(Context ctx) throws ApiException {
        try {
            String categoryParam = ctx.pathParam("category").toUpperCase();
            Category category = Category.valueOf(categoryParam);

            List<Trip> trips = tripDao.getTripsByCategory(category);
            List<TripDTO> tripDtos = TripDTO.toTripDTOList(trips);

            ctx.res().setStatus(200);
            ctx.json(tripDtos);
        } catch (IllegalArgumentException e) {
            throw new ApiException(400, "Invalid category provided");
        }
    }

    public void getTotalPriceByGuide(Context ctx) throws ApiException {
        List<GuideTotalPriceDTO> results = tripDao.getTotalPriceByGuide();
        ctx.res().setStatus(200);
        ctx.json(results);
    }



    @Override
    public void create(Context ctx) throws ApiException
    {
        TripDTO tripDto = ctx.bodyAsClass(TripDTO.class);
        Trip trip = new Trip(
                tripDto.getStartTime(),
                tripDto.getEndTime(),
                tripDto.getStartLocation(),
                tripDto.getName(),
                tripDto.getPrice(),
                tripDto.getCategory()
        );
        trip = tripDao.create(trip);
        TripDTO createdTripDto = new TripDTO(trip);
        ctx.res().setStatus(201);
        ctx.json(createdTripDto, TripDTO.class);
    }

    @Override
    public void update(Context ctx) throws ApiException
    {
        try
        {
            Integer id = Integer.parseInt(ctx.pathParam("id"));
            TripDTO tripDto = ctx.bodyAsClass(TripDTO.class);
            Trip existingTrip = tripDao.getById(id);

            existingTrip.setStartTime(tripDto.getStartTime());
            existingTrip.setEndTime(tripDto.getEndTime());
            existingTrip.setStartLocation(tripDto.getStartLocation());
            existingTrip.setName(tripDto.getName());
            existingTrip.setPrice(tripDto.getPrice());
            existingTrip.setCategory(tripDto.getCategory());

            tripDao.update(id, existingTrip);
            ctx.res().setStatus(200);
            ctx.json(new TripDTO(existingTrip), TripDTO.class);
        } catch (NumberFormatException e)
        {
            throw new ApiException(400, "Invalid trip ID format");
        }
    }

    @Override
    public void delete(Context ctx) throws ApiException
    {
        try
        {
            int id = Integer.parseInt(ctx.pathParam("id"));
            tripDao.delete(id);
            ctx.res().setStatus(204); // No content
        } catch (NumberFormatException e)
        {
            throw new ApiException(400, "Invalid trip ID format");
        }
    }

    public void addGuideToTrip(Context ctx) throws ApiException
    {
        try
        {
            int tripId = Integer.parseInt(ctx.pathParam("tripId"));
            int guideId = Integer.parseInt(ctx.pathParam("guideId"));

            tripDao.addGuideToTrip(tripId, guideId);
            ctx.res().setStatus(200);
            ctx.result("Guide added to trip");
        } catch (NumberFormatException e)
        {
            throw new ApiException(400, "Invalid trip or guide ID format");
        }
    }
}
