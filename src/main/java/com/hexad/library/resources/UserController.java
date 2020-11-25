package com.hexad.library.resources;

import com.hexad.library.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user){
        log.info("Input request to add user {}", user);
        return ResponseEntity.status(HttpStatus.CREATED).body(null);
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Integer id){
        log.info("Fetching user by Id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @GetMapping
    public ResponseEntity<User> getAll(){
        log.info("Fetching all users");
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable Integer id){
        log.info("Deleting user by Id {}", id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }
}
