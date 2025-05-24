package com.literalis.literalis_api.repository;

import com.literalis.literalis_api.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, Long> {
    // This interface will automatically provide CRUD operations for the Book entity
    // No additional methods are needed unless you want to define custom queries
}
