package fr.aelion.streamer.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class AddCourse {
    @NotBlank(message="Course title cannot be empty")
    @Size(max = 255 , message = "The title contains too many characters")
    private String title;

    private LocalDate createdAt;
    private LocalDate updatedAt;

    private String objective;
}
