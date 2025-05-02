package by.itclass.controllers;

import by.itclass.model.entities.Airplane;
import by.itclass.model.repositories.AirplaneRepository;
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
@RequestMapping("/airplanes")
public class AirplaneController {
    private AirplaneRepository repository;

    @Autowired
    public void setRepository(AirplaneRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public String getAllPlanes(Model model) {
        model.addAttribute("planes", repository.findAll());
        return "airplanes";
    }

    @GetMapping("/newPlane")
    public String newPlane(@ModelAttribute("plane") Airplane airplane) {
        return "new-plane";
    }

    @PostMapping
    public String savePlane(@ModelAttribute("plane") @Valid Airplane airplane,
                            BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "new-plane";
        }
        repository.save(airplane);
        return "redirect:/airplanes";
    }
}
