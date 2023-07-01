package com.company.bookstore.web.screens.store;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Store;

@UiController("bookstore_Store.browse")
@UiDescriptor("store-browse.xml")
@LookupComponent("storesTable")
@LoadDataBeforeShow
public class StoreBrowse extends StandardLookup<Store> {
}