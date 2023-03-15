package fr.aelion.streamer.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import fr.aelion.streamer.entities.Module;
public interface ModuleRepository extends JpaRepository<Module, Integer> {
}
