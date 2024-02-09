package com.bacia.quickstart.Controller;

import com.bacia.quickstart.Domain.DTO.AuthorDto;
import com.bacia.quickstart.Domain.Entity.AuthorEntity;
import com.bacia.quickstart.Mappers.Mapper;
import com.bacia.quickstart.Mappers.impl.AuthorMapperImpl;
import com.bacia.quickstart.Service.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
public class AuthorController {
    private final AuthorService service;
    private final Mapper<AuthorEntity, AuthorDto> mapper;

    @Autowired
    public AuthorController(AuthorService service, Mapper<AuthorEntity, AuthorDto> mapper) {
        this.service = service;
        this.mapper = mapper;
    }

    @PostMapping("/author")
    public ResponseEntity<AuthorDto> createAuthor(@RequestBody AuthorDto author) {
        AuthorEntity authorEntity = mapper.mapDtoToEntity(author);
        AuthorEntity savedAuthorEntity = service.createAuthor(authorEntity);
        return new ResponseEntity<>(mapper.mapEntityToDto(savedAuthorEntity), HttpStatus.CREATED);
    }

    @GetMapping("/author/{id}")
    public ResponseEntity<AuthorDto> getAuthor(@PathVariable Long id) {
        Optional<AuthorEntity> returnAuthor = service.getAuthor(id);
        return returnAuthor.map(authorEntity -> {
            AuthorDto authorDto = mapper.mapEntityToDto(authorEntity);
            return new ResponseEntity<>(authorDto, HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("/authors")
    public List<AuthorDto> getAllAuthors() {
        List<AuthorEntity> allAuthorsEntity = service.getAllAuthors();
        return allAuthorsEntity.stream().map(mapper::mapEntityToDto).collect(Collectors.toList());
    }

    @PutMapping("/author/{id}")
    public ResponseEntity<AuthorDto> fullUpdateAuthor(@PathVariable Long id, @RequestBody AuthorDto authorDto) {
        if (!service.isExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } else {
            authorDto.setId(id);
            return new ResponseEntity<>(
                    mapper.mapEntityToDto(
                            service.createAuthor(
                                    mapper.mapDtoToEntity(
                                            authorDto))), HttpStatus.OK);

        }

    }
    @DeleteMapping("/author/{id}")
    public ResponseEntity deleteAuthor (@PathVariable Long id){
        service.deleteAuthor(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
}
