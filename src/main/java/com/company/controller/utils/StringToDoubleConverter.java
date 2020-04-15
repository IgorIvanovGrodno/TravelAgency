package com.company.controller.utils;

import org.springframework.core.convert.converter.Converter;

public class StringToDoubleConverter implements Converter<String, Double> {

    @Override
    public Double convert(String s) {
        return Double.valueOf(s);
    }
}
