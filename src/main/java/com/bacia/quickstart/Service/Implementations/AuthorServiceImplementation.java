package com.bacia.quickstart.Service.Implementations;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;
import com.bacia.quickstart.Repository.AuthorRepository;
import com.bacia.quickstart.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthorServiceImplementation implements AuthorService {
    private final AuthorRepository repository;
    @Autowired
    public AuthorServiceImplementation(AuthorRepository repository) {
        this.repository = repository;
    }
    @Override
    public AuthorEntity createAuthor(AuthorEntity author) {
        return repository.save(author);
    }
}
