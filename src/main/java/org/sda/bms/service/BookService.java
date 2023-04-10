package org.sda.bms.service;
import org.sda.bms.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    void create (String title,String description,int authorId);
    List<Book> findAll();
    Optional<Book> findById(int id );
    void deleteById(int id);
    void updateBYId(int id, String title, String description);
}