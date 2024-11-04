package app.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@Getter
@NoArgsConstructor
@Entity
public class Guide
{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Setter(lombok.AccessLevel.NONE)
    private int id;

    private String firstName;

    private String lastName;

    private String email;

    private int phone;

    private int yearsOfExperience;

    @OneToMany(mappedBy = "guide", cascade = CascadeType.PERSIST)
    private Set<Trip> trips = new HashSet<>();

    public Guide(String firstName, String lastName, String email, int phone, int yearsOfExperience)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phone = phone;
        this.yearsOfExperience = yearsOfExperience;
    }
}
