package com.bacia.quickstart.Controller;

import com.bacia.quickstart.Domain.Entity.AuthorEntity;
import com.bacia.quickstart.Service.AuthorService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
public class AuthorControllerIntegrationTest {
    private MockMvc mockMvc;
    private AuthorService service;
    private ObjectMapper objectMapper;

    @Autowired
    public AuthorControllerIntegrationTest(MockMvc mockMvc, AuthorService service) {
        this.mockMvc = mockMvc;
        this.service = service;
        this.objectMapper = new ObjectMapper();
    }

    @Test
    public void testThatCreateAuthorSuccessfullyReturnHttp201Created() throws Exception {

        AuthorEntity testAuthor = AuthorEntity.builder()
                                .firstName("Baciu")
                                .lastName("Alex")
                                .age(22)
                                .build();
        testAuthor.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthor);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.status().isCreated()
        );
    }

    @Test
        public void testThatCreateAuthorSuccessfullyReturnSavedAuthor() throws Exception {

        AuthorEntity testAuthor = AuthorEntity.builder()
                .firstName("Baciu")
                .lastName("Alex")
                .age(22)
                .build();
        testAuthor.setId(null);
        String authorJson = objectMapper.writeValueAsString(testAuthor);

        mockMvc.perform(
                MockMvcRequestBuilders.post("/author")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(authorJson)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Baciu")
        );
    }
    @Test
    public void testThatGetAllAuthorsReturnsHttpStatus200() throws Exception {
        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }
    @Test
    public void testThatGetAllAuthorsReturnsListOfAuthors() throws Exception {

        AuthorEntity testAuthor = AuthorEntity.builder()
                .firstName("Baciu")
                .lastName("Alex")
                .age(22)
                .build();
        service.createAuthor(testAuthor);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/authors")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$[0].firstName").value("Baciu")
        );
    }
    @Test
    public void testThatGetAuthorReturnsHttpStatus200WhenExists() throws Exception {
        AuthorEntity testAuthor = AuthorEntity.builder()
                .firstName("Baciu")
                .lastName("Alex")
                .age(22)
                .build();
        service.createAuthor(testAuthor);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/author/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isOk()
        );
    }

    @Test
    public void testThatGetAuthorReturnsErrorWhenAuthorNotExists() throws Exception {
        AuthorEntity testAuthor = AuthorEntity.builder()
                .firstName("Baciu")
                .lastName("Alex")
                .age(22)
                .build();
        service.createAuthor(testAuthor);
        mockMvc.perform(
                MockMvcRequestBuilders.get("/author/99")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.status().isNotFound()
        );
    }

    @Test
    public void testThatGetAuthorReturnsAuthorWhenAuthorExist() throws Exception {

        AuthorEntity testAuthor = AuthorEntity.builder()
                .firstName("Baciu")
                .lastName("Alex")
                .age(22)
                .build();
        service.createAuthor(testAuthor);

        mockMvc.perform(
                MockMvcRequestBuilders.get("/author/1")
                        .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.id").isNumber()
        ).andExpect(
                MockMvcResultMatchers.jsonPath("$.firstName").value("Baciu")
        );
    }
}
