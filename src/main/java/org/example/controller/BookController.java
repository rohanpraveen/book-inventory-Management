package org.example.controller;

import org.example.dto.BookRequest;
import org.example.entity.Book;
import org.example.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;



@RestController
@RequestMapping("/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService){
        this.bookService = bookService;
    }



    @PostMapping("/createBook")
    public ResponseEntity<String> createBook(@RequestBody BookRequest bookRequest) {
       try{
           String result = bookService.createBook(bookRequest);
           return new ResponseEntity<>(result, HttpStatus.CREATED);
       } catch (Exception e){
           return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }

    @GetMapping("/getAllBooks")
    public ResponseEntity<List<Book>> getAllBooks() {
        try{
            List<Book> bookList = bookService.getAllBooks();

            return new ResponseEntity<>(bookList, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBook(@PathVariable Long id) {
            try{
                String result = bookService.deleteBook(id);
                return new ResponseEntity<>(result,HttpStatus.OK);
            } catch (Exception e) {
                return new  ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
            }
    }


    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooksByTitle(@RequestParam String title) {
        try {
            List<Book> books = bookService.searchBooksByTitle(title);
            return new ResponseEntity<>(books, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateBook(@PathVariable Long id, @RequestBody BookRequest bookRequest) {
        try {
            String result = bookService.updateBook(id, bookRequest);
            return new ResponseEntity<>(result, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }


    //soft deletion check i am not deleting
    @GetMapping("/admin/deleted-books")
    public ResponseEntity<List<Book>> getDeletedBooks() {
        try {
            List<Book> deletedBooks = bookService.getDeletedBooks();
            return new ResponseEntity<>(deletedBooks, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
