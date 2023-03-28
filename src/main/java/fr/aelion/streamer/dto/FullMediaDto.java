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

    private Typemedia typemedia;

//    private Set<TypemediaDto> typemedias = new HashSet<>();
//
//    public void addTypemedia(Typemedia typemedia){
//        var typemediaDto = new TypemediaDto();
//        typemediaDto.setId(typemedia.getId());
//        typemediaDto.setTitle(typemedia.getTitle());
//
//        this.typemedias.add(typemediaDto);
//    }
//    private Set<ModuleDto> modules = new HashSet<>();
//
//    public void addModule(Module module){
//        var moduleDto = new ModuleDto();
//        moduleDto.setId(module.getId());
//        moduleDto.setName(module.getName());
//        moduleDto.setObjective(module.getObjective());
//
//        this.modules.add(moduleDto);
//    }


}
