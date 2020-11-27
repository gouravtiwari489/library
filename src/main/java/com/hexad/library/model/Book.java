package com.hexad.library.model;

import lombok.Data;

import java.util.Objects;

@Data
public class Book {
    private Integer id;
    private String bookName;
    private String genre;
    private Integer price;
    private Integer quantity;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return id.equals(book.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
