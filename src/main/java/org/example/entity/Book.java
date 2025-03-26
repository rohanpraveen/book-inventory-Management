 package org.example.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String genre;

     private BookStatus availability;

    public Book(String title, String author, String genre ,BookStatus availability ){
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.availability = availability;
    }

    // Validation methods
    public boolean isValid() {
        return title != null && !title.trim().isEmpty() &&
                author != null && !author.trim().isEmpty() &&
                availability != null;
    }


    // internal tracking fields
    private LocalDateTime createdAt;
    private String createdBy;
    private LocalDateTime lastModified;
    private boolean deleted;
}
