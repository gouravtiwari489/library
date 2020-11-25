package com.hexad.library.resources;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
public class BookControllerTest {

    private MockMvc mockMvc;

    @InjectMocks
    private BookController bookController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(BookController.class).build();
    }

    @Test
    public void shouldAddBooksForValidInput() throws Exception {
        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"bookName\":\"book1\"}")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status()
                .isCreated());
    }

    @Test
    public void shouldGetBookById() throws Exception
    {
        mockMvc.perform(get("/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
                //.andExpect(MockMvcResultMatchers.jsonPath("$.id")
               // .value(1));
    }

    @Test
    public void shouldGetAllBooks() throws Exception
    {
        mockMvc.perform(get("/books")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        //.andExpect(MockMvcResultMatchers.jsonPath("$.id")
        // .value(1));
    }

    @Test
    public void shouldDeleteBook() throws Exception {
        mockMvc.perform(delete("/books/{id}", 1)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}
