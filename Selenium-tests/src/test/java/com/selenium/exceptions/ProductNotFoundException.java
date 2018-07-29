package com.selenium.exceptions;

public class ProductNotFoundException extends Exception {

    private static final long serialVersionUID = -2590367716941915996L;

    private String name;

    public ProductNotFoundException(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
