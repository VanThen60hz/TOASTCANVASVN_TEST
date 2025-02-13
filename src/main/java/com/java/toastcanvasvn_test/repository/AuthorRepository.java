package com.java.toastcanvasvn_test.repository;

import com.java.toastcanvasvn_test.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AuthorRepository extends JpaRepository<Author, Long> {
}