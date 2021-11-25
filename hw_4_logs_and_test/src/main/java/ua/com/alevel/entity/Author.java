package ua.com.alevel.entity;

import java.util.List;

public class Author {

    private String name;
    private List<Book> book;
    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBook() {
        return book;
    }

    public void setBook(List<Book> book) {
        this.book = book;
    }

    @Override
    public String toString() {
        return "Author{" +
                "name='" + name + '\'' +
                ", book=" + book +
                ", id='" + id + '\'' +
                '}';
    }
}
