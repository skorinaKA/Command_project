package com.company.bookstore.web.screens.book;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Book;

@UiController("bookstore_Book.browse")
@UiDescriptor("book-browse.xml")
@LookupComponent("booksTable")
@LoadDataBeforeShow
public class BookBrowse extends StandardLookup<Book> {
}