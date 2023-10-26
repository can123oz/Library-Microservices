package com.library.libraryservice.client;

import com.library.libraryservice.dto.BookDto;
import com.library.libraryservice.dto.BookIdDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

// Bu anostasyon sayesinde verdiğimiz uygulama ismi sayesinde server bilgilerini çekip kullanıma açılıyor.
@FeignClient(name="book-service",path = "/v1/book")
public interface BookServiceClient {

    Logger logger = LoggerFactory.getLogger(BookServiceClient.class);

    @GetMapping
    ResponseEntity<List<BookDto>> getAllBook();

    @GetMapping("/{id}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker",fallbackMethod = "getBookDetailsFallBack")
    ResponseEntity<BookDto> getBookDetailsById(@PathVariable String id);

    @GetMapping("/isbn/{isbn}")
    @CircuitBreaker(name = "getBookByIsbnCircuitBreaker",fallbackMethod = "getBookFallBack")
    ResponseEntity<BookIdDto> getBookIdByIsbn(@PathVariable String isbn);

    //FALLBACK METHODA UYMALI YUKARDAKI !!
    // Her zaman feign clientta gönderdiğimiz parametre ve exception alır.
    // (String isbn) fallbackini yaptığım methodun parametresi
    // Exception circuit breaker tarafından üretilir.
    default ResponseEntity<BookIdDto> getBookFallBack(String isbn, Exception exception) {
        logger.info("Cant Find a book with this isbn : "+ isbn + ", returning default BookDTO object");
        return ResponseEntity.ok(new BookIdDto("default-id","default-isbn"));
    }

    default ResponseEntity<BookDto> getBookDetailsFallBack(String id, Exception exception) {
        logger.info("GetBookDetailsFallBack with this id : "+id);
        return ResponseEntity.ok(new BookDto(new BookIdDto("default-id","default-isbn")));
    }
}