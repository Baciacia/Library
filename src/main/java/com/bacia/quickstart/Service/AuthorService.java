package com.bacia.quickstart.Service;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;

import java.util.List;

public interface AuthorService {
    public AuthorEntity createAuthor(AuthorEntity author);
    public List<AuthorEntity> getAllAuthors();
}
