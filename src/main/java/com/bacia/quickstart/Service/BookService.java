package com.bacia.quickstart.Service;

import com.bacia.quickstart.Domain.Entity.BookEntity;

import java.util.List;
import java.util.Optional;

public interface BookService {
    BookEntity createBook(String id, BookEntity book);
    Optional<BookEntity> getBook(String id);
    List<BookEntity> getAllBooks();
    public boolean exist(String id);
    public void deleteBook(String id);
}
