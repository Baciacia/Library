package com.bacia.quickstart.Domain.Entity;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;
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
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "author_id")
    private AuthorEntity author;
}
