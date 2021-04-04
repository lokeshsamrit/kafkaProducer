package com.example.demo.controller;

import com.example.demo.model.LibraryEvent;
import com.example.demo.service.LibraryEventProducer;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LibraryEventController {

    @Autowired
    LibraryEventProducer libraryEventProducer;

    @PostMapping("/sendToKafka")
    public ResponseEntity<LibraryEvent> sendKafkaMessage(@RequestBody LibraryEvent libraryEvent) throws JsonProcessingException {

        return libraryEventProducer.sendLibraryEvent(libraryEvent);

    }

}
