package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.AddStudentDto;
import fr.aelion.streamer.dto.SimpleStudentDto;
import fr.aelion.streamer.dto.SimpleStudentProjection;
import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.repositories.StudentRepository;
import fr.aelion.streamer.services.exceptions.EmailAlreadyExistsException;
import fr.aelion.streamer.services.exceptions.LoginAlreadyExistsException;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.stream.Collectors;


@Service
public class StudentService {
//    @Autowired //cable automatiquement notre service et (injection de dépendance)
//    private JpaRepository repositor; // je peux l'utilisé car l'interface etends JpaRepository
    @Autowired
    private StudentRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SimpleStudentProjection> fromProjection() {
        return repository.getSimpleStudents();
    }

    public List<Student> findAll() {
        List<Student> students = repository.findAll();
        return students;
    }
      // public List<Student> findAll(){
     //   return repository.findAll();
    //}


      public List<SimpleStudentDto> findSimpleStudents() {
          return repository.findAll()
                  .stream()
                  .map(s -> {
                      SimpleStudentDto dto = new SimpleStudentDto();
                      dto.setId(s.getId());
                      dto.setLastName(s.getLastName());
                      dto.setFirstName(s.getFirstName());
                      dto.setEmail(s.getEmail());
                      return dto;
                  })
                  .collect(Collectors.toList());
      }

    public Student add(AddStudentDto student) throws Exception {

        Student anyStudent = repository.findByEmail(student.getEmail());
        if (anyStudent != null) {
            throw new EmailAlreadyExistsException("Email " + student.getEmail() + " already exists");
        }
        anyStudent = repository.findByEmail(student.getLogin());
        if (anyStudent != null) {
            throw new LoginAlreadyExistsException("Login " + student.getLogin() + " already exists");
        }
        Student newStudent = modelMapper.map(student, Student.class);
        newStudent = (Student) repository.save(newStudent);

        return newStudent;
    }

    public Student findOne(int id) {
        return repository.findById(id)
                .map(s ->s)
                .orElseThrow();
    }

    public void update(Student student) throws Exception {
       try {
           repository.save(student);
       } catch (Exception e) {
           throw new Exception("Something went wrong while updating Student");
       }
    }
//service fait ce que demande le controller (sert a faire la methode)
    public void delete(int id) {
        try {
            var student = this.findOne(id);
            repository.delete(student);
        } catch (NoSuchElementException e) {
            throw e;
        }
    }

    public Set<Integer> multipleDelete(Set<Integer> ids) {
        var nonDeletedIds = new HashSet<Integer>();
        ids.stream()
                .forEach(i -> {
                    try {
                        repository.delete(this.findOne(i));
                    } catch(NoSuchElementException e) {
                        nonDeletedIds.add(i);
                    } catch (Exception e) {
                        nonDeletedIds.add(i);
                    }
                });
        return nonDeletedIds;
    }


    /*public void delete (int id) {
        try {
            var student = this.findOne(id);
            repository.delete(student);
        } catch (NoSuchElementException e){
            throw e;
        }
    }


    public Set<Integer> multipleDelete(Set<Integer> ids) {
        var nonDeletedIds = new HashSet<Integer>();
        ids.stream()
                .forEach(i ->{
                    try {
                        repository.delete(this.findOne(i));
                    }catch (NoSuchElementException e) {
                        nonDeletedIds.add(i);
                    }catch (Exception e) {
                        nonDeletedIds.add(i);
                    }
                });
        return nonDeletedIds;
    }*/
   /* public Student add(AddStudentDto student) throws Exception { // Student => AddStudentDto

        Student anyStudent = repository.findByEmail(student.getEmail());
        // if email was already created: we can't use this email
        if (anyStudent != null) {
            throw new Exception("Student already exists");
        }
        Student newStudent = modelMapper.map(student, Student.class);
        newStudent = (Student) repository.save(newStudent);

        return newStudent;
    }*/

    //public Student add(Student student){
    //    student = (Student) repository.save(student);
    //    return student;
    //}


}
