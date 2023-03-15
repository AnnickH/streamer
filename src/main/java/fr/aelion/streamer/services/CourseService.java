package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.FullCourseDto;
import fr.aelion.streamer.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseService<Course> {

    List<FullCourseDto> findAll();
}
