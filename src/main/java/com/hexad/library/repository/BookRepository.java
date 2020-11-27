package com.hexad.library.repository;

import com.hexad.library.model.Book;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class BookRepository {

    private Map<Integer, Book> bookMap = new HashMap<>();

    public Map<Integer, Book>  save(Book book){
        bookMap.put(book.getId(), book);
        return bookMap;
    }

    public Map<Integer, Book> getAll() {
        return bookMap;
    }
}
