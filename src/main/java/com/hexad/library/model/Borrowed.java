package com.hexad.library.model;

import lombok.Data;

@Data
public class Borrowed {
    private Integer bookId;
    private String issueDate;
    private String dueDate;
}
