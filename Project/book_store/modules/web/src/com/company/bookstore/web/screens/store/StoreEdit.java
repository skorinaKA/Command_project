package com.company.bookstore.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Store;

@UiController("bookstore_Store.edit")
@UiDescriptor("store-edit.xml")
@EditedEntityContainer("storeDc")
@LoadDataBeforeShow
public class StoreEdit extends StandardEditor<Store> {
}