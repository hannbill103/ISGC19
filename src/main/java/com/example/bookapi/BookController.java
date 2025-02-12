package com.example.bookapi;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

//denna klass är en rest kontroller för att kunna hantera http förfrågningar
@RestController
@RequestMapping("/books") // bas urlen
public class BookController {
    private final BookService bookService;

    // onstruktor
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    // hämtar en lista med alla böcker
    @GetMapping
    public List<Book> getAllBooks() {
        return bookService.getAllBooks();
    }

    // hämtar en bok baserat på dess unika ID
    @GetMapping("/{id}")
    public ResponseEntity<Book> getBookById(@PathVariable Long id) {
        Optional<Book> book = bookService.getBookById(id);
        return book.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // hämtar en lista med böcker som tillhör en viss kategori
    @GetMapping("/category/{category}")
    public List<Book> getBooksByCategory(@PathVariable Category category) {
        return bookService.getBooksByCategory(category);
    }

    // skapar en ny bok med inskickad data
    @PostMapping
    public ResponseEntity<?> createBook(@RequestBody Book book) {
        // kollar att titel inte är tom
        if (book.getTitle() == null || book.getTitle().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("titel kan ej vara tom");
        }

        // kollar att författare inte är tom
        if (book.getAuthor() == null || book.getAuthor().trim().isEmpty()) {
            return ResponseEntity.badRequest().body("författare kan ej vara tom");
        }

        // Validerar att beskrivningen inte överstiger 500 tecken
        if (book.getDescription() != null && book.getDescription().length() > 500) {
            return ResponseEntity.badRequest().body("beskrivningen får ej vara mer än 500 ord");
        }

        // om valideringen är godkänd så sparas boken i databasen
        Book createdBook = bookService.saveBook(book);
        return ResponseEntity.status(201).body(createdBook);
    }

    // raderar en bok baserat på dess id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}

