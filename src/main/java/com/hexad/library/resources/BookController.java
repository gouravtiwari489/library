package com.hexad.library.resources;

import com.hexad.library.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.logging.Logger;

@RestController
@RequestMapping("/books")
@Slf4j
public class BookController {

    @PostMapping
    public ResponseEntity<Book> save(@RequestBody Book book){
        log.info("Input request to add books {}", book);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Book> getById(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity<Book> getAll(){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Book> delete(@PathVariable Integer id){
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

}
