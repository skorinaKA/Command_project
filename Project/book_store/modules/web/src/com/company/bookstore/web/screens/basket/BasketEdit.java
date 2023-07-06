package com.company.bookstore.web.screens.basket;

import com.haulmont.cuba.core.config.defaults.Default;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.InstanceContainer;
import com.haulmont.cuba.gui.model.InstanceLoader;
import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Basket;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;

@UiController("bookstore_Basket.edit")
@UiDescriptor("basket-edit.xml")
@EditedEntityContainer("basketDc")
@LoadDataBeforeShow
public class BasketEdit extends StandardEditor<Basket> {

    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private LookupField<User> userField;

    @Inject
    private Logger log;
    @Inject
    private CollectionLoader<com.company.bookstore.entity.User> userDl;
    com.company.bookstore.entity.User currUser;

    @Subscribe
    public void onInit(InitEvent event) {
        log.info("BasketEdit: Событие onInit началось");
        currUser = (com.company.bookstore.entity.User) userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        if (!Objects.equals(currUser.getId().toString(), "60885987-1b61-4247-94c7-dff348347f93")) {
            userDl.setParameter("userId", currUser.getId());
        }
    }

    @Subscribe("userField")
    public void onUserFieldValueChange(HasValue.ValueChangeEvent<User> event) {
        log.info("BasketEdit: onUserFieldValueChange: " + ((event.getValue() != null) ? event.getValue().getId().toString() : "NULL"));
    }







}