alter table BOOKSTORE_BASKET add constraint FK_BOOKSTORE_BASKET_ON_USER foreign key (USER_ID) references BOOKSTORE_USER(ID);
create index IDX_BOOKSTORE_BASKET_ON_USER on BOOKSTORE_BASKET (USER_ID);
