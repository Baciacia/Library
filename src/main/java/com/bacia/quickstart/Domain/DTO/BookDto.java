package com.bacia.quickstart.Domain.DTO;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;
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
}
