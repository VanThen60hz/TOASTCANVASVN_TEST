package com.java.toastcanvasvn_test.service;

import com.java.toastcanvasvn_test.dto.AuthorDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface AuthorService {
    AuthorDTO saveAuthor(AuthorDTO authorDTO);
    AuthorDTO getAuthorById(Long id);
    Page<AuthorDTO> getAllAuthors(Pageable pageable);
    AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO);
    void deleteAuthor(Long id);
}