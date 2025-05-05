package by.itclass.controllers;

import by.itclass.model.repositories.AirplaneRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ApplicationController {
    private AirplaneRepository airplaneRepository;

    @Autowired
    public void setAirplaneRepository(AirplaneRepository airplaneRepository) {
        this.airplaneRepository = airplaneRepository;
    }

    @GetMapping
    public String root(HttpSession session) {
        session.setAttribute("ourPlanes", airplaneRepository.findAll());
        return "airport";
    }
}
