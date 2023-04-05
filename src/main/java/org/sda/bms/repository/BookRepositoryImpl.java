package org.sda.bms.repository;

import org.sda.bms.model.Book;

public class BookRepositoryImpl extends BaseRepositoryImpl<Book> implements BookRepository{
    public BookRepositoryImpl() {
        super(Book.class);
    }
}
