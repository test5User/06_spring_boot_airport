package by.itclass.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "passengers")
@Getter
@Setter
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "FIO should not be empty")
    @Size(min = 3, max = 30, message = "FIO should be between 3 and 30 characters.")
    private String fio;
    @NotEmpty(message = "place should not be empty")
    @Pattern(regexp = "^(?:[1-9]|1\\d|2[0-5])[A-F]$")
    private String place;
    @Transient
    private int flightId;
    @ManyToOne
    private Flight flight;
}
