package com.hexad.library.util;

import com.hexad.library.exception.LibraryException;
import com.hexad.library.model.Book;
import com.hexad.library.model.Borrowed;
import com.hexad.library.model.Issue;
import com.hexad.library.repository.BookRepository;
import com.hexad.library.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class IssueValidator {

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private IssueRepository issueRepository;

    public void checkAvailability(List<Borrowed> issuedBookIds) {
        Map<Integer, Book> availableBooks =  bookRepository.getAll();

        if(availableBooks.isEmpty()){
            throw new LibraryException("99","Library has no books", "VALIDATION_ERROR");
        }

        for(Map.Entry<Integer, Book> entry:availableBooks.entrySet()){
            for(Borrowed borrowed:issuedBookIds){
                if(!availableBooks.containsKey(borrowed.getBookId())){
                    throw new LibraryException("100", "Book with id: "+borrowed.getBookId()+" is no available in library","VALIDATION_ERROR");
                }
                if(entry.getKey().equals(borrowed.getBookId()) && entry.getValue().getQuantity()<=0){
                    throw new LibraryException("101", "Book with id "+borrowed.getBookId()+" is out of stock","VALIDATION_ERROR");
                }
            }

        }
    }

    public void assignBook(Issue issue) {
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
                        Integer count = entry.getValue().getQuantity();
                        entry.getValue().setQuantity(count-1);
                    }else{
                        List<Borrowed> existingIssuedBooks = issuedBooks.get(issue.getUserId());
                        existingIssuedBooks.add(orderedBook);
                        issuedBooks.put(issue.getUserId(), existingIssuedBooks);
                        Integer count = entry.getValue().getQuantity();
                        entry.getValue().setQuantity(count-1);
                    }
                }
            }
        }

    }
}
