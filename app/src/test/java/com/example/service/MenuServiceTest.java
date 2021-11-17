package com.example.service;

import com.example.exception.CustomException;
import com.example.model.Menu;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MenuServiceTest {
    MenuService menuService;

    @BeforeEach
    public void init() {
        menuService = new MenuService();
    }

    @Test
    void createMenu() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", 100);
        map.put("Chole_Bhutre", 100);

        Menu menu = menuService.createMenu(map);
        assertNotNull(menu);
        assertEquals(menu.getItems().get("Pav_Bhaji"), 100);
        assertEquals(menu.getItems().get("Chole_Bhutre"), 100);
    }

    @Test
    void createMenuWithNegativePrice() {
        Map<String, Integer> map = new HashMap<>();
        map.put("Pav_Bhaji", -98);
        map.put("Chole_Bhutre", 100);

        CustomException thrown = assertThrows(
            CustomException.class,
            () -> menuService.createMenu(map),
            "Expected doThing() to throw, but it didn't"
        );

        assertTrue(thrown.getMessage().contains("Price Should be positive"));
    }
}
