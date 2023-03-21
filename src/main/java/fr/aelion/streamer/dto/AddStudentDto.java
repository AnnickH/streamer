package fr.aelion.streamer.dto;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AddStudentDto {
    @NotBlank (message = "Lastname cannot be null")
    private String lastName;

    private String firstName;

    @NotNull
    @Email
    private String email;

    private String phoneNumber;

    @NotBlank
    @Size(min = 8, message = "Login must have at least 8 chars")
    private String login;

    @NotBlank
    @Size(min = 8, message = "Password must have at least 8 chars") //@Min(8) //minimum 8 caractère
    //@Pattern(regexp = "/^(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$/")
    private String password;
}
