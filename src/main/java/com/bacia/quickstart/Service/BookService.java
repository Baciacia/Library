package com.bacia.quickstart.Service;

import com.bacia.quickstart.Domain.Entity.BookEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(String id, BookEntity book);
    Optional<BookEntity> getBook(String id);
    List<BookEntity> getAllBooks();
    Page<BookEntity> getAllBooks(Pageable pageable);
    public boolean exist(String id);
    public void deleteBook(String id);
}
