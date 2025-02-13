package com.java.toastcanvasvn_test.dto;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

@Data
public class BookResponseDTO {
    private Long id;
    private String title;
    private AuthorDTO author; // Full AuthorDTO for response
    private Date publishedDate;
    private String isbn;
    private BigDecimal price;
} 