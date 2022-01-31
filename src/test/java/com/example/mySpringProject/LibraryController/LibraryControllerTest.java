package com.example.mySpringProject.LibraryController;
import org.json.*;

import org.apache.tomcat.jni.Library;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static java.util.Collections.sort;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.internal.bytebuddy.matcher.ElementMatchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@AutoConfigureJsonTesters
@WebMvcTest(LibraryController.class)
class LibraryControllerTest {
    private LibraryController libraryController;
    @Autowired
    private MockMvc mvc;

    @BeforeEach
    void prepTests() {
        // Set up concrete library controller instance
        libraryController = new LibraryControllerImpl();
    }
    @Test
    void testWelcomeMessage() throws Exception {
        String welcomeMessage = "Welcome to the library!";
        MockHttpServletResponse welcomeResponse = mvc.perform(
                get("/library"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        assertThat(welcomeResponse.getContentAsString()).isEqualTo(welcomeMessage);
    }

    @Test
    void addBookandRemoveBookSuccess() throws Exception {
        String title1 = "Hello";
        String content1 = "World";
        // Put book into library
        MockHttpServletResponse postResponseAfterOneBookAdded = mvc.perform(
                post("/library/addBook?title=" + title1 + "&contents=" + content1))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        // Check that book is within library
        MockHttpServletResponse getResponseAfterOneBookAdded = mvc.perform(
                get("/library/getBook?title=" + title1))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        // Check contents are correct
        String bookContents = getResponseAfterOneBookAdded.getContentAsString();
        assertThat(bookContents).isEqualTo(content1);
        // Remove book
        mvc.perform(delete("/library/removeBook?title=" + title1))
                .andExpect(status().isOk());
        // Check that book was removed
        mvc.perform(get("/library/getBook?title=" + title1))
                .andExpect(status().isNotFound());
    }
    @Test
    void addBookUpdateBookRemoveBookSuccess() throws Exception {
        String title1 = "Hello";
        String content1 = "World";
        String content2 = "New content";
        // Put book into library
        MockHttpServletResponse postResponseAfterOneBookAdded = mvc.perform(
                post("/library/addBook?title=" + title1 + "&contents=" + content1))
                .andExpect(status().isCreated())
                .andReturn().getResponse();
        // Check that book is within library
        MockHttpServletResponse getResponseAfterOneBookAdded = mvc.perform(
                get("/library/getBook?title=" + title1))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        // Update book
        MockHttpServletResponse getResponseAfterUpdateBook = mvc.perform(
                put("/library/updateBookContents?title=" + title1 + "&newContents=" + content2))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        // Check Book is updated
        MockHttpServletResponse getResponseAfterGettingUpdatedBook = mvc.perform(
                get("/library/getBook?title=" + title1))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        String updatedContents = getResponseAfterGettingUpdatedBook.getContentAsString();
        // Check contents updated correctly
        assertThat(updatedContents).isEqualTo(content2);
        // Remove book
        mvc.perform(delete("/library/removeBook?title=" + title1))
                .andExpect(status().isOk());
        // Check that book was removed
        mvc.perform(get("/library/getBook?title=" + title1))
                .andExpect(status().isNotFound());
    }

    @Test
    void updateBookFailure() throws Exception{
        // Check that book (not added) is not updated
        String title1 = "Hello";
        String content1 = "Byeee";
        mvc.perform(
                put("/library/updateBookContents?title=" + title1 + "&newContents=" + content1))
                .andExpect(status().isNotFound());
    }
    @Test
    void removeBookFailure() throws Exception {
        // Check that book (not added) is not in the library
        String title1 = "Hello";
        mvc.perform(
                get("/library/getBook?title=" + title1))
                .andExpect(status().isNotFound());
    }

    @Test
    void add2BooksGetAllBooksRemoveAllBooks() throws Exception {
        String title1 = "Hello";
        String title2 = "All";
        String content1 = "World";
        String content2 = "the way down";
        // Create list of strings; filled in alphabetical order
        ArrayList<String> titles = new ArrayList<String>();
        titles.add(title1);
        titles.add(title2);
        // Sort titles for consistency
        sort(titles);
        // Put first book into library
        mvc.perform(
                post("/library/addBook?title=" + title1 + "&contents=" + content1))
                .andExpect(status().isCreated());
        // Put second book into library
        mvc.perform(
                post("/library/addBook?title=" + title2 + "&contents=" + content2))
                .andExpect(status().isCreated());
        // Get all books
        MockHttpServletResponse getAllBooksResponse = mvc.perform(
                get("/library/getAllBooks"))
                .andExpect(status().isOk())
                .andReturn().getResponse();
        // Ensure both books are there
        JSONArray getAllBooksMultiBookResponseArray =  new JSONArray(getAllBooksResponse.getContentAsString());
        List<String> responseList = new ArrayList<String>();
        // Add JSON array concents to responseList for testing
        for (int i = 0; i < getAllBooksMultiBookResponseArray.length(); i++){
            String currString = getAllBooksMultiBookResponseArray.get(i).toString();
            responseList.add(currString);
        }
        assertThat(responseList).isEqualTo(titles);
        // Remove first book
        mvc.perform(
                delete("/library/removeBook?title=" + title1))
                .andExpect(status().isOk());
        // Remove second book
        mvc.perform(
                delete("/library/removeBook?title=" + title2))
                .andExpect(status().isOk());
        // Get all books, expect none
        MockHttpServletResponse getAllBooksResponseExpectNone = mvc.perform(
                get("/library/getAllBooks"))
                .andExpect(status().isNotFound())
                .andReturn().getResponse();
        assertThat(getAllBooksResponseExpectNone.getContentAsByteArray()).isEmpty();
    }
}
