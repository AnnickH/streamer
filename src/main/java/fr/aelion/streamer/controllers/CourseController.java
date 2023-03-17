package fr.aelion.streamer.controllers;

import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController // class qui traite les requetes entrante
@RequestMapping("api/v1/course") // toute requete depuis le frontend seront traité avec cette class, uri identique à <=
public class CourseController {
    @Autowired
    private CourseService service;
    @GetMapping // associer une method a un verb et a une uri (GET)
    @CrossOrigin
    public List<FullCourseDto> findAll() {
       return service.findAll();
    }
}
