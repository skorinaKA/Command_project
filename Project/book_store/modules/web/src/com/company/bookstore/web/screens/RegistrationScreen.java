package com.company.bookstore.web.screens;

import com.company.bookstore.service.RegistrationService;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.validation.MethodParametersValidationException;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.*;
import com.haulmont.cuba.gui.screen.Screen;
import com.haulmont.cuba.gui.screen.Subscribe;
import com.haulmont.cuba.gui.screen.UiController;
import com.haulmont.cuba.gui.screen.UiDescriptor;

import javax.inject.Inject;
import javax.inject.Named;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@UiController("bookstore_RegistrationScreen")
@UiDescriptor("registration-screen.xml")
public class RegistrationScreen extends Screen {

    @Inject
    private TextField<String> lastNameField;
    @Inject
    private TextField<String> firstNameField;
    @Inject
    private TextField<String> middleNameField;

    @Inject
    private DateField<LocalDate> dateField;

    @Inject
    private TextField<String> loginField;
    @Inject
    private PasswordField passwordField;

    @Inject
    private RegistrationService registrationService;
    @Inject
    private Notifications notifications;
    @Inject
    private Messages messages;

    @Subscribe("okBtn")
    public void onOkBtnClick(Button.ClickEvent event) {
        try {
            registrationService.userRegistration(getLogin(), getPassword(),
                                                 getLastNameField(),getFirstNameField(),getMiddleNameField(),
                                                 getDateField());

            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption("Created user " + getLogin())
                    .show();

            close(WINDOW_COMMIT_AND_CLOSE_ACTION);
        } catch (MethodParametersValidationException e) {
            notifications.create(Notifications.NotificationType.TRAY)
                    .withCaption(
                            messages.getMessage(
                                    "com.company.sample.validation",
                                    "UserExistsValidator.message"))
                    .show();
        }
    }

    @Subscribe("cancelBtn")
    public void onCancelBtnClick(Button.ClickEvent event) {
        close(WINDOW_CLOSE_ACTION);
    }
    
    /**
     * @return entered login
     */
    public String getLogin() {
        return loginField.getValue();
    }

    /**
     * @return entered password
     */
    public String getPassword() {
        return passwordField.getValue();
    }

    /**
     * @return last name
     */
    public String getLastNameField() {
        return lastNameField.getValue();
    }

    /**
     * @return first name
     */
    public String getFirstNameField() {
        return firstNameField.getValue();
    }

    /**
     * @return middle name
     */
    public String getMiddleNameField() {
        return middleNameField.getValue();
    }

    /**
     * @return date of birth
     */
    public LocalDate getDateField() {
        return dateField.getValue();
    }
}