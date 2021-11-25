package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.BookDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public class BookService {
    private static final Logger LOGGER = LoggerFactory.getLogger(BookService.class);
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private static final BookDao bookDao = new BookDao();

    public static void create(Book book) {
        LOGGER_INFO.info("start create book");
        if (!bookDao.bookExistByTitle(book.getTitle())) {
            bookDao.create(book);
        } else {
            System.out.println("Book is already exist");
            LOGGER_WARN.warn("Book is already exist: " + book.getTitle());
        }
    }

    public static void update(Book book) {
        LOGGER_INFO.info("start update book");
        bookDao.update(book);
    }

    public static void delete(String id) {
        LOGGER_INFO.info("start delete book");
        bookDao.delete(id);
    }

    public static Book findByBookId(String id) {
        LOGGER_INFO.info("start find book by id");
        return bookDao.findById(id);
    }

    public static List<Book> findAllBook() {
        LOGGER_INFO.info("start find all books");
        return bookDao.findAll();
    }

    public Book findByTitle(String title) {
        LOGGER_INFO.info("start find book by title");
        if (!bookDao.bookExistByTitle(title)) {
            LOGGER_WARN.warn("no book by this title");
        }
        return bookDao.findByTitle(title);
    }
}
