package com.hexad.library.resources;

import com.hexad.library.model.Issue;
import com.hexad.library.service.IssueService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
}
