package fr.aelion.streamer.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name="student")
@Getter
@Setter
public class Student {
    @Id //détermine la clé primaire d'une entité
    @GeneratedValue(strategy = GenerationType.IDENTITY) //cette colonne sera une identity avec un valeur pré défini
    private int id;

    private String lastName;

    private String firstName;

    private String email;

    private String phoneNumber;

    private String login;

    private String password;

}
