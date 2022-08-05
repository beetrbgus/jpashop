package com.jpabook.jpashop.exception;

public class NotFoundItem extends RuntimeException{
    public NotFoundItem(String message) {
        super(message);
    }
}
