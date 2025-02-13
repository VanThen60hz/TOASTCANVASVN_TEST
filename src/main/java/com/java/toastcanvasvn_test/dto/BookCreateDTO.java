package com.java.toastcanvasvn_test.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookCreateDTO {
    private Long id;
    private String title;
    private Long authorId; // Only authorId for create/update
    private Date publishedDate;
    private String isbn;
    private BigDecimal price;
} 