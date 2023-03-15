package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Module;
import lombok.Getter;
import lombok.Setter;
import org.springframework.cglib.core.Local;
import fr.aelion.streamer.dto.ModuleDto;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FullCourseDto {
    private int id;
    private String title;
    private LocalDate createdAt;
    private LocalDate updatedAt;

    private String objective;
    private Set<ModuleDto> modules = new HashSet<>(); //HashSet est une classe fille de Set (qui est une class abstract)

    public void addModule(Module module){ //! import depuis fr.aelion
        var moduleDto = new ModuleDto(); // new ModulesDto() est une instance
        moduleDto.setId(module.getId());
        moduleDto.setName(module.getName());
        moduleDto.setObjective(module.getObjective());

        this.modules.add(moduleDto);
    }
}
