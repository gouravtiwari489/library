package com.hexad.library.repository;

import com.hexad.library.exception.LibraryException;
import com.hexad.library.model.Borrowed;
import com.hexad.library.model.Issue;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class IssueRepository {

    private Map<Integer, List<Borrowed>> issuedBooks = new HashMap<>();

    public Issue save(Issue issue){
       return null;
    }

    public Map<Integer, List<Borrowed>> getAllIssuedBooks(){
        return issuedBooks;
    }

    public Map<Integer, List<Borrowed>> getAll() {
        return issuedBooks;
    }
}
