package com.bacia.quickstart.Service.Implementations;

import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Error.BookNotFoundException;
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
        return Optional.ofNullable(repository.findById(id).orElseThrow(() -> new BookNotFoundException("Cartea nu exista!")));
    }

    @Override
    public List<BookEntity> getAllBooks() {
        return repository.findAll();
    }

    @Override
    public Page<BookEntity> getAllBooks(Pageable pageable) {
        return repository.findAll(pageable);
    }

    @Override
    public Page<BookEntity> getAllBooksByGenre(String gen, Pageable pageable) {
        return repository.findAllByGenre(gen,pageable);
    }

    @Override
    public Page<BookEntity> getAllBooksAfterYear(int year, Pageable pageable) {
        return repository.findAllByYearPublishGreaterThanEqual(year, pageable);
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
