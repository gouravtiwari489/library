package com.hexad.library.service;

import com.hexad.library.model.Book;
import com.hexad.library.model.Borrowed;
import com.hexad.library.model.Issue;
import com.hexad.library.repository.BookRepository;
import com.hexad.library.repository.IssueRepository;
import com.hexad.library.util.IssueValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IssueService {

    @Autowired
    private IssueValidator issueValidator;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private BookRepository bookRepository;

    public Issue issue(Issue issue) {
        List<Borrowed> borrowedList = issue.getIssueBooks();
        issueValidator.checkAvailability(borrowedList);
        assignBook(issue);
        return issue;
    }

    private void assignBook(Issue issue) {
        Map<Integer, List<Borrowed>> issuedBooks = issueRepository.getAllIssuedBooks();
        List<Borrowed> orderedBooks = issue.getIssueBooks();
        Map<Integer, Book> availableBooks  = bookRepository.getAll();

        for(Map.Entry<Integer, Book> entry:availableBooks.entrySet()){
            for(Borrowed orderedBook: orderedBooks){
                if(entry.getValue().getId().equals(orderedBook.getBookId())){

                    if(!issuedBooks.containsKey(issue.getUserId())){
                        List<Borrowed> list = new ArrayList<>();
                        list.add(orderedBook);
                        issuedBooks.put(issue.getUserId(), list);
                        updateQuantity(entry);
                    }else{
                        List<Borrowed> existingIssuedBooks = issuedBooks.get(issue.getUserId());
                        issueValidator.isBookAlreadyAssignedToUser(existingIssuedBooks, orderedBook, issue.getUserId());
                        existingIssuedBooks.add(orderedBook);
                        issuedBooks.put(issue.getUserId(), existingIssuedBooks);
                        updateQuantity(entry);
                    }
                }
            }
        }

    }

    private void updateQuantity(Map.Entry<Integer, Book> entry) {
        Integer count = entry.getValue().getQuantity();
        entry.getValue().setQuantity(count - 1);
    }

    public Map<Integer, List<Borrowed>> getAll() {
        return issueRepository.getAll();
    }
}
