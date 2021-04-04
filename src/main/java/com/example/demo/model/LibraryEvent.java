package com.example.demo.model;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Data
public class LibraryEvent {
    private int libraryEventId;
    private Book book;
}
