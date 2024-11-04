package app.daos;

import app.exceptions.ApiException;

import java.util.Set;

public interface ITripGuideDAO<T> {
    void addGuideToTrip(int tripId, int guideId) throws ApiException;

    Set<T> getTripsByGuide(int guideId) throws ApiException;
}

