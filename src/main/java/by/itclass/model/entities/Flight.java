package by.itclass.model.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "flights")
@Getter
@Setter
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @NotEmpty(message = "Number should not be empty.")
    @Size(min = 2, max = 10, message = "Number should be between 2 and 10.")
    private String number;
    @NotEmpty(message = "Direction should not be empty.")
    @Pattern(regexp = "^[A-Za-zА-Яа-я -]+ - [A-Za-zА-Яа-я -]+$", message = "Direction doesn't match.")
    private String direction;
    @Transient
    private int planeId;
    @ManyToOne
    private Airplane airplane;
}
