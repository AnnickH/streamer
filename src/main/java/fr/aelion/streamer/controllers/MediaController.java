package fr.aelion.streamer.controllers;

import fr.aelion.streamer.dto.FullMediaDto;


import fr.aelion.streamer.services.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/v1/medias")
public class MediaController {
    @Autowired
    private MediaService service;

    @GetMapping
    @CrossOrigin
    public List<FullMediaDto> findAll(){
        return service.findAll();
    }
}
