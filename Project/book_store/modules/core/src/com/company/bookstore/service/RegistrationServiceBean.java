package com.company.bookstore.service;

import com.company.bookstore.core.role.CustomerRole;
import com.company.bookstore.entity.User;
import com.haulmont.cuba.core.global.*;
import com.haulmont.cuba.security.entity.Group;
import com.haulmont.cuba.security.entity.Role;
import com.haulmont.cuba.security.entity.UserRole;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.time.LocalDate;
import java.util.UUID;

@Service(RegistrationService.NAME)
public class RegistrationServiceBean implements RegistrationService {

    private static final String COMPANY_GROUP_ID = "0fa2b1a5-1d68-4d69-9fbd-dff348347f93";
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(RegistrationServiceBean.class);

    @Inject
    private DataManager dataManager;

    @Inject
    private Metadata metadata;

    @Inject
    private PasswordEncryption passwordEncryption;

    @Override
    public RegistrationResult userRegistration(String login, String password,
                                               String lastName, String firstName, String middleName,
                                               LocalDate dateOfBirth) {

        Group group = dataManager.load(LoadContext.create(Group.class).setId(UUID.fromString(COMPANY_GROUP_ID)));
        log.info("Начало регистрации");
        com.company.bookstore.entity.User user = metadata.create(User.class);
        user.setLogin(login);
        user.setPassword(passwordEncryption.getPasswordHash(user.getId(), password));
        user.setBalance(0.0);
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setMiddleName(middleName);
        user.setDayOfBirth(dateOfBirth);
        user.setGroup(group);

        log.info("Предконец регистрации");
        dataManager.commit(new CommitContext(user));

        log.info("Конец регистрации");
        return new RegistrationResult(user);
    }
}