package fr.aelion.streamer.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ModuleDto {
    private int id;
    private String name;
    private String objective;
    //course est exclu par rapport a l'entité

}
