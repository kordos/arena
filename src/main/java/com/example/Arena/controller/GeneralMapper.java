package com.example.Arena.controller;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper {

    /**
     * Copy properties from one object to another.
     * It can't copy properties if toObject uses lombok.Value
     */
    <S, T> T mapSimple(S fromObject, T toObject) {
        BeanUtils.copyProperties(fromObject, toObject);
        return toObject;
    }
}
