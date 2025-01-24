package com.bookstore;


import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.bookstore.entity.Author;
import com.bookstore.service.BookstoreService;


@SpringBootApplication
public class MainApplication {

    private final BookstoreService bookstoreService;


    public MainApplication(BookstoreService bookstoreService) {

        this.bookstoreService = bookstoreService;
    }


    public static void main(String[] args) {

        SpringApplication.run(MainApplication.class, args);
    }


    @Bean
    public ApplicationRunner init() {

        return args -> {

            // Just changing the author name is happening here

         /*   Author newAuthor = new Author();
            newAuthor.setId(1l);
            newAuthor.setName("Aish");
            newAuthor.setGenre("Anthology");
            newAuthor.setAge(23);

            bookstoreService.updateAuthorNameWithIncoming(newAuthor);*/

            System.out.println("Before update Author" + bookstoreService.findAuthorById(1l).toString());
            Author updated = bookstoreService.updateAuthorName(1l, "Aish");
            System.out.println("Updated Author"+ updated.toString());


        };
    }
}
