package com.example.mySpringProject.LibraryController;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import static java.util.Collections.sort;

@RestController
@RequestMapping("/library")
public class LibraryControllerImpl implements LibraryController {
    // Key = title
    // Value = contents
    private HashMap<String, String> library;

    public LibraryControllerImpl(){
        this.library = new HashMap<String, String>();
    }
    // Prints welcome message from library
    @Override
    public String welcome() {
        return "Welcome to the library!";
    }

    // Adds book from library by title and coontents
    @Override
    public void addBook(String title, String contents) {
        this.library.put(title,contents);
    }

    // Removes book from library by title
    // Returns not found error if book was not in library already
    @Override
    public void removeBook(String title) {
        if(this.library.containsKey(title)) {
            this.library.remove(title);
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    // Get single book's content, by title
    @Override
    public String getBook(String title) {
        Optional<String> bookContents = Optional.ofNullable(this.library.get(title));
        if (!bookContents.isPresent()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return bookContents.get();
    }

    // Get all books in library
    // Will sort books by alphabetical order
    @Override
    public List<String> getBooksInLibrary() {
        // Create list
        List<String>booksInLibrary = new ArrayList<String>();

        // See if library has any books; exit if no books
        if(this.library.keySet().size() == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        // Loop over list, add titles
        for (String title : this.library.keySet()) {
            String titleString = title;
            booksInLibrary.add(titleString);
        }
        // Sort List
        sort(booksInLibrary);

        // Return list
        return booksInLibrary;
    }
}
