package com.hexad.library.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Issue {

    private Integer userId;
    private List<Book> issuedBookIds;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;

}
