package fr.aelion.streamer.dto;

import fr.aelion.streamer.entities.Media;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
public class ModuleDto {
    private int id;
    private String name;
    private String objective;
    //course est exclu par rapport a l'entité

    private Set<FullMediaDto> medias = new HashSet<>();
    public void addMedia(Media media){
        var fullMediaDto = new FullMediaDto();
        fullMediaDto.setId(media.getId());
        fullMediaDto.setDuration(media.getDuration());
        fullMediaDto.setTitle(media.getTitle());
        fullMediaDto.setTypemedia(media.getTypemedia());

        this.medias.add(fullMediaDto);
    }

}
