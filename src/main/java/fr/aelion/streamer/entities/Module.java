package fr.aelion.streamer.entities;

import fr.aelion.streamer.services.CourseService;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Set;

@Entity
@Getter
@Setter
public class Module {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int Id;

    @Column(nullable = false)
    private String name;

    private String objective;

    //Foreign KEY
    @ManyToOne
    @JoinColumn(name="course_id")
    private Course course;

    @OneToMany(mappedBy = "module") // pour un cours donnée => j'ai plusieurs module
    private Set<Media> medias;
}
