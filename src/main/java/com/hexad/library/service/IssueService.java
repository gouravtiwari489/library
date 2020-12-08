package com.hexad.library.service;

import com.hexad.library.model.Borrowed;
import com.hexad.library.model.Issue;
import com.hexad.library.repository.IssueRepository;
import com.hexad.library.util.IssueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IssueService {

    @Autowired
    private IssueValidator issueValidator;

    @Autowired
    private IssueRepository issueRepository;

    public Issue issue(Issue issue) {
        List<Borrowed> borrowedList = issue.getIssueBooks();
        issueValidator.checkAvailability(borrowedList);
        issueValidator.assignBook(issue);

        return issue;
    }

    private Integer toUserId(List<Borrowed> existingIssues) {
        return null;//existingIssues.stream().findFirst().get().getUserId();
    }
}
