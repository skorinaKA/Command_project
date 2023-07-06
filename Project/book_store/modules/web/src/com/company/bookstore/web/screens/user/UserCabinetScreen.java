package com.company.bookstore.web.screens.user;

import com.company.bookstore.entity.User;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.TextField;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;

@UiController("bookstore_UserCabinetScreen")
@UiDescriptor("user-cabinet-screen.xml")
public class UserCabinetScreen extends Screen {

    @Inject
    private TextField<String> lastNameTextField;
    @Inject
    private TextField<String> firstNameTextField;
    @Inject
    private UserSessionSource userSessionSource;

    @Inject
    private TextField<String> balanceTextField;

    User currUser;

    @Subscribe
    public void onInit(InitEvent event) {
        currUser = (User) userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        lastNameTextField.setValue(currUser.getLastName());
        firstNameTextField.setValue(currUser.getFirstName());
        balanceTextField.setValue(currUser.getBalance().toString());
    }


}