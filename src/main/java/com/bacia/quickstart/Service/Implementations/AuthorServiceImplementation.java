package com.bacia.quickstart.Service.Implementations;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;
import com.bacia.quickstart.Repository.AuthorRepository;
import com.bacia.quickstart.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    @Override
    public Optional<AuthorEntity> getAuthor(Long id) {
        return repository.findById(id);
    }
    @Override
    public List<AuthorEntity> getAllAuthors() {
        return repository.findAll();
    }

    @Override
    public Page<AuthorEntity> getAllAuthors(Pageable pageable) {
        return null;
    }

    @Override
    public Boolean isExists(Long id) {
        return repository.existsById(id);
    }

    @Override
    public void deleteAuthor(Long id) {
        repository.deleteById(id);
    }
}
