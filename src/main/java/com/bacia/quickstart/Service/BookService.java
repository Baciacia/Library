package com.bacia.quickstart.Service;

import com.bacia.quickstart.Domain.Entity.BookEntity;

public interface BookService {
    BookEntity createBook(String id, BookEntity book);
}
