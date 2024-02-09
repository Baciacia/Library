package com.bacia.quickstart.Service;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;

import java.util.List;
import java.util.Optional;

public interface AuthorService {
    public AuthorEntity createAuthor(AuthorEntity author);
    public Optional<AuthorEntity> getAuthor(Long id);
    public List<AuthorEntity> getAllAuthors();
    public Boolean isExists(Long id);
    public void deleteAuthor(Long id);
}
