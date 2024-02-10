package com.bacia.quickstart.Domain.DTO;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Getter
@Setter
public class BookDto {
    private String isbn;
    private String title;
    private AuthorDto author;
    private String genre;
    private int numberOfExemplars;
    private int yearPublish;
}
