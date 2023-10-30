package com.book.bookservice.controller;

import com.book.bookservice.dto.BookDto;
import com.book.bookservice.dto.BookIdDto;
import com.book.bookservice.service.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.DoubleSummaryStatistics;
import java.util.List;

@RestController
@RequestMapping("/v1/book")
public class BookController {
    private final BookService bookService;
    Logger logger = LoggerFactory.getLogger(BookController.class);

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }


    @GetMapping
    public ResponseEntity<List<BookDto>> getAllBook() {
        return ResponseEntity.ok(bookService.getAllBooks());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BookDto> getBookDetailsById(@PathVariable @NotEmpty String id) {
        logger.info("Book Api getBookDetailsById request..");
        return ResponseEntity.ok(bookService.findBookDetailsById(id));
    }

    @GetMapping("/isbn/{isbn}")
    public ResponseEntity<BookIdDto> getBookIdByIsbn(@PathVariable @NotEmpty String isbn) {
        logger.info("Book Api getBookByIsbn request..");
        return ResponseEntity.ok(bookService.findByIsbn(isbn));
    }




}
