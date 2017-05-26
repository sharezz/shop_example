package com.sharezzorama.example.shop.cart;


import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;

public class CartLinkedHashMap extends LinkedHashMap<Item, Integer> {

    public Item getItemByPosition(int pos) {
        Map.Entry<Item, Integer> entry = this.getEntry(pos);
        if (entry == null) {
            return null;
        }
        return entry.getKey();
    }

    public int getItemPosition(Item item) {
        Set<Entry<Item, Integer>> entries = entrySet();
        int i = 0;

        for (Map.Entry<Item, Integer> entry : entries) {
            if (entry.getKey().equals(item)) {
                return i;
            }
            i++;
        }

        return -1;
    }

    private Map.Entry<Item, Integer> getEntry(int pos) {
        Set<Entry<Item, Integer>> entries = entrySet();
        int i = 0;

        for (Map.Entry<Item, Integer> entry : entries) {
            if (i++ == pos) {
                return entry;
            }
        }

        return null;
    }
}
