package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.Media;
import fr.aelion.streamer.entities.Module;
import fr.aelion.streamer.entities.Typemedia;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class FullMediaDto {
    private int Id;

    private String title;

    private String summary;

    private int duration;

    private LocalDate createdAt;

    private String url;

    private Set<Typemedia> typemedia = new HashSet<>();
    private Set<ModuleDto> modules = new HashSet<>();

    public void addModule(Module module){
        var moduleDto = new ModuleDto();
        moduleDto.setId(module.getId());
        moduleDto.setName(module.getName());
        moduleDto.setObjective(module.getObjective());

        this.modules.add(moduleDto);

    }


}
