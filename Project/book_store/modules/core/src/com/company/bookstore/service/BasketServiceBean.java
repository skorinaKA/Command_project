package com.company.bookstore.service;

import com.company.bookstore.entity.Basket;
import com.haulmont.cuba.core.EntityManager;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import com.haulmont.cuba.core.global.UserSessionSource;
import com.haulmont.cuba.security.entity.User;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.List;

@Service(BasketService.NAME)
public class BasketServiceBean implements BasketService {


}