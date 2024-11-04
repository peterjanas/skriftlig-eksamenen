package app.dtos;

import app.entities.Trip;
import app.enums.Category;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class TripDTO
{
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String startLocation;
    private String name;
    private int price;
    private Category category;
    private GuideDTO guide;

    public TripDTO(Trip trip)
    {
        this.id = trip.getId();
        this.startTime = trip.getStartTime();
        this.endTime = trip.getEndTime();
        this.startLocation = trip.getStartLocation();
        this.name = trip.getName();
        this.price = trip.getPrice();
        this.category = trip.getCategory();
        this.guide = trip.getGuide() != null ? new GuideDTO(trip.getGuide()) : null;  // Initialize GuideDTO if a guide exists
    }


     public static List<TripDTO> toTripDTOList (List<Trip> trips) {
        return trips.stream().map(TripDTO::new).collect(Collectors.toList());
    }

}
