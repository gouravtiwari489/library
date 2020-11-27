package com.hexad.library.resources;

import com.hexad.library.model.Book;
import com.hexad.library.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @Autowired
    private BookService bookService;

    @PostMapping
    public ResponseEntity<List<Book>> save(@RequestBody Book book){
        log.info("Input request to add books {}", book);
        List<Book> books = bookService.save(book);
        return ResponseEntity.status(HttpStatus.CREATED).body(books);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Integer id){
        log.info("Get book by id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity<List<Book>> getAll(){
        log.info("Getting all books");
        List<Book> books = bookService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(books);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id){
        log.info("Deleting book by Id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
