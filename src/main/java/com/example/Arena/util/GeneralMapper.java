package com.example.Arena.util;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component
public class GeneralMapper {

    /**
     * Copy properties from one object to another.
     * It can't copy properties if toObject uses lombok.Value(without setters)
     * It uses getters from one object and setters in another.
     */
    public <S, T> T mapSimple(S fromObject, T toObject) {
        BeanUtils.copyProperties(fromObject, toObject);
        return toObject;
    }
}
