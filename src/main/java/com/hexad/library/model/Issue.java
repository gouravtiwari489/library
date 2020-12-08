package com.hexad.library.model;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class Issue {

    private Integer userId;
    private List<Borrowed> issueBooks;


}
