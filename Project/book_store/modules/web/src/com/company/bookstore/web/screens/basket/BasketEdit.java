package com.company.bookstore.web.screens.basket;

import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Basket;

@UiController("bookstore_Basket.edit")
@UiDescriptor("basket-edit.xml")
@EditedEntityContainer("basketDc")
@LoadDataBeforeShow
public class BasketEdit extends StandardEditor<Basket> {
}