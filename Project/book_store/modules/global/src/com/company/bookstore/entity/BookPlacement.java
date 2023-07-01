package com.company.bookstore.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import groovyjarjarpicocli.CommandLine;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "BOOKSTORE_BOOK_PLACEMENT")
@Entity(name = "bookstore_BookPlacement")
@NamePattern("count: %s|count")
public class BookPlacement extends StandardEntity {
    private static final long serialVersionUID = 5991205918098334682L;

    @NotNull
    @Column(name = "COUNT")
    private Integer count;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "STORE_ID")
    private Store store;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Integer getCount() {
        return count;
    }

    public Store getStore() {
        return store;
    }
}