package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBBook {
    public static DBBook instance;
    private static List<Book> books = new ArrayList<>();

    public DBBook() {
        new ArrayList<Book>();
    }

    public static DBBook getInstance() {
        if (instance == null) {
            instance = new DBBook();
        }
        return instance;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        for (Book book : books) {
            if (id != null && book.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }

    public void create(Book book) {
        book.setId(generateId());
        books.add(book);
    }

    public void update(Book book) {
        Book current = findById(book.getId());
        current.setAuthor(book.getAuthor());
        current.setTitle(book.getTitle());
    }

    public void delete(String id) {
        books.remove(findById(id));
    }

    public Book findById(String id) {
        for (Book book : books) {
            if (id.equals(book.getId())) {
                return book;
            }
        }
        throw new RuntimeException("No book");
    }

    public Book findByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        throw new RuntimeException("No book");
    }

    public List<Book> findAll() {
        return books;
    }

    public boolean bookExistByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return true;
            }
        }
        return false;
    }
}
