package ua.com.alevel.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.com.alevel.dao.AuthorDao;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.List;

public class AuthorService {
    private static final Logger LOGGER = LoggerFactory.getLogger(AuthorService.class);
    private static final Logger LOGGER_INFO = LoggerFactory.getLogger("info");
    private static final Logger LOGGER_WARN = LoggerFactory.getLogger("warn");

    private static final AuthorDao authorDao = new AuthorDao();

    public static void create(Author author) {
        LOGGER_INFO.info("start create book");
        if (!authorDao.existByAuthorName(author.getName())) {
            authorDao.create(author);
        } else {
            System.out.println("author is already exist");
            LOGGER_WARN.warn("author is already exist: " + author.getName());
        }
    }

    public static void update(Author author) {
        LOGGER_INFO.info("start update author");
        authorDao.update(author);
    }

    public static void delete(String id) {
        LOGGER_INFO.info("start delete author");
        authorDao.delete(id);
    }

    public static Author findByAuthorId(String id) {
        LOGGER_INFO.info("start find by id");
        return authorDao.findById(id);
    }

    public List<Author> findAllAuthorOfBook(Book book) {
        LOGGER_INFO.info("start find all author of book");
        return authorDao.findAllAuthorOfBook(book);
    }

    public static List<Author> findAll() {
        LOGGER_INFO.info("start find all");
        return authorDao.findAll();

    }

}
