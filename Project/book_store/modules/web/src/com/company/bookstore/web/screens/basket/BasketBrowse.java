package com.company.bookstore.web.screens.basket;

import com.company.bookstore.entity.User;
import com.company.bookstore.service.BasketService;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.RemoveOperation;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionContainer;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.model.DataComponents;
import com.haulmont.cuba.gui.screen.*;
import com.company.bookstore.entity.Basket;
import org.slf4j.Logger;

import javax.inject.Inject;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@UiController("bookstore_Basket.browse")
@UiDescriptor("basket-browse.xml")
@LookupComponent("basketsTable")
@LoadDataBeforeShow
public class BasketBrowse extends StandardLookup<Basket> {

    @Inject
    private UserSessionSource userSessionSource;
    @Inject
    private CollectionLoader<Basket> basketsDl;
    @Inject
    private GroupTable<Basket> basketsTable;
    @Inject
    private Button buyBtn;
    @Inject
    private Logger log;
    @Inject
    private RemoveOperation removeOperation;
    @Inject
    private Notifications notifications;

    User currUser;

    @Subscribe
    public void onInit(InitEvent event) {
        currUser = (User) userSessionSource.getUserSession().getCurrentOrSubstitutedUser();
        if (!Objects.equals(currUser.getId().toString(), "60885987-1b61-4247-94c7-dff348347f93")) {
            basketsDl.setParameter("userId", currUser);
        }
        buyBtn.setEnabled(false);
    }

    @Subscribe("buyBtn")
    public void onBuyBtnClick(Button.ClickEvent event) {
        removeOperation.builder(basketsTable)
                .beforeActionPerformed(beforeActionPerformedEvent  -> {
                    List<Basket> selectedItems = beforeActionPerformedEvent.getItems();
                    boolean userHaveEnoughMoney = selectedItems.stream().allMatch(basket ->
                            basket.getBook().getPrice() <= currUser.getBalance());
                    if (userHaveEnoughMoney) {
                        currUser.setBalance(currUser.getBalance() - selectedItems.get(0).getBook().getPrice());
                        notifications.create()
                                .withType(Notifications.NotificationType.HUMANIZED)
                                .withCaption("Успех!")
                                .withDescription("Книга успешно куплена")
                                .show();
                    }
                    else {
                        beforeActionPerformedEvent.preventAction();
                        notifications.create()
                                .withType(Notifications.NotificationType.WARNING)
                                .withCaption("Ошибка!")
                                .withDescription("Книга успешно куплена")
                                .show();
                    }
                })
                .withConfirmation(false)
                .remove();
    }

    @Subscribe("basketsTable")
    public void onBasketsTableSelection(Table.SelectionEvent<Basket> event) {
        buyBtn.setEnabled(!event.getSelected().isEmpty());
    }

    
}