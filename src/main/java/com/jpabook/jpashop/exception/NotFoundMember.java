package com.jpabook.jpashop.exception;

public class NotFoundMember extends RuntimeException{
    public NotFoundMember(String message) {
        super(message);
    }
}
