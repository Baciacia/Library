package com.bacia.quickstart.Service.Implementations;

import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Repository.BookRepository;
import com.bacia.quickstart.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BookServiceImplementation implements BookService {
    private final BookRepository repository;

    @Autowired
    public BookServiceImplementation(BookRepository repository) {
        this.repository = repository;
    }
    @Override
    public BookEntity createBook(String id, BookEntity book) {
        book.setIsbn(id);
        return repository.save(book);
    }
}
