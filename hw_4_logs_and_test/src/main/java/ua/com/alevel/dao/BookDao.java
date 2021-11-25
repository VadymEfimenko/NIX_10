package ua.com.alevel.dao;

import ua.com.alevel.db.DBAuthor;
import ua.com.alevel.db.DBBook;
import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;

public class BookDao {

    public void create(Book book){
        DBBook.getInstance().create(book);
    }

    public void update(Book book){
        DBBook.getInstance().update(book);
    }

    public void delete(String id){
        DBBook.getInstance().delete(id);
    }

    public Book findById(String id){
        return DBBook.getInstance().findById(id);
    }

    public List<Book> findAll(){
        List<Book> books = DBBook.getInstance().findAll();
        return books;
    }

    public boolean bookExistByTitle(String title){
        return DBBook.getInstance().bookExistByTitle(title);
    }

    public Book findByTitle(String title){
        return DBBook.getInstance().findByTitle(title);
    }
}
