package com.hexad.library.resources;

import com.hexad.library.model.Book;
import com.hexad.library.model.Borrowed;
import com.hexad.library.model.Issue;
import com.hexad.library.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@Slf4j
@RequestMapping("/issue")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping
    public ResponseEntity<Issue> issueBook(@RequestBody Issue issue){
        return ResponseEntity.ok(issueService.issue(issue));
    }

    @GetMapping
    public ResponseEntity<Map<Integer, List<Borrowed>>> getAll(){
        log.info("Getting all books");
        Map<Integer, List<Borrowed>> issues = issueService.getAll();
        return ResponseEntity.status(HttpStatus.OK).body(issues);
    }
}
