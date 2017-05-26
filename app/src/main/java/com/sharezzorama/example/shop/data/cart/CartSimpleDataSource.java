package com.sharezzorama.example.shop.data.cart;

import com.sharezzorama.example.shop.cart.CartLinkedHashMap;
import com.sharezzorama.example.shop.data.catalog.item.Item;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartSimpleDataSource implements CartDataSource {

    private CartLinkedHashMap mCartMap;

    private static CartSimpleDataSource INSTANCE;

    private CartSimpleDataSource() {
        mCartMap = new CartLinkedHashMap();
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
    public int edit(Item item, int count) {
        int itemPosition = mCartMap.getItemPosition(item);
        mCartMap.put(item, count);
        return itemPosition;
    }

    @Override
    public int remove(Item item) {
        int itemPosition = mCartMap.getItemPosition(item);
        mCartMap.remove(item);
        return itemPosition;
    }

    @Override
    public CartLinkedHashMap getAll() {
        return mCartMap;
    }
}
