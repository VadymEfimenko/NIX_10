package ua.com.alevel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorServiceTest {
    private static final AuthorService authorService = new AuthorService();

    private static final String NAME = "NAME";
    private static final String NAME_UPDATE = "NAME_UPDATE";
    private static final String BOOK = "BOOK";
    private static final String BOOK_UPDATE = "BOOK_UPDATE";
    private static final int DEFAULT_SIZE = 10;

    public static Author generateAuthor() {
        Author author = new Author();
        author.setName(NAME);
        Book book = new Book();
        book.setTitle("title");
        List<Book> books = new ArrayList<>();
        books.add(book);
        author.setBook(books);
        return author;
    }

    @Test
    @Order(1)
    public void shouldBeCreateAuthor() {
        List<Author> authors = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Author author = new Author();
            author.setName(NAME + i);
            Book book = new Book();
            book.setTitle(BOOK);
            ArrayList<Book> books = new ArrayList<>();
            books.add(book);
            author.setBook(books);
            authors.add(author);
        }
        Assertions.assertEquals(authors.size(), DEFAULT_SIZE);
    }

    @Test
    @Order(2)
    public void shouldBeUpdateAuthor() {
        Author author = generateAuthor();
        AuthorService.create(author);
        AuthorService.update(author);
        author.setName(NAME_UPDATE);
        ArrayList<Book> books = new ArrayList<>();
        Book bookUpdate = new Book();
        bookUpdate.setTitle(BOOK_UPDATE);
        books.add(bookUpdate);
        author.setBook(books);
        Assertions.assertEquals(author.getName(), NAME_UPDATE);
        Assertions.assertEquals(author.getBook(), books);
    }

    @Test
    @Order(3)
    public void shouldBeDeleteAuthor() {
        Author author = generateAuthor();
        AuthorService.create(author);
        String authorId = author.getId();
        AuthorService.delete(authorId);
        List<Author> authors = AuthorService.findAll();
        Assertions.assertEquals(authors.size()-1, 0);
    }
}
