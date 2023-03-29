package fr.aelion.streamer.controllers;

import fr.aelion.streamer.dto.AddCourse;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.services.CourseService;
import fr.aelion.streamer.services.CourseServiceImpl;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // class qui traite les requetes entrante
@RequestMapping("api/v1/course") // toute requete depuis le frontend seront traité avec cette class, uri identique à <=
public class CourseController {
    @Autowired
    private CourseServiceImpl courseService;

    @GetMapping // associer une method a un verb et a une uri (GET)
    @CrossOrigin
    public List findAll() {
       return courseService.findAll();
    }

    @PostMapping
    @CrossOrigin
    public ResponseEntity<?> add(@Valid @RequestBody AddCourse course) {
        try {
            Course newCourse = courseService.add(course);
            return ResponseEntity.created(null).body(newCourse);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }


   /* @GetMapping("/{id}")
   @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> findOne(@PathVariable() int id) {
        try {
            return ResponseEntity.ok(service.findOne(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
        }
    } */
}
