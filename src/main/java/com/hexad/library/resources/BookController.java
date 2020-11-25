package com.hexad.library.resources;

import com.hexad.library.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @PostMapping
    public ResponseEntity<Book> addBook(@RequestBody Book book){
        log.info("Input request to add books {}", book);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

}
