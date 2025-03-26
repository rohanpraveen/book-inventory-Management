
import org.example.dto.BookRequest;
import org.example.entity.Book;
import org.example.entity.BookStatus;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

    class BookServiceTest {

        @Mock
        private BookRepository bookRepository;

        @InjectMocks
        private BookService bookService;

        @BeforeEach
        void setUp() {
            MockitoAnnotations.openMocks(this);
        }

        @Test
        void createBook_Success() throws Exception {
            // Arrange
            BookRequest request = new BookRequest("Test Book", "Test Author", "Fiction", BookStatus.AVAILABLE);

            // Act
            String result = bookService.createBook(request);

            // Assert
            assertEquals("Book has been successfully inserted into database.", result);
            verify(bookRepository, times(1)).save(any(Book.class));
        }

        @Test
        void createBook_EmptyTitle_ShouldThrowException() {
            // Arrange
            BookRequest request = new BookRequest("", "Test Author", "Fiction", BookStatus.AVAILABLE);

            // Assert
            assertThrows(Exception.class, () -> {
                bookService.createBook(request);
            });
        }

        @Test
        void getAllBooks_Success() throws Exception {
            // Arrange
            List<Book> mockBooks = Arrays.asList(
                    new Book("Book1", "Author1", "Genre1", BookStatus.AVAILABLE),
                    new Book("Book2", "Author2", "Genre2", BookStatus.NOT_AVAILABLE)
            );
            when(bookRepository.findByDeletedFalse()).thenReturn(mockBooks);

            // Act
            List<Book> books = bookService.getAllBooks();

            // Assert
            assertFalse(books.isEmpty());
            assertEquals(2, books.size());
        }

        @Test
        void findBookById_Success() throws Exception {
            // Arrange
            Book mockBook = new Book("Test Book", "Test Author", "Fiction", BookStatus.AVAILABLE);
            mockBook.setId(1L);
            when(bookRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(mockBook));

            // Act
            Book foundBook = bookService.findBookById(1L);

            // Assert
            assertNotNull(foundBook);
            assertEquals("Test Book", foundBook.getTitle());
        }

        @Test
        void deleteBook_Success() throws Exception {
            // Arrange
            Book mockBook = new Book("Test Book", "Test Author", "Fiction", BookStatus.AVAILABLE);
            mockBook.setId(1L);
            when(bookRepository.findByIdAndDeletedFalse(1L)).thenReturn(Optional.of(mockBook));

            // Act
            String result = bookService.deleteBook(1L);

            // Assert
            assertEquals("Book has been deleted", result);
            assertTrue(mockBook.isDeleted());
            verify(bookRepository, times(1)).save(mockBook);
        }
    }

