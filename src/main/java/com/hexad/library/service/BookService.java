package com.hexad.library.service;

import com.hexad.library.model.Book;
import com.hexad.library.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;

    public List<Book> save(Book book){
        return bookRepository.save(book).entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public List<Book> getAll() {
        return bookRepository.getAll().entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
    }

    public Book getById(Integer id) {
       Book book = bookRepository.getAll().get(id);
       return book;
    }
}
