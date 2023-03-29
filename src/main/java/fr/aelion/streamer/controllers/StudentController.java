package fr.aelion.streamer.controllers;

import fr.aelion.streamer.dto.AddStudentDto;
import fr.aelion.streamer.dto.SimpleStudentDto;
import fr.aelion.streamer.dto.SimpleStudentProjection;
import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.services.StudentService;
import fr.aelion.streamer.services.exceptions.EmailAlreadyExistsException;
import fr.aelion.streamer.services.exceptions.LoginAlreadyExistsException;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

@RestController
@RequestMapping("api/v1/students")   // Tout ce qu'on va mettre dans notre controller sera visible:
                                    // http://127.0.0.1:8080/api/v1/students
public class StudentController {
    @Autowired
    private StudentService studentService;




    @GetMapping // http://localhost:8080/api/v1/students on va retourner ici
    @CrossOrigin
    public List<Student> findAll(){
        return studentService.findAll();
    }

    @GetMapping("{id}") // Get http://127.0.0.1:5000/api/v1/students/1
    public ResponseEntity<?> findOne(@PathVariable int id) {
        try {
            return ResponseEntity.ok(studentService.findOne(id));
        } catch (NoSuchElementException e) {
            return ResponseEntity.notFound().build();
            /*return new ResponseEntity<>("Student with "+ id + " was not found", HttpStatus.NOT_FOUND);*/
            // => return : Student with 2000 was not found (if we don't have a student with this id
        }
    }

    @GetMapping("simple")
    @CrossOrigin
    public List<SimpleStudentProjection> findSimpleStudents() {
        return studentService.fromProjection();
    }
    @CrossOrigin
    @GetMapping("dto")
    public List<SimpleStudentDto> simpleStudentDto() {
        return studentService.findSimpleStudents();
    }


    //l'uri est déterminer par le PostMapping
    /**
     * POST a new student
     * uri: POST http://127.0.0.1:5000/api/v1/students
     * @param student
     * @return
     */
    @PostMapping // post = recupere les requetes de types Post
    @CrossOrigin // Plus besoin de le mettre vu qu'on a CorsCongigurationn qui nous donne la possibilité de l'enlever
    public ResponseEntity<?> add(@Valid @RequestBody AddStudentDto student) {
        try {
            Student newStudent = studentService.add(student);
            return ResponseEntity.created(null).body(newStudent);
        } catch(EmailAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.reject());
        } catch (LoginAlreadyExistsException e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body((e.reject()));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PutMapping
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> update(@RequestBody Student student) {
        try{
            studentService.update(student);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch (Exception e){
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<?> singleDelete(@PathVariable int id) {
        try {
            studentService.delete(id);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } catch(Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @DeleteMapping()
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> multipleDelete(@RequestBody Set<Integer> ids) {
        return ResponseEntity.ok(studentService.multipleDelete(ids));
    }


    //public ResponseEntity<?> add(@RequestBody Student student){
    //    return ResponseEntity.created(null).body(studentService.add(student));
        //return ResponseEntity.created(null).build();  //created http
        // le created demande un paramètre ici null et vu que c'est un build on met un build() => return une réponse 201
    //}
}