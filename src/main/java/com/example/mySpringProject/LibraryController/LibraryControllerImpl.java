package com.example.mySpringProject.LibraryController;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/library")
public class LibraryControllerImpl implements LibraryController {
    // Key = title
    // Value = contents
    private HashMap library;

    void LibraryController(){
        library = new HashMap();
    }

    @Override
    public String welcome() {
        return "Welcome to the library!";
    }

    @Override
    public void addBook(String title, String contents) {
        library.put(title,contents);
    }

    @Override
    public void removeBook(String title) {
        library.remove(title);
    }

    @Override
    public String getBook(String title) {
        String contents = (String) library.get(title);
        return contents;
    }

    @Override
    public List<String> getBooksInLibrary() {
        // Create list
        List<String> booksInLibrary = new ArrayList<String>();
        // Loop over list, add titles
        for(Object title : library.keySet()){
            String titleString = (String) title;
            booksInLibrary.add(titleString);
        }
        // Return list
        return booksInLibrary;
    }
}
