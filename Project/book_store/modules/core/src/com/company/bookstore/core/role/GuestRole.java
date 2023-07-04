package com.company.bookstore.core.role;

import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.app.role.annotation.ScreenComponentAccess;
import com.haulmont.cuba.security.role.ScreenComponentPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = GuestRole.NAME)
public class GuestRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Guest";

    @ScreenAccess(screenIds = {"registration-screen.xml", "bookstore_RegistrationScreen"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
       return super.screenPermissions();
    }
}
