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

    @Column(nullable = false)
    private String title;

    @Column
    private String summary;

    @Column
    private int duration;

    @Column
    private LocalDate  createdAt;

    @Column
    private String url;

    @ManyToOne
    @JoinColumn(name = "typemedia_id")
    private Typemedia typemedia;

    @ManyToOne
    @JoinColumn(name = "module_id", nullable = true)
    private Module module;

}
