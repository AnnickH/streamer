package fr.aelion.streamer.entities;

import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;
import java.util.Date;
import java.util.Set;


@Entity
@Table(name="course") //souvent le nom de l'entité suffit pour définir le nom de la table Course course
//elle peut etre utilisée par le repository
@Getter
@Setter
public class Course {
    @Id // pour jpa ça devient une clé primaire
    @GeneratedValue(strategy = GenerationType.IDENTITY) // ne gère pas la valueur de l'id, la base de donnée le fait
    private int id;

    @Column(name = "title", nullable = false) // spécifie que le title ne peut pas etre null
    //@Column(name = "title", unique = true)
    // on utilise @Columun au cas ou une appelation diffère , a un intérêt si il a une condition particulière
    private String title;
    @CreationTimestamp
    private LocalDate createdAt;
    @UpdateTimestamp
    private LocalDate updatedAt; // spring fait la connexion automatique de camelCase en snake_case
    private String objective;

    @OneToMany(mappedBy = "course") // pour un cours donnée => j'ai plusieurs module
    private Set<Module> modules;

   /* @OneToMany(targetEntity = Module.class) // 1 cours pour plusieurs module Je veux recupérer tous les modules associer à ce cours
    private Set<Module> modules;*/
}
