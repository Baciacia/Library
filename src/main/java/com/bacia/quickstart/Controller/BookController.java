package com.bacia.quickstart.Controller;

import com.bacia.quickstart.Domain.DTO.BookDto;
import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Mappers.Mapper;
import com.bacia.quickstart.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;


@RestController
public class BookController {
    private final BookService service;
    private final Mapper<BookEntity, BookDto> mapper;
    @Autowired
    public BookController(BookService service, Mapper<BookEntity, BookDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @GetMapping("/book/{id}")
    public ResponseEntity<BookDto> getBook(@PathVariable String id) {
        Optional<BookEntity> returnedBook = service.getBook(id);
        return returnedBook.map(bookEntity -> {
            BookDto responseDto = mapper.mapEntityToDto(bookEntity);
            return new ResponseEntity<>(responseDto, HttpStatus.OK);
        }).orElse(
                new ResponseEntity<>(HttpStatus.NOT_FOUND)
        );
    }
    @GetMapping("/books/year/{year}")
    public Page<BookDto> getAllBooksAfterYear(@PathVariable int year, Pageable pageable){

      Page<BookEntity> bookList = service.getAllBooksAfterYear(year, pageable);
      return bookList.map(mapper::mapEntityToDto);
    }
    @GetMapping("/books/genre/{gen}")
    public Page<BookDto> getAllGenBooks(@PathVariable String gen, Pageable pageable){
        Page<BookEntity> books = service.getAllBooksByGenre(gen, pageable);
        return books.map(mapper::mapEntityToDto);
    }
    @GetMapping("/books")
    public Page<BookDto> getAllBooks(Pageable pageable){
        Page<BookEntity> allBooksEntity = service.getAllBooks(pageable);
        return allBooksEntity.map(mapper::mapEntityToDto);
    }
    @PutMapping("/book/{id}")
    public ResponseEntity<BookDto> createUpdateBook(@PathVariable String id, @RequestBody BookDto bookDto ){
        BookEntity book = mapper.mapDtoToEntity(bookDto);
        boolean exist = service.exist(id);
        BookEntity savedBook = service.createBook(id, book);
        if(!exist){
            return new ResponseEntity<>(mapper.mapEntityToDto(savedBook), HttpStatus.CREATED);
        }
        else{
            return new ResponseEntity<>(mapper.mapEntityToDto(savedBook), HttpStatus.OK);
        }

    }
}
