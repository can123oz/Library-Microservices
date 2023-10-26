package com.library.libraryservice.service;

import com.library.libraryservice.client.BookServiceClient;
import com.library.libraryservice.dto.AddBookRequest;
import com.library.libraryservice.dto.BookDto;
import com.library.libraryservice.dto.BookIdDto;
import com.library.libraryservice.dto.LibraryDto;
import com.library.libraryservice.exception.LibraryNotFoundException;
import com.library.libraryservice.model.Library;
import com.library.libraryservice.repository.LibraryRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LibraryService {
    private final LibraryRepository libraryRepository;
    private final BookServiceClient bookServiceClient;

    public LibraryService(LibraryRepository libraryRepository,
                          BookServiceClient bookServiceClient) {
        this.libraryRepository = libraryRepository;
        this.bookServiceClient = bookServiceClient;
    }

    public LibraryDto getAllBooksInLibraryById(String id) {
        Library library = libraryRepository.findById(id)
                .orElseThrow(() -> new LibraryNotFoundException("Library couldnt find by id : " + id));
        LibraryDto libraryDto = new LibraryDto(library.getId(),
                library.getUserBook()
                        .stream()
                        .map(bookServiceClient::getBookDetailsById)
                        //.map(bookId -> bookServiceClient.getBookDetailsById(bookId)) // üst satır ile aynı şey
                        .map(ResponseEntity::getBody)
                        .collect(Collectors.toList()));
        return libraryDto;
    }

    public LibraryDto createLibrary() {
        Library newLibrary = libraryRepository.save(new Library());
        return new LibraryDto(newLibrary.getId());
    }

    public void addBookToLibrary(AddBookRequest addBookRequest) {
        String bookId = bookServiceClient.getBookIdByIsbn(addBookRequest.getIsbn()).getBody().getId();
        Library library = libraryRepository.findById(addBookRequest.getId())
                .orElseThrow(() -> new LibraryNotFoundException("Library not found by id :"+addBookRequest.getId()));

        library.getUserBook().add(bookId);
        libraryRepository.save(library);
    }


    public List<LibraryDto> getAllLibrary() {
        return libraryRepository.findAll()
                .stream()
                .map(library -> new LibraryDto(
                        library.getId(),
                        library.getUserBook()
                                .stream()
                                .map(bookId ->
                                        bookServiceClient.getBookDetailsById(bookId)
                                                .getBody()
                ).collect(Collectors.toList()))).collect(Collectors.toList());
    }


}
