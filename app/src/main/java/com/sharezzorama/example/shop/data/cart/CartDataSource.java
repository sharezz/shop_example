package com.sharezzorama.example.shop.data.cart;

import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface CartDataSource {

    boolean add(Item item);

    void edit(Item item, int count);

    void remove(Item item);

    Map<Item, Integer> getAll();
}
