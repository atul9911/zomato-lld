package com.example.strategy;

import com.example.model.Restaurant;

import java.util.List;

public interface SearchStrategy {
    public List<Restaurant> apply(String searchKeyWord);
}
