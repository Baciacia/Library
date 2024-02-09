package com.bacia.quickstart.Controller;

import com.bacia.quickstart.Domain.DTO.BookDto;
import com.bacia.quickstart.Domain.Entity.BookEntity;
import com.bacia.quickstart.Service.BookService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
@AutoConfigureMockMvc
public class BookControllerIntegrationTest {
    private final MockMvc mockMvc;
    private final BookService service;
    private final ObjectMapper objectMapper;

    @Autowired
    public BookControllerIntegrationTest(MockMvc mockMvc, BookService service) {
        this.mockMvc = mockMvc;
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateBookReturnsHttpStatus201Created() throws Exception {
        BookDto book = BookDto.builder()
                .isbn("3")
                .title("Alice in Wonderland")
                .author(null)
                .build();
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/book/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
    public void testThatCreateBookReturnsCreatedBook() throws Exception {
        BookDto book = BookDto.builder()
                .isbn("3")
                .title("Alice in Wonderland")
                .author(null)
                .build();
        String bookJson = objectMapper.writeValueAsString(book);

        mockMvc.perform(
                MockMvcRequestBuilders.put("/book/" + book.getIsbn())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(bookJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.title").value("Alice in Wonderland")
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.isbn").value("3")
        );
    }

    @Test
    public void testThatGetAllBooksReturnsHttpStatus200() throws Exception {

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAllBooksReturnsListOfBooks() throws Exception {
        BookEntity book = BookEntity.builder()
                .isbn("3")
                .title("Alice in Wonderland")
                .author(null)
                .build();
        service.createBook(book.getIsbn(), book);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/books")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].title").value(book.getTitle())
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[1].isbn").value(book.getIsbn())
        );
    }

}
