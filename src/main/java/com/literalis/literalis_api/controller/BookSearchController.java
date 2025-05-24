package com.literalis.literalis_api.controller;

import com.literalis.literalis_api.service.GoogleBooksService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "https://pinhorenan.github.io")
public class BookSearchController {

    private final GoogleBooksService service;

    public BookSearchController(GoogleBooksService service) {
        this.service = service;
    }

    @GetMapping("/search")
    public List<Map<String, String>> search(@RequestParam("q") String query) {
        return service.searchBooks(query);
    }
}
