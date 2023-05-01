package com.example.mukgen.domain.meal.entity;

import java.util.List;

public class Rice {
    private final List<String> items;

    public Rice(List<String> items) {
        this.items = items;
    }

    public List<String> getItems() {
        return items;
    }
}