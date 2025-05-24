package com.literalis.literalis_api.service;

import com.literalis.literalis_api.model.Book;
import com.literalis.literalis_api.repository.BookRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository repository;

    public BookService(BookRepository repository) {
        this.repository = repository;
    }

    public List<Book> getAll() {
        return repository.findAll();
    }

    public Book create(Book book) {
        return repository.save(book);
    }
}
