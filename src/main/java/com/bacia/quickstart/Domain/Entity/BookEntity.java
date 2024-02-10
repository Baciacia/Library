package com.bacia.quickstart.Domain.Entity;

import jakarta.persistence.*;
import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "books")
public class BookEntity {
    @Id
    private String isbn;
    private String title;
    private String genre;
    private int numberOfExemplars;
    private int yearPublish;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;


}
