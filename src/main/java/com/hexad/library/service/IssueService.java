package com.hexad.library.service;

import com.hexad.library.exception.LibraryException;
import com.hexad.library.model.Borrowed;
import com.hexad.library.model.Issue;
import com.hexad.library.repository.IssueRepository;
import com.hexad.library.util.IssueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Service
public class IssueService {

    @Autowired
    private IssueValidator issueValidator;

    @Autowired
    private IssueRepository issueRepository;

    public Issue issue(Issue issue) {
        List<Borrowed> borrowedList = issue.getIssuedBookIds();
        issueValidator.checkAvailability(borrowedList);
        issueValidator.validateBooksPerUser(issue);

        Integer userId= Objects.nonNull(issue)? issue.getUserId() : null;
        Map<Integer, List<Borrowed>>  issuedBooksUserIdMap = issueRepository.getAllIssuedBooks();

        if(!issuedBooksUserIdMap.containsKey(issue.getUserId())){
            List<Borrowed> list = new ArrayList<>();
            issuedBooksUserIdMap.put(issue.getUserId(), list);
        }else{
            List<Borrowed> existingBorrowedBooks = issuedBooksUserIdMap.get(issue.getUserId());
            //issueValidator.validateBooksPerUser(toUserId(existingBorrowedBooks), userId);
            //check availability of books
            existingBorrowedBooks.addAll(issue.getIssuedBookIds());
        }

        return issue;
    }

    private Integer toUserId(List<Borrowed> existingIssues) {
        return null;//existingIssues.stream().findFirst().get().getUserId();
    }
}
