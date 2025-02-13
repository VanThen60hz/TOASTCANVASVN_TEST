package com.java.toastcanvasvn_test.controller;

import com.java.toastcanvasvn_test.dto.BookCreateDTO;
import com.java.toastcanvasvn_test.dto.BookResponseDTO;
import com.java.toastcanvasvn_test.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public BookResponseDTO createBook(@RequestBody BookCreateDTO bookDTO) {
        return bookService.saveBook(bookDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookResponseDTO> getBookById(@PathVariable Long id) {
        BookResponseDTO bookDTO = bookService.getBookById(id);
        return ResponseEntity.ok(bookDTO);
    }

    @GetMapping
    public Page<BookResponseDTO> getAllBooks(
            @RequestParam(required = false) Long authorId,
            @RequestParam(required = false) String publishedDate,
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            Pageable pageable) {
        return bookService.getAllBooks(authorId, publishedDate, title, minPrice, maxPrice, pageable);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<BookResponseDTO> updateBook(@PathVariable Long id, @RequestBody BookCreateDTO bookDTO) {
        BookResponseDTO updatedBook = bookService.updateBook(id, bookDTO);
        return ResponseEntity.ok(updatedBook);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}