package com.srkapi.common.service;

public interface Mapper<T,S> {

    S toDto(T model);


    T toModel(S dto);
}