package fr.aelion.streamer.services;


import fr.aelion.streamer.dto.AddCourse;
import fr.aelion.streamer.dto.FullMediaDto;
import fr.aelion.streamer.dto.SimpleMediaProjection;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Media;
import fr.aelion.streamer.entities.Student;
import fr.aelion.streamer.repositories.MediaRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MediaService {
    @Autowired
    private MediaRepository repository;

    @Autowired
    private ModelMapper modelMapper;

    public List<SimpleMediaProjection> getRepository() {
        return repository.getSimpleMedias();
    }

    public List<Media> findAll() {
        List<Media> medias = repository.findAll();
        return medias;
    }
}
