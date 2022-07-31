package com.jpabook.jpashop.domain.delivery;

public enum DeliveryStatus {
    READY("READY"), COMP("DELIVERYOK"), CANCEL("ORDERCANCEL");

    private final String msg;

    DeliveryStatus(String msg){
        this.msg = msg;
    }

    String getMessage(){
        return msg;
    }
}
