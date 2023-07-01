package com.company.bookstore.web.screens.basket;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Basket;

@UiController("bookstore_Basket.browse")
@UiDescriptor("basket-browse.xml")
@LookupComponent("basketsTable")
@LoadDataBeforeShow
public class BasketBrowse extends StandardLookup<Basket> {
}