package app.entities;

import app.enums.Category;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Trip
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    private int id;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String startLocation;
    private String name;
    private int price;
    private Category category;

    @ManyToOne
    private Guide guide;

    public Trip(LocalDateTime startTime, LocalDateTime endTime, String startLocation, String name, int price, Category category)
    {
        this.startTime = startTime;
        this.endTime = endTime;
        this.startLocation = startLocation;
        this.name = name;
        this.price = price;
        this.category = category;
    }
}
