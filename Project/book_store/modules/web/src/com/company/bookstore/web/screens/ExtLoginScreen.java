package com.company.bookstore.web.screens;

import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.security.global.UserSession;
import com.haulmont.cuba.web.app.login.LoginScreen;
import org.slf4j.Logger;

import javax.inject.Inject;


@UiController("loginScreen")
@UiDescriptor("ext-login-screen.xml")
public class ExtLoginScreen extends LoginScreen {

    @Inject
    private Button loginButton;
    @Inject
    private Logger log;

    @Subscribe("registerBtn")
    public void onRegisterBtnClick(Button.ClickEvent event) {
        log.info("Окно не создано");
        // Create "Register" screen with dialog mode
        RegistrationScreen registrationScreen = screens.create(RegistrationScreen.class, OpenMode.DIALOG);
        log.info("Окно создано");

        // Add a listener to be notified when the "Register" screen is closed with COMMIT_ACTION_ID
        registrationScreen.addAfterCloseListener(afterCloseEvent -> {
            CloseAction closeAction = afterCloseEvent.getCloseAction();
            if (closeAction == WINDOW_COMMIT_AND_CLOSE_ACTION) {
                // Set the new registered user credentials to login and password fields
                loginField.setValue(registrationScreen.getLogin());
                passwordField.setValue(registrationScreen.getPassword());
                // Set focus on "Submit" button
                loginButton.focus();
            }
        });

        // Show "Register" screen
        registrationScreen.show();
    }

    
}