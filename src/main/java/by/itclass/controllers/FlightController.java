package by.itclass.controllers;

import by.itclass.model.entities.Flight;
import by.itclass.model.repositories.AirplaneRepository;
import by.itclass.model.repositories.FlightRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/flights")
public class FlightController {
    private FlightRepository flightRepository;
    private AirplaneRepository airplaneRepository;

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Autowired
    public void setAirplaneRepository(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping
    public String getAllFlights(Model model) {
        model.addAttribute("flights", flightRepository.findAll());
        return "flights";
    }

    @GetMapping("/newFlight")
    public String newFlight(@ModelAttribute("flight") Flight flight) {
        return "new-flight";
    }

    @PostMapping
    public String addFlight(@ModelAttribute("flight") @Valid Flight flight,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-flight";
        }
        var airplane = airplaneRepository.findById(flight.getPlaneId()).get();
        flight.setAirplane(airplane);
        flightRepository.save(flight);
        return "redirect:/flights";
    }
}
