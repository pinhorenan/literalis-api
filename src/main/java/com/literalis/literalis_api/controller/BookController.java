package com.literalis.literalis_api.controller;

import com.literalis.literalis_api.model.Book;
import com.literalis.literalis_api.service.BookService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@CrossOrigin(origins = "https://pinhorenan.github.io")
public class BookController {

    private final BookService service;

    public BookController(BookService service) {
        this.service = service;
    }

    @GetMapping
    public List<Book> listBooks() {
        return service.getAll();
    }

    @PostMapping
    public Book addBook(@RequestBody Book book) {
        return service.create(book);
    }
}
