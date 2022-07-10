package com.jpabook.jpashop.domain.delivery;

public enum DeliveryStatus {
    READY("준비 중"), COMP("배송 완료");

    private final String msg;

    DeliveryStatus(String msg){
        this.msg = msg;
    }

    String getMessage(){
        return msg;
    }
}
