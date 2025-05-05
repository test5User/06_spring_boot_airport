package by.itclass.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.List;

@Entity
@Table(name = "airplanes")
@Getter
@Setter
public class Airplane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Model should not be empty.")
    @Size(min = 2, max = 15, message = "Model should be between 2 and 15 chars.")
    private String model;
    @Min(value = 1, message = "Places should be greater than 0.")
    private int places;
    @OneToMany(mappedBy = "airplane", fetch = FetchType.EAGER)
    private List<Flight> flights;
}
