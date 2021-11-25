package ua.com.alevel.controller;

import ua.com.alevel.entity.Author;
import ua.com.alevel.entity.Book;
import ua.com.alevel.service.AuthorService;
import ua.com.alevel.service.BookService;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class AuthorController {
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
        System.out.println("Create author: 1");
        System.out.println("Update author: 2");
        System.out.println("Delete author: 3");
        System.out.println("Find by id author: 4");
        System.out.println("Find all authors: 5");
        System.out.println("EXIT -> 0");
    }

    private static void crud(String position, BufferedReader reader) {
        switch (position) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findByAuthorId(reader);
            case "5" -> findAll(reader);
        }
        runNavigation();
    }

    private static void create(BufferedReader reader) {
        System.out.println("author create");
        try {
            System.out.println("enter author name");
            String name = reader.readLine();
            System.out.println("enter book");
            Book book = new Book();
            List<Book> books = new ArrayList<>();
            String bookTitle = reader.readLine();
            book.setTitle(bookTitle);
            books.add(book);
            Author author = new Author();
            author.setName(name);
            author.setBook(books);
            AuthorService.create(author);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void update(BufferedReader reader) {
        System.out.println("author update");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            System.out.println("enter author name");
            String name = reader.readLine();
            System.out.println("enter book title");
            List<Book> books = new ArrayList<>();
            Book book = new Book();
            String bookTitle = reader.readLine();
            book.setTitle(bookTitle);
            books.add(book);
            Author author = new Author();
            author.setName(name);
            author.setBook(books);
            AuthorService.update(author);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void delete(BufferedReader reader) {
        System.out.println("author delete");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            AuthorService.delete(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findByAuthorId(BufferedReader reader) {
        System.out.println("author find by id");
        try {
            System.out.println("enter id");
            String id = reader.readLine();
            Author author = AuthorService.findByAuthorId(id);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void findAll(BufferedReader reader){
        System.out.println("author find all");
        List<Author> authors = AuthorService.findAll();
        if(authors != null && authors.size() != 0){
            for (Author author : authors){
                System.out.println("author: " + author);
            }
        } else {
            System.out.println("authors is empty");
        }
    }
}
