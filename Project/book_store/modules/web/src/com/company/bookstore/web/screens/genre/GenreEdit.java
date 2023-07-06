package com.company.bookstore.web.screens.genre;

import com.haulmont.cuba.gui.Route;
import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Genre;

@UiController("bookstore_Genre.edit")
@UiDescriptor("genre-edit.xml")
@EditedEntityContainer("genreDc")
@LoadDataBeforeShow
@Route(value = "genres/edit", parentPrefix = "pets")
public class GenreEdit extends StandardEditor<Genre> {}