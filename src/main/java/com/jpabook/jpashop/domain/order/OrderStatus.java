package com.jpabook.jpashop.domain.order;

public enum OrderStatus {
    ORDER("주문"),DELEIVERY_OK("배달 완료") ,CANCEL("취소");

    private final String msg;

    OrderStatus(String msg){
        this.msg = msg;
    }

    String getMessage(){
        return msg;
    }
}
