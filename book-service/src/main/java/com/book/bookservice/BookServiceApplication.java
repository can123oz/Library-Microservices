package com.book.bookservice;

import com.book.bookservice.model.Book;
import com.book.bookservice.repository.BookRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
@EnableEurekaClient // BookService bir client olarak Eurokaya bağlamak için app context e configleri gir bulması için
public class BookServiceApplication implements CommandLineRunner {

    private final BookRepository bookRepository;

    public BookServiceApplication(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(BookServiceApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        Book book1 = new Book("1","Hp1", 1999, "Jk Rowling", "Harry P", "123");
        Book book2 = new Book("2", "Hp2", 2002, "Jk Rowling1", "Harry P2", "1231");
        Book book3 = new Book("3","Hp3", 2007, "Jk Rowling2", "Harry P3", "12333");
        Book book4 = new Book("4","Hp4", 2010, "Jk Rowling3", "Harry P4", "222");
        List<Book> result =  bookRepository.saveAll(Arrays.asList(book1,book4,book3,book2));
        System.out.println(result);
    }


}
