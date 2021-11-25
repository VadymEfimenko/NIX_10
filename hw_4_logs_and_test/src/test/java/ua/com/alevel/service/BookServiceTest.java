package ua.com.alevel.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookServiceTest {

    private static final BookService bookService = new BookService();

    private static final String TITLE = "TITLE";
    private static final String TITLE_UPDATE = "TITLE_UPDATE";
    private static final String AUTHOR_NAME = "AUTHOR";
    private static final String AUTHOR_UPDATE = "AUTHOR_UPDATE";
    private static final int DEFAULT_SIZE = 10;


    @Test
    @Order(1)
    public void shouldBeCreateBook(){
        List<Book> books = new ArrayList<>();
        for (int i = 0; i < DEFAULT_SIZE; i++) {
            Book book = new Book();
            book.setTitle(TITLE + i);
            Author author = new Author();
            author.setName(AUTHOR_NAME + i);
            ArrayList<Author> authors = new ArrayList<>();
            authors.add(author);
            book.setAuthor(authors);
            books.add(book);
        }
        Assertions.assertEquals(books.size(), DEFAULT_SIZE );
    }

    @Test
    @Order(2)
    public void shouldBeUpdateBook(){
        Book book = new Book();
        book.setTitle(TITLE);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(author);
        book.setAuthor(authors);
        BookService.create(book);
        BookService.update(book);
        Author authorUpdate = new Author();
        author.setName(AUTHOR_UPDATE);
        ArrayList<Author> authorsUpdate = new ArrayList<>();
        authorsUpdate.add(authorUpdate);
        book.setTitle(TITLE_UPDATE);
        book.setAuthor(authorsUpdate);
        Assertions.assertEquals(book.getTitle(), TITLE_UPDATE);
        Assertions.assertEquals(book.getAuthor(), authorsUpdate);
    }

    @Test
    @Order(3)
    public void shouldBeDeleteBook(){
        Book book = new Book();
        book.setTitle(TITLE);
        Author author = new Author();
        author.setName(AUTHOR_NAME);
        ArrayList<Author> authors = new ArrayList<>();
        authors.add(author);
        book.setAuthor(authors);
        BookService.create(book);
        String bookId = book.getId();
        BookService.delete(bookId);
        List<Book> books = BookService.findAllBook();
        Assertions.assertEquals(books.size()-1, 0);
    }
}
