package ua.com.alevel.dao;

import ua.com.alevel.db.DBAuthor;
import ua.com.alevel.db.DBBook;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class AuthorDao {

    public void create(Author author) {
        DBAuthor.getInstance().create(author);
    }

    public void update(Author author) {
        DBAuthor.getInstance().update(author);
    }

    public void delete(String id) {
        DBAuthor.getInstance().delete(id);
    }

    public List<Author> findAll() {
        List<Author> authors = DBAuthor.getInstance().findAll();
        return authors;
    }

    public List<Author> findAllAuthorOfBook(Book book) {
        return DBAuthor.getInstance().findAllAuthorOfBook(book);
    }

    public Author findById(String id) {
        return DBAuthor.getInstance().findById(id);
    }

    public boolean existByAuthorName(String name) {
        return DBAuthor.getInstance().existByAuthorName(name);
    }
}
