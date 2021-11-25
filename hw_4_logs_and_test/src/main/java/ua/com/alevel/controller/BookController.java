package ua.com.alevel.controller;


import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class BookController {
    public static void run() {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select your option:");
        String position;
        try {
            runNavigation();
            while ((position = reader.readLine()) != null) {
                crud(position, reader);
                position = reader.readLine();
                if (position.equals("0")) {
                    return;
                }
                crud(position, reader);
            }
        } catch (IOException e) {
            System.out.println("Problem: = " + e.getMessage());
        }
    }

    private static void runNavigation() {
        System.out.println("Create book: 1");
        System.out.println("Update book: 2");
        System.out.println("Delete book: 3");
        System.out.println("Find by id book: 4");
        System.out.println("Find all books: 5");
        System.out.println("EXIT -> 0");
    }

    private static void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findByBookId(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private static void create(BufferedReader reader) {
        System.out.println("Book create");
        try {
            System.out.println("enter book title");
            String title = reader.readLine();
            System.out.println("enter author");
            Author author = new Author();
            List<Author> authors = new ArrayList<>();
            String authorName = reader.readLine();
            author.setName(authorName);
            authors.add(author);
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(authors);
            BookService.create(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void update(BufferedReader reader) {
        System.out.println("Book update");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            System.out.println("enter book title");
            String title = reader.readLine();
            System.out.println("enter author name");
            List<Author> authors = new ArrayList<>();
            Author author = new Author();
            String authorName = reader.readLine();
            author.setName(authorName);
            authors.add(author);
            Book book = new Book();
            book.setTitle(title);
            book.setAuthor(authors);
            BookService.update(book);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void delete(BufferedReader reader) {
        System.out.println("Book delete");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            BookService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findByBookId(BufferedReader reader) {
        System.out.println("Book find by id");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            Book book = BookService.findByBookId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findAll(BufferedReader reader){
        System.out.println("Book find all");
        List<Book> books = BookService.findAllBook();
        if(books != null && books.size() != 0){
            for (Book book : books){
                System.out.println("Book: " + book);
            }
        } else {
            System.out.println("Books is empty");
        }
    }
}
