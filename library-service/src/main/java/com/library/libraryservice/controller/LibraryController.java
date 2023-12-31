package com.library.libraryservice.controller;

import com.library.libraryservice.dto.AddBookRequest;
import com.library.libraryservice.dto.LibraryDto;
import com.library.libraryservice.service.LibraryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RefreshScope // ?? bak buna
@RequestMapping("/v1/library")
public class LibraryController {
    private final LibraryService libraryService;
    private final Environment environment;
    Logger logger = LoggerFactory.getLogger(LibraryController.class);

    @Value("${library.service.count}")
    private Integer count;

    public LibraryController(LibraryService libraryService,
                             Environment environment) {
        this.libraryService = libraryService;
        this.environment = environment;
    }


    @GetMapping("/count")
    public ResponseEntity<Integer> Count() {
        return ResponseEntity.ok(count);
    }


    @GetMapping("/{id}")
    public ResponseEntity<LibraryDto> getLibraryById(@PathVariable String id) {
        return ResponseEntity.ok(libraryService.getAllBooksInLibraryById(id));
    }

    @PostMapping
    public ResponseEntity<LibraryDto> createLibrary() {
        logger.info("Library Created with this port : " + environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.createLibrary());
    }


    @PutMapping
    public ResponseEntity<Void> addBookToLibrary(@RequestBody AddBookRequest request) {
        libraryService.addBookToLibrary(request);
        return ResponseEntity.ok().build();
    }


    @GetMapping()
    public ResponseEntity<List<LibraryDto>> getAllLibrary() {
        return ResponseEntity.ok(libraryService.getAllLibrary());
    }

    @GetMapping("/getAllLibraryIds")
    public ResponseEntity<List<String>> getAllLibraryIds() {
        logger.info("Libraries getter method with : "+ environment.getProperty("local.server.port"));
        return ResponseEntity.ok(libraryService.getAllLibraryIds());
    }



}
