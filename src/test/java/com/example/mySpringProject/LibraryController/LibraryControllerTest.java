package com.example.mySpringProject.LibraryController;

import org.apache.tomcat.jni.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;

import java.net.http.HttpResponse;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(LibraryController.class)
class LibraryControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private LibraryController libraryController;

    @Test
    void addBookandRemoveBookSuccess() {
        String title1 = "Hello";
        String content1 = "World";
        try {
            // Put book into library
            MockHttpServletResponse postResponseAfterOneBookAdded  = mvc.perform(
                    post("/library/add?title="+title1+"&content="+content1))
                    .andReturn().getResponse();
            assertThat(postResponseAfterOneBookAdded.getStatus()).isEqualTo(HttpStatus.OK.value());
            // Check that book is within library
            MockHttpServletResponse getResponseAfterOneBookAdded = mvc.perform(get("/library/getBook?title="+title1))
                    .andExpect(status().isOk())
                    .andReturn().getResponse();
            assertThat(getResponseAfterOneBookAdded.getContentAsString()).isEqualTo(content1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    void removeBookFailure(){

    }
    @Test
    void add2BooksGetAllBooksRemoveAllBooks(){

    }
}
