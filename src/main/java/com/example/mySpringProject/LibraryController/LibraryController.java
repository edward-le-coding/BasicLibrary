package com.example.mySpringProject.LibraryController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/library")
public interface LibraryController {
    @GetMapping("/")
    String welcome();
    @PostMapping("/add")
    @ResponseStatus(HttpStatus.OK)
    void addBook(@RequestParam String title,@RequestParam String contents);
    @DeleteMapping("/remove")
    @ResponseStatus(HttpStatus.OK)
    void removeBook(@RequestParam String title);
    @GetMapping("/getBook")
    @ResponseStatus(HttpStatus.OK)
    String getBook(@RequestParam String title);
    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    List<String> getBooksInLibrary();
}
