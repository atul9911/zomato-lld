package com.example.service;

import com.example.exception.CustomException;
import com.example.model.Menu;

import java.util.HashMap;
import java.util.Map;

public class MenuService {
    Map<String, Menu> menuMap;

    public MenuService() {
        menuMap = new HashMap<>();
    }

    public Menu createMenu(Map<String, Integer> items) {
        Menu menu = new Menu(items);
        for (String item : items.keySet()) {
            if (items.get(item) <= 0) {
                throw new CustomException("Price Should be positive");
            }
        }
        menuMap.put(menu.getId(), menu);
        return menu;
    }
}
