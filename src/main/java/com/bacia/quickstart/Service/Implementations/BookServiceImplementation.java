package com.bacia.quickstart.Service.Implementations;

import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Repository.BookRepository;
import com.bacia.quickstart.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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

    @Override
    public Optional<BookEntity> getBook(String id) {
        return repository.findById(id);
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Page<BookEntity> getAllBooks(Pageable pageable) {
        return null;
    }

    @Override
    public boolean exist(String id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteBook(String id) {
        repository.deleteById(id);
    }
}
