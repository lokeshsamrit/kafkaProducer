package com.example.demo.service;

import com.example.demo.model.LibraryEvent;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;
@Service
public class LibraryEventProducer {

Logger logger = LoggerFactory.getLogger(LibraryEventProducer.class);

    @Autowired
    private KafkaTemplate kafkaTemplate;
    
    @Autowired
    ObjectMapper objectMapper;

    public ResponseEntity<LibraryEvent> sendLibraryEvent(LibraryEvent libraryEvent) throws JsonProcessingException {
        String message = objectMapper.writeValueAsString(libraryEvent);
        //kafkaTemplate.send("library-event-topic",message);
        ListenableFuture listenableFuture = kafkaTemplate.sendDefault("library-event-topic",message);
        listenableFuture.addCallback(new ListenableFutureCallback() {
            @Override
            public void onFailure(Throwable throwable) {
                logger.error("Unable to send essage");
            }

            @Override
            public void onSuccess(Object o) {
                logger.info("message sent successfully");
            }
        });
        return ResponseEntity.status(HttpStatus.CREATED).body(libraryEvent);
    }
}
