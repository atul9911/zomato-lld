package com.example.service;

import com.example.enums.SearchStrategyType;
import com.example.exception.CustomException;
import com.example.model.Restaurant;
import com.example.strategy.ItemSearchStrategy;
import com.example.strategy.LocationStrategy;
import com.example.strategy.SearchStrategy;

import java.util.List;

public class CatalogService {
    RestaurantService restaurantService;

    public CatalogService(RestaurantService restaurantService) {
        this.restaurantService = restaurantService;
    }

    public List<Restaurant> searchRestaurant(String searchKey, SearchStrategyType searchStrategyType) {
        SearchStrategy searchStrategy;
        switch (searchStrategyType) {
            case ITEM:
                searchStrategy = new ItemSearchStrategy(restaurantService);
                break;
            case LOCATION:
                searchStrategy = new LocationStrategy(restaurantService);
                break;
            default:
                throw new CustomException("Invalid Search Strategy");
        }
        return searchStrategy.apply(searchKey);
    }
}
