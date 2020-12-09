package com.hexad.library.util;

import com.hexad.library.exception.LibraryException;
import com.hexad.library.model.Book;
import com.hexad.library.model.Borrowed;
import com.hexad.library.repository.BookRepository;
import com.hexad.library.repository.IssueRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public void isBookAlreadyAssignedToUser(List<Borrowed> existingIssuedBooks, Borrowed orderedBook, Integer userId) {
        for(Borrowed borrowed: existingIssuedBooks){
            if (borrowed.getBookId().equals(orderedBook.getBookId())){
                throw new LibraryException("103", "Book with id "+borrowed.getBookId()+" is already assigned to "+userId, "VALIDATION_ERROR");
            }
        }
    }


}
