package com.java.toastcanvasvn_test.util;

import com.java.toastcanvasvn_test.dto.AuthorDTO;
import com.java.toastcanvasvn_test.dto.BookCreateDTO;
import com.java.toastcanvasvn_test.dto.BookResponseDTO;
import com.java.toastcanvasvn_test.model.Author;
import com.java.toastcanvasvn_test.model.Book;
import com.java.toastcanvasvn_test.repository.AuthorRepository;
import com.java.toastcanvasvn_test.exception.ResourceNotFoundException;

public class EntityDTOConverter {

    public static BookResponseDTO convertToResponseDTO(Book book) {
        BookResponseDTO bookDTO = new BookResponseDTO();
        bookDTO.setId(book.getId());
        bookDTO.setTitle(book.getTitle());
        if (book.getAuthor() != null) {
            bookDTO.setAuthor(convertToDTO(book.getAuthor()));
        }
        bookDTO.setPublishedDate(book.getPublishedDate());
        bookDTO.setIsbn(book.getIsbn());
        bookDTO.setPrice(book.getPrice());
        return bookDTO;
    }

    public static Book convertToEntity(BookCreateDTO bookDTO, Author author) {
        Book book = new Book();
        book.setId(bookDTO.getId());
        book.setTitle(bookDTO.getTitle());
        book.setPublishedDate(bookDTO.getPublishedDate());
        book.setIsbn(bookDTO.getIsbn());
        book.setPrice(bookDTO.getPrice());
        book.setAuthor(author);
        return book;
    }

    public static AuthorDTO convertToDTO(Author author) {
        AuthorDTO authorDTO = new AuthorDTO();
        authorDTO.setId(author.getId());
        authorDTO.setName(author.getName());
        authorDTO.setNationality(author.getNationality());
        return authorDTO;
    }

    public static Author convertToEntity(AuthorDTO authorDTO) {
        Author author = new Author();
        author.setId(authorDTO.getId());
        author.setName(authorDTO.getName());
        author.setNationality(authorDTO.getNationality());
        return author;
    }
}