package com.java.toastcanvasvn_test.service;

import com.java.toastcanvasvn_test.dto.BookCreateDTO;
import com.java.toastcanvasvn_test.dto.BookResponseDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface BookService {
    BookResponseDTO saveBook(BookCreateDTO bookDTO);
    BookResponseDTO getBookById(Long id);
    Page<BookResponseDTO> getAllBooks(Long authorId, String publishedDate, String title, Double minPrice, Double maxPrice, Pageable pageable);
    BookResponseDTO updateBook(Long id, BookCreateDTO bookDTO);
    void deleteBook(Long id);
}