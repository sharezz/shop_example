package com.sharezzorama.example.shop.data.cart;

import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartSimpleDataSource implements CartDataSource {

    private Map<Item, Integer> mCartMap = new HashMap<>();

    private static CartSimpleDataSource INSTANCE;

    private CartSimpleDataSource() {
    }

    public static CartSimpleDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new CartSimpleDataSource();
        }
        return INSTANCE;
    }

    @Override
    public boolean add(Item item) {
        if (mCartMap.containsKey(item)) {
            return false;
        } else {
            mCartMap.put(item, 1);
            return true;
        }
    }

    @Override
    public void edit(Item item, int count) {
        mCartMap.put(item, count);
    }

    @Override
    public void remove(Item item) {
mCartMap.remove(item);
    }

    @Override
    public Map<Item, Integer> getAll() {
        return mCartMap;
    }
}
