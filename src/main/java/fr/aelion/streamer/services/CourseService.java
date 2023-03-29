package fr.aelion.streamer.services;

import fr.aelion.streamer.dto.FullCourseDto;

import java.util.List;

public interface CourseService<Course> {

    List<FullCourseDto> findAll();

}
