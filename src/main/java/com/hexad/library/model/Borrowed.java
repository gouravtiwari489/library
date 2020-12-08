package com.hexad.library.model;

import lombok.Data;

import java.util.Date;

@Data
public class Borrowed {
    private Integer bookId;
    private Date issueDate;
    private Date dueDate;
    private Date returnDate;
}
