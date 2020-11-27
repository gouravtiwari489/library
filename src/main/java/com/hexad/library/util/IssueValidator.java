package com.hexad.library.util;

import com.hexad.library.exception.LibraryException;
import org.springframework.stereotype.Service;

@Service
public class IssueValidator {

    public void validateBooksPerUser(){
        if(true){
            throw new LibraryException("1",
                    "Each User has a borrowing limit of 2 books at any point of time","001");
        }
    }


}
