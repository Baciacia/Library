package com.bacia.quickstart.Controller;

import com.bacia.quickstart.Domain.DTO.BookDto;
import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Mappers.Mapper;
import com.bacia.quickstart.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class BookController {
    private final BookService service;
    private final Mapper<BookEntity, BookDto> mapper;
    @Autowired
    public BookController(BookService service, Mapper<BookEntity, BookDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PutMapping("/book/{id}")
    public ResponseEntity<BookDto> createBook(@PathVariable String id, @RequestBody BookDto bookDto ){
        BookEntity book = mapper.mapDtoToEntity(bookDto);
        BookEntity savedBook = service.createBook(id, book);
        return new ResponseEntity<>(mapper.mapEntityToDto(savedBook), HttpStatus.CREATED);
    }
}
