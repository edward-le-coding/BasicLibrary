package com.example.mySpringProject.LibraryController;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/library")
public interface LibraryController {
    @GetMapping()
    String welcome();

    @PostMapping("/addBook")
    @ResponseStatus(HttpStatus.CREATED)
    void addBook(@RequestParam String title, @RequestParam String contents);

    @PutMapping("/updateBookContents")
    @ResponseStatus(HttpStatus.OK)
    void updateBookContents(@RequestParam String title, @RequestParam String newContents);
    @DeleteMapping("/removeBook")
    @ResponseStatus(HttpStatus.OK)
    void removeBook(@RequestParam String title);

    @GetMapping("/getBook")
    @ResponseStatus(HttpStatus.OK)
    String getBook(@RequestParam String title);

    @GetMapping("/getAllBooks")
    @ResponseStatus(HttpStatus.OK)
    List<String> getBooksInLibrary();
}
