package com.company.bookstore.core.role;

import com.company.bookstore.entity.*;
import com.haulmont.cuba.security.app.role.AnnotatedRoleDefinition;
import com.haulmont.cuba.security.app.role.annotation.EntityAccess;
import com.haulmont.cuba.security.app.role.annotation.EntityAttributeAccess;
import com.haulmont.cuba.security.app.role.annotation.Role;
import com.haulmont.cuba.security.app.role.annotation.ScreenAccess;
import com.haulmont.cuba.security.entity.EntityOp;
import com.haulmont.cuba.security.role.EntityAttributePermissionsContainer;
import com.haulmont.cuba.security.role.EntityPermissionsContainer;
import com.haulmont.cuba.security.role.ScreenPermissionsContainer;

@Role(name = CustomerRole.NAME, isDefault = true)
public class CustomerRole extends AnnotatedRoleDefinition {
    public final static String NAME = "Customer";

    @EntityAccess(entityClass = User.class, operations = {EntityOp.READ, EntityOp.UPDATE})
    @EntityAccess(entityClass = Basket.class, operations = {EntityOp.CREATE, EntityOp.DELETE, EntityOp.READ})
    @EntityAccess(entityClass = Book.class, operations = {EntityOp.READ})
    @EntityAccess(entityClass = BookPlacement.class, operations = {EntityOp.READ})
    @Override
    public EntityPermissionsContainer entityPermissions() {
        return super.entityPermissions();
    }
    @EntityAttributeAccess(entityName = "*", view = "*")
    @EntityAttributeAccess(entityClass = User.class,
            modify = {"login", "password","first_name", "last_name", "middle_name", "email", "balance", "date_of_birth"})
    @Override
    public EntityAttributePermissionsContainer entityAttributePermissions() {
        return super.entityAttributePermissions();
    }

    @ScreenAccess(screenIds = {"settings"})
    @Override
    public ScreenPermissionsContainer screenPermissions() {
        return super.screenPermissions();
    }
}
