package com.company.bookstore.web.screens.bookplacement;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.BookPlacement;

@UiController("bookstore_BookPlacement.browse")
@UiDescriptor("book-placement-browse.xml")
@LookupComponent("bookPlacementsTable")
@LoadDataBeforeShow
public class BookPlacementBrowse extends StandardLookup<BookPlacement> {
}