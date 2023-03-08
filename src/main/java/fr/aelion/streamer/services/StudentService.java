package fr.aelion.streamer.services;

import fr.aelion.streamer.entities.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {
    @Autowired //cable automatiquement notre service et (injection de dépendance)
    private JpaRepository repository; // je peux l'utilisé car l'interface etends JpaRepository
    public List<Student> findAll(){
        return repository.findAll();
    }

    public Student add(Student student){
        student = (Student) repository.save(student);
        return student;
    }
}
