package com.company.bookstore.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import org.eclipse.persistence.platform.database.oracle.annotations.NamedPLSQLStoredFunctionQueries;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.time.LocalTime;

@Table(name = "BOOKSTORE_STORE")
@Entity(name = "bookstore_Store")
@NamePattern("%s: %s. %s|name, address, status")
public class Store extends StandardEntity {
    private static final long serialVersionUID = 3324239723442394670L;

    @NotNull
    @Column(name = "NAME")
    private String name;

    @NotNull
    @Column(name = "ADDRESS")
    private String address;

    @NotNull
    @Column(name = "OPENNING_TIME")
    private LocalTime openning_time;

    @NotNull
    @Column(name = "CLOSING_TIME")
    private LocalTime closing_time;

    @NotNull
    @Column(name = "STATUS")
    private Boolean status;

    public void setName(String name) {
        this.name = name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setOpenning_time(LocalTime openning_time) {
        this.openning_time = openning_time;
    }

    public void setClosing_time(LocalTime closing_time) {
        this.closing_time = closing_time;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public String getAddress() {
        return address;
    }

    public LocalTime getOpenning_time() {
        return openning_time;
    }

    public LocalTime getClosing_time() {
        return closing_time;
    }

    public Boolean getStatus() {
        return status;
    }
}