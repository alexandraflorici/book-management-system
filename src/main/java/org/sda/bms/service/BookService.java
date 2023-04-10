package org.sda.bms.service;

import org.sda.bms.model.Book;

import java.util.List;

public interface BookService {
    void create(String title, String description, int authorId);
    List<Book> findAll();
}
