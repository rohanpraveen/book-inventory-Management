package org.example.repository;

import org.example.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findByDeletedTrue();
    List<Book> findByDeletedFalse();
    List<Book> findByTitleContainingIgnoreCaseAndDeletedFalse(String title);
    Optional<Book> findByIdAndDeletedFalse(Long id);


}
