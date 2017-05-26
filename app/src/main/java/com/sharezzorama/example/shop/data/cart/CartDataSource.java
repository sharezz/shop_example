package com.sharezzorama.example.shop.data.cart;

import com.sharezzorama.example.shop.cart.CartLinkedHashMap;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface CartDataSource {

    boolean add(Item item);

    int edit(Item item, int count);

    int remove(Item item);

    CartLinkedHashMap getAll();
}
