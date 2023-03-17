package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.dto.ModuleDto;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.repositories.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service // sans ça on ne peut pas instancier automatiquement, autowired => injection de dépendance
public class CourseServiceImpl implements CourseService<Course>{
    @Autowired // si interface pas nécessaire de le mettre
    private CourseRepository repository;
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

}
