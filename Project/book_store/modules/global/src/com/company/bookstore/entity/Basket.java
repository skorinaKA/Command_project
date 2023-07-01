package com.company.bookstore.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Table(name = "BOOKSTORE_BASKET")
@Entity(name = "bookstore_Basket")
@NamePattern("%s|user")
public class Basket extends StandardEntity {
    private static final long serialVersionUID = 6145929935260163644L;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private com.haulmont.cuba.security.entity.User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "BOOK_ID")
    private Book book;

    @NotNull
    @Column(name = "COUNT")
    private Integer count;

    public void setBook(Book book) {
        this.book = book;
    }

    public Book getBook() {
        return book;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    public Integer getCount() {
        return count;
    }

    public void setUser(com.haulmont.cuba.security.entity.User user) {
        this.user = user;
    }

    public com.haulmont.cuba.security.entity.User getUser() {
        return user;
    }
}