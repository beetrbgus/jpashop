package com.jpabook.jpashop.domain.order;

public enum OrderStatus {
    ORDER("ORDERED"),DELEIVERY_OK("DELIEVERYOK") ,CANCEL("ORDERCANCEL");

    private final String msg;

    OrderStatus(String msg){
        this.msg = msg;
    }

    String getMessage(){
        return msg;
    }
}
