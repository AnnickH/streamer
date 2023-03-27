package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class Media {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    private String title;

    private String summary;

    private int duration;

    private LocalDate  createdAt;

    private String url;

    @ManyToOne
    @JoinColumn(name = "typemedia_id")
    private Typemedia typemedia;

    @ManyToOne
    @JoinColumn(name = "module_id")
    private Module module;

}
