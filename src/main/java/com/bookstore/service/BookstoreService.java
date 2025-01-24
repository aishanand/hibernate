package com.bookstore.service;


import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bookstore.entity.Author;
import com.bookstore.entity.Book;
import com.bookstore.repository.AuthorRepository;
import com.bookstore.repository.BookRepository;


@Service
public class BookstoreService {

    private final AuthorRepository authorRepository;
    private final BookRepository bookRepository;


    public BookstoreService(AuthorRepository authorRepository, BookRepository bookRepository) {

        this.authorRepository = authorRepository;
        this.bookRepository = bookRepository;
    }


    public List<Book> fetchBooksOfAuthor(String name) {

        return bookRepository.booksOfAuthor(name);
    }


    @Transactional
    public void updateBooksOfAuthor(String name, List<Book> detachedBooks) {

        Author author = authorRepository.authorAndBooks(name);
        System.out.println("-------------------------------------------------");

        // Remove the existing database rows that are no 
        // longer found in the incoming collection (detachedBooks)
        List<Book> booksToRemove = author.getBooks().stream().filter(b -> !detachedBooks.contains(b)).toList();
        booksToRemove.forEach(author::removeBook);

        // Update the existing database rows which can be found 
        // in the incoming collection (detachedBooks)
        List<Book> newBooks = detachedBooks.stream().filter(b -> !author.getBooks().contains(b)).toList();

        detachedBooks.stream().filter(b -> !newBooks.contains(b)).forEach((b) -> {
            b.setAuthor(author);
            Book mergedBook = bookRepository.save(b);
            author.getBooks().set(author.getBooks().indexOf(mergedBook), mergedBook);
        });

        // Add the rows found in the incoming collection, 
        // which cannot be found in the current database snapshot
        newBooks.forEach(b -> author.addBook(b));
    }


    @Transactional
    public Author updateAuthorName(final Long authorChangedId, final String newAuthorName) {

        Author saved = null;

        Optional<Author> dbAuthorOptional = authorRepository.findById(authorChangedId);

        if (dbAuthorOptional.isPresent()) {

            Author dbAuthor = dbAuthorOptional.get();
            dbAuthor.setName(newAuthorName);

            // this is merge
            saved = authorRepository.save(dbAuthor);

        }

        return saved;
    }


    @Transactional
    public Author updateAuthorNameWithIncoming(final Author newAuthor) {

        // this is merge
        return authorRepository.save(newAuthor);

    }


    public Author findAuthorById(final Long id) {

        Author found = null;

        Optional<Author> author = authorRepository.findById(id);
        if (author.isPresent()) {
            found = author.get();
        }
        return found;
    }


}
