package com.company.bookstore.web.screens.bookplacement;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.BookPlacement;

@UiController("bookstore_BookPlacement.edit")
@UiDescriptor("book-placement-edit.xml")
@EditedEntityContainer("bookPlacementDc")
@LoadDataBeforeShow
public class BookPlacementEdit extends StandardEditor<BookPlacement> {
}