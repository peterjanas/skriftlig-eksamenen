package app.dtos;

import app.entities.Guide;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Setter
@Getter
@NoArgsConstructor
public class GuideDTO
{
    private int id;
    private String firstName;
    private String lastName;
    private String email;
    private int phone;
    private int yearsOfExperience;

    public GuideDTO(Guide guide)
    {
        this.id = guide.getId();
        this.firstName = guide.getFirstName();
        this.lastName = guide.getLastName();
        this.email = guide.getEmail();
        this.phone = guide.getPhone();
        this.yearsOfExperience = guide.getYearsOfExperience();
    }

    public static List<GuideDTO> toGuideDTOList(List<Guide> guides)
    {
        return guides.stream().map(GuideDTO::new).collect(Collectors.toList());
    }


}


