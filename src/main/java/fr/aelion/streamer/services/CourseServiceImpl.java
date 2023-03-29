package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.AddCourse;
import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.dto.ModuleDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.repositories.CourseRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // sans ça on ne peut pas instancier automatiquement, autowired => injection de dépendance
public class CourseServiceImpl implements CourseService<Course>{
    @Autowired // si interface pas nécessaire de le mettre
    private CourseRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<FullCourseDto> findAll(){
        return repository.findAll()
                .stream()
                .map(c ->{
                    FullCourseDto fullCourseDto = new FullCourseDto(); // creer un instante de FullCourseDto

                    fullCourseDto.setId(c.getId());
                    fullCourseDto.setTitle(c.getTitle());
                    fullCourseDto.setCreatedAt(c.getCreatedAt());
                    fullCourseDto.setUpdatedAt(c.getUpdatedAt());
                    var modules = c.getModules();
                    // Make as many ModuleDto as needed
                    for(var module : modules) { //for of => java obligé de type donc var
                        var moduleDto = new ModuleDto();
                        fullCourseDto.addModule(module);
                    }
                    return fullCourseDto;
                })
                .collect(Collectors.toList());
    }

    public Course add(AddCourse course) throws Exception{
        if(course.getTitle() == null){
            throw new Exception("Title cannot be empty");
        }
       Course newCourse = modelMapper.map(course, Course.class);
        newCourse = (Course) repository.save(newCourse);
       return newCourse;
    }

    public void update(Course course) throws Exception{
        try {
            repository.save(course);
        } catch (Exception e) {
            throw new Exception("Something went wrong while updating Course");
        }
    }

}
