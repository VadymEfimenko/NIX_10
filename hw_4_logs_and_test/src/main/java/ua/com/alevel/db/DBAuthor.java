package ua.com.alevel.db;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DBAuthor {

    private static DBAuthor instance;
    private static List<Author> authors = new ArrayList<>();

    private DBAuthor() {
        authors = new ArrayList<>();
    }

    public static DBAuthor getInstance() {
        if (instance == null) {
            instance = new DBAuthor();
        }
        return instance;
    }

    private static String generateId() {
        String id = UUID.randomUUID().toString();
        for (Author author : authors) {
            if (author != null && author.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }

    public List<Author> findAll() {
        return authors;
    }

    public void create(Author author) {
        author.setId(generateId());
        authors.add(author);
    }

    public void update(Author author) {
        Author current = findById(author.getId());
        current.setName(author.getName());
        current.setBook(author.getBook());
    }

    public void delete(String id) {
        Author current = findById(id);
        authors.remove(current);
    }

    public List<Author> findAllAuthorOfBook(Book book) {
        ArrayList<Author> books = new ArrayList<>();
        for (Author author : authors) {
            if (author.getBook().equals(book)) {
                books.add(author);
            }
            return books;
        }
        throw new RuntimeException("No Book");
    }

    public Author findById(String id) {
        for (Author author : authors) {
            if (id.equals(author.getId())) {
                return author;
            }
        }
        throw new RuntimeException("No author");
    }

    public boolean existByAuthorName(String name) {
        for (Author author : authors) {
            if (author.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }
}
