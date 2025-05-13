package by.itclass.controllers;

import by.itclass.model.entities.Passenger;
import by.itclass.model.repositories.FlightRepository;
import by.itclass.model.repositories.PassengerRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/passengers")
public class PassengersController {
    private PassengerRepository passengerRepository;
    private FlightRepository flightRepository;

    @Autowired
    public void setPassengerRepository(PassengerRepository passengerRepository) {
        this.passengerRepository = passengerRepository;
    }

    @Autowired
    public void setFlightRepository(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @GetMapping("/add/{id}")
    public String addPass(@ModelAttribute("pass") Passenger passenger,
                          @PathVariable("id") int flightId) {
        passenger.setFlightId(flightId);
        return "add-pass";
    }

    @PostMapping
    public String savePass(@ModelAttribute("pass") @Valid Passenger pass,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "add-pass";
        }
        if (passengerRepository.findByFlight_IdAndPlace(pass.getFlightId(), pass.getPlace()) != null) {
            bindingResult.rejectValue("place", null,"Busy");
            return "add-pass";
        }
        pass.setFlight(flightRepository.getById(pass.getFlightId()));
        passengerRepository.save(pass);
        return "redirect:/flights/" + pass.getFlightId();
    }

    @DeleteMapping("/{id}/{id2}")
    public String delPass(@PathVariable("id") int passId,
                          @PathVariable("id2") int flightId) {
        passengerRepository.deleteById(passId);
        return "redirect:/flights/" + flightId;
    }

    @GetMapping("/upd/{id}")
    public String updPass(@PathVariable("id") int passId,
                          Model model) {
        var passenger = passengerRepository.getById(passId);
        passenger.setFlightId(passenger.getFlight().getId());
        model.addAttribute("pass", passenger);
        return "edit-pass";
    }

    @PatchMapping("/{id}")
    public String saveUpdated(@ModelAttribute("pass") @Valid Passenger passenger,
                              BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "edit-pass";
        }
        if (passengerRepository.findByFlight_IdAndPlace(passenger.getFlightId(), passenger.getPlace()) != null) {
            bindingResult.rejectValue("place", null,"Busy");
            return "edit-pass";
        }
        passenger.setFlight(flightRepository.getById(passenger.getFlightId()));
        passengerRepository.save(passenger);
        return "redirect:/flights/" + passenger.getFlightId();
    }
}
