package org.example.service;

import org.example.dto.BookRequest;
import org.example.entity.Book;
import org.example.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    public BookService(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public String createBook(BookRequest request) throws Exception{

        //create book entity from book request(dto)
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getPrice());

        LocalDateTime now = LocalDateTime.now();
        book.setCreatedAt(now);
        book.setCreatedBy("system"); // Ideally from security context
        book.setLastModified(now);
        book.setDeleted(false);

        bookRepository.save(book);

        return "Book has been successfully inserted into database.";
    }

    public List<Book> getAllBooks() throws Exception{
        List<Book> bookList = bookRepository.findAll().stream().filter(book -> !book.isDeleted())
                .toList();


        if(bookList.isEmpty()){
            throw new Exception("No books is present inside the database.");
        }
        return bookList;
    }

    public String deleteBook(Long id ) throws Exception{

        Optional<Book> bookOptional = bookRepository.findById(id);

        if (bookOptional.isPresent()) {

            Book book  = bookOptional.get();

            book.setDeleted(true);
            book.setLastModified(LocalDateTime.now());
            bookRepository.save(book);

            //bookRepository.deleteById(id);
            return "Book has been deleted";
        } else {
            throw new Exception("Book not found with id: " + id);
        }

    }

    public List<Book> getDeletedBooks() throws  Exception {
        List<Book> deltedBooks = bookRepository.findByDeletedTrue();
        if(deltedBooks.isEmpty()){
            throw new Exception("No Deleted books in DB");
        }
        return deltedBooks;
    }
}
