package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.AddStudentDto;
import fr.aelion.streamer.dto.SimpleStudentDto;
import fr.aelion.streamer.dto.SimpleStudentProjection;
import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public Student add(AddStudentDto student) throws Exception { // Student => AddStudentDto

        Student anyStudent = repository.findByEmail(student.getEmail());
        // if email was already created: we can't use this email
        if (anyStudent != null) {
            throw new Exception("Student already exists");
        }
        Student newStudent = modelMapper.map(student, Student.class);
        newStudent = (Student) repository.save(newStudent);

        return newStudent;
    }

    //public Student add(Student student){
    //    student = (Student) repository.save(student);
    //    return student;
    //}


}
