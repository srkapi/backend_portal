package com.srkapi.order.api.dto;


import java.io.Serializable;

public enum TypeLoad implements Serializable {
    EUROPE(1),
    NATIONAL(2);

    private int value;

    TypeLoad(int i) {
        this.value = i;
    }
}