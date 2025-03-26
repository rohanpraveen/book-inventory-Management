package org.example.service;

import org.example.dto.BookRequest;
import org.example.entity.Book;
import org.example.entity.BookStatus;
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

        if (request.getTitle() == null || request.getTitle().trim().isEmpty()) {
            throw new Exception("Title cannot be empty");
        }
        if (request.getAuthor() == null || request.getAuthor().trim().isEmpty()) {
            throw new Exception("Author cannot be empty");
        }
        //create book entity from book request(dto)
        Book book = new Book(request.getTitle(), request.getAuthor(), request.getGenre(),request.getAvailability() != null ? request.getAvailability() : BookStatus.AVAILABLE);

        LocalDateTime now = LocalDateTime.now();
        book.setCreatedAt(now);
        book.setCreatedBy("system");
        book.setLastModified(now);
        book.setDeleted(false);

        bookRepository.save(book);

        return "Book has been successfully inserted into database.";
    }

    public List<Book> getAllBooks() throws Exception{
        List<Book> bookList = bookRepository.findByDeletedFalse();

        if(bookList.isEmpty()){
            throw new Exception("No books is present inside the database.");
        }
        return bookList;
    }

    public Book findBookById(Long id) throws Exception {
        Optional<Book> bookOptional = bookRepository.findByIdAndDeletedFalse(id);

        if (bookOptional.isPresent()) {
            return bookOptional.get();
        } else {
            throw new Exception("Book not found with id: " + id);
        }
    }

    public String updateBook(Long id, BookRequest request) throws Exception {
        Optional<Book> bookOptional = bookRepository.findByIdAndDeletedFalse(id);

        if (bookOptional.isPresent()) {
            Book book = bookOptional.get();

            // Update book details if provided
            if (request.getTitle() != null) {
                book.setTitle(request.getTitle());
            }
            if (request.getAuthor() != null) {
                book.setAuthor(request.getAuthor());
            }
            if (request.getGenre() != null) {
                book.setGenre(request.getGenre());
            }
            if (request.getAvailability() != null) {
                book.setAvailability(request.getAvailability());
            }

            book.setLastModified(LocalDateTime.now());
            bookRepository.save(book);

            return "Book has been updated successfully.";
        } else {
            throw new Exception("Book not found with id: " + id);
        }
    }

    public String deleteBook(Long id ) throws Exception{

        Optional<Book> bookOptional = bookRepository.findByIdAndDeletedFalse(id);

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

    public List<Book> searchBooksByTitle(String title) throws Exception {
        List<Book> books = bookRepository.findByTitleContainingIgnoreCaseAndDeletedFalse(title);

        if(books.isEmpty()){
            throw new Exception("No books found with title: " + title);
        }
        return books;
    }

    public List<Book> getDeletedBooks() throws  Exception {
        List<Book> deletedBooks = bookRepository.findByDeletedTrue();
        if(deletedBooks.isEmpty()){
            throw new Exception("No Deleted books in DB");
        }
        return deletedBooks;
    }
}
