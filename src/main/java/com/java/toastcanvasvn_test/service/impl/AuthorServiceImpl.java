package com.java.toastcanvasvn_test.service.impl;

import com.java.toastcanvasvn_test.dto.AuthorDTO;
import com.java.toastcanvasvn_test.exception.ResourceNotFoundException;
import com.java.toastcanvasvn_test.model.Author;
import com.java.toastcanvasvn_test.repository.AuthorRepository;
import com.java.toastcanvasvn_test.service.AuthorService;
import com.java.toastcanvasvn_test.util.EntityDTOConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    public AuthorDTO saveAuthor(AuthorDTO authorDTO) {
        Author author = EntityDTOConverter.convertToEntity(authorDTO);
        Author savedAuthor = authorRepository.save(author);
        return EntityDTOConverter.convertToDTO(savedAuthor);
    }

    @Override
    public AuthorDTO getAuthorById(Long id) {
        Author author = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        return EntityDTOConverter.convertToDTO(author);
    }

    @Override
    public Page<AuthorDTO> getAllAuthors(Pageable pageable) {
        int pageNumber = pageable.getPageNumber() > 0 ? pageable.getPageNumber() - 1 : 0;
        Pageable adjustedPageable = PageRequest.of(pageNumber, pageable.getPageSize(), pageable.getSort());

        Page<Author> authors = authorRepository.findAll(adjustedPageable);
        return authors.map(EntityDTOConverter::convertToDTO);
    }

    @Override
    public AuthorDTO updateAuthor(Long id, AuthorDTO authorDTO) {
        Author existingAuthor = authorRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Author not found with id: " + id));
        existingAuthor.setName(authorDTO.getName());
        existingAuthor.setNationality(authorDTO.getNationality());
        Author updatedAuthor = authorRepository.save(existingAuthor);
        return EntityDTOConverter.convertToDTO(updatedAuthor);
    }

    @Override
    public void deleteAuthor(Long id) {
        if (!authorRepository.existsById(id)) {
            throw new ResourceNotFoundException("Author not found with id: " + id);
        }
        authorRepository.deleteById(id);
    }
}