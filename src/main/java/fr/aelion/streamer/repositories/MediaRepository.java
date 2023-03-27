package fr.aelion.streamer.repositories;

import fr.aelion.streamer.dto.SimpleMediaProjection;
import fr.aelion.streamer.dto.SimpleStudentProjection;
import fr.aelion.streamer.entities.Course;
import fr.aelion.streamer.entities.Media;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Integer> {
    @Query("SELECT m.id id, m.title title  FROM Media m")
    public List<SimpleMediaProjection> getSimpleMedias();
}
