package com.java.toastcanvasvn_test.service.impl;

import com.java.toastcanvasvn_test.dto.BookCreateDTO;
import com.java.toastcanvasvn_test.dto.BookResponseDTO;
import com.java.toastcanvasvn_test.exception.ResourceNotFoundException;
import com.java.toastcanvasvn_test.model.Author;
import com.java.toastcanvasvn_test.model.Book;
import com.java.toastcanvasvn_test.repository.AuthorRepository;
import com.java.toastcanvasvn_test.repository.BookRepository;
import com.java.toastcanvasvn_test.service.BookService;
import com.java.toastcanvasvn_test.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public BookResponseDTO saveBook(BookCreateDTO bookDTO) {
        Optional<Book> existingBook = bookRepository.findByIsbn(bookDTO.getIsbn());
        if (existingBook.isPresent()) {
            throw new IllegalArgumentException("A book with the same ISBN already exists.");
        }
        
        Long authorId = bookDTO.getAuthorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
        Book book = EntityDTOConverter.convertToEntity(bookDTO, author);
        Book savedBook = bookRepository.save(book);
        return EntityDTOConverter.convertToResponseDTO(savedBook);
    }

    @Override
    public BookResponseDTO getBookById(Long id) {
        Book book = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        return EntityDTOConverter.convertToResponseDTO(book);
    }

    @Override
    public Page<BookResponseDTO> getAllBooks(Long authorId, String publishedDate, String title, Double minPrice, Double maxPrice, Pageable pageable) {
        int pageNumber = pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : 0;
        Pageable adjustedPageable = PageRequest.of(pageNumber, pageable.getPageSize(), pageable.getSort());

        Page<Book> books = bookRepository.findAll(adjustedPageable);
        return books.map(EntityDTOConverter::convertToResponseDTO);
    }

    @Override
    public BookResponseDTO updateBook(Long id, BookCreateDTO bookDTO) {
        Book existingBook = bookRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Book not found with id: " + id));
        
        existingBook.setTitle(bookDTO.getTitle());
        existingBook.setPublishedDate(bookDTO.getPublishedDate());
        existingBook.setPrice(bookDTO.getPrice());

        Long authorId = bookDTO.getAuthorId();
        Author author = authorRepository.findById(authorId)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + authorId));
        existingBook.setAuthor(author);

        Book updatedBook = bookRepository.save(existingBook);
        return EntityDTOConverter.convertToResponseDTO(updatedBook);
    }

    @Override
    public void deleteBook(Long id) {
        if (!bookRepository.existsById(id)) {
            throw new ResourceNotFoundException("Book not found with id: " + id);
        }
        bookRepository.deleteById(id);
    }
}