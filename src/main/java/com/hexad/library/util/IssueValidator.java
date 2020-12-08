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
        for(Map.Entry<Integer, Book> entry:availableBooks.entrySet()){
            for(Borrowed borrowed:issuedBookIds){
                if(!availableBooks.containsKey(borrowed.getBookId())){
                    throw new LibraryException("100", "Book with id"+borrowed.getBookId()+"is no available in library","E001");
                }
                if(entry.getKey().equals(borrowed.getBookId()) && entry.getValue().getQuantity()<=0){
                    throw new LibraryException("101", "Book with id"+borrowed.getBookId()+"is out of stock","E002");
                }
            }

        }
    }

    public void validateBooksPerUser(Issue issue) {
        Map<Integer, List<Borrowed>> issuedBooks = issueRepository.getAllIssuedBooks();
        List<Borrowed> orderedBooks = issue.getIssuedBookIds();
        Map<Integer, Book> availableBooks  = bookRepository.getAll();

        for(Map.Entry<Integer, List<Borrowed>> entry:issuedBooks.entrySet()){
            for (Borrowed order: orderedBooks) {
                if(!issuedBooks.containsKey(issue.getUserId())){
                    List<Borrowed> borroweds = new ArrayList<>();
                    borroweds.add(order);
                    Integer count = availableBooks.get(order.getBookId()).getQuantity();
                    availableBooks.get(order.getBookId()).setQuantity(count-1);
                    issuedBooks.put(issue.getUserId(), borroweds);
                }else{
                    List<Borrowed> borroweds=  issuedBooks.get(issue.getUserId());
                    borroweds.add(order);
                    Integer count = availableBooks.get(order.getBookId()).getQuantity();
                    availableBooks.get(order.getBookId()).setQuantity(count-1);
                    issuedBooks.put(issue.getUserId(), borroweds);
                }
            }

        }

    }
}
