package com.sharezzorama.example.shop.data.catalog.item.external;

import android.util.Log;

import com.sharezzorama.example.shop.data.catalog.item.Item;
import com.sharezzorama.example.shop.data.catalog.item.ItemDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemFakeExternalDataSource implements ItemDataSource {

    private ItemFakeExternalDataSource() {
    }

    private static ItemFakeExternalDataSource INSTANCE;

    public static ItemFakeExternalDataSource getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new ItemFakeExternalDataSource();
        }
        return INSTANCE;
    }

    @Override
    public void getItems(Long categoryId, LoadItemsCallback callback) {
        Log.d(this.getClass().getSimpleName(), "getItems()");
        List<Item> items = new ArrayList<>();
        switch (categoryId.intValue()) {
            case 0:
                items.add(new Item(null, categoryId, "Телевизор Samsung 50\"", "Разрешение 3840x2160 (4K UHD), Поддержка SMART TV", 10000.0));
                items.add(new Item(null, categoryId, "Телевизор Sony 43\" ", "Разрешение 1920x1080,(Full HD),Поддержка SMART TV", 20000.0));
                items.add(new Item(null, categoryId, "Телевизор LG 65\" 65UH850V LED UHD Smart 3D Black", "Разрешение 3840x2160 (4K UHD),Поддержка SMART TV,Поддержка 3D,", 100500.0));
                break;
            case 1:
                items.add(new Item(null, categoryId, "Notebook Cloudbook Lenovo IdeaPad 100s", "11,6` 1366x768, Intel Atom Z3735F, RAM 2, SSD 32 ", 10000.0));
                items.add(new Item(null, categoryId, "Notebook Dell Inspiron 15.6 Series-3552", "15.6` 1366x768 | Intel Pentium N3700 | RAM 4 | HDD 500 ", 20000.0));
                items.add(new Item(null, categoryId, "Notebook Apple MacBook Air 13", "13,3` 1366x768 | Intel Core i5 5250U | RAM 8 | SSD 128", 30000.0));
                break;
            case 2:
                items.add(new Item(null, categoryId, "GSM Fly FF241 BLX-D-2.4-1.3-0 Black", "Экран 2.4\" TFT (320x240) | Батарея 2750 мАч | FM радио | 126 x 51 x 14.3 мм, 119 г ", 100.7));
                items.add(new Item(null, categoryId, "GSM Micromax Joy X1800 BLX-D-1.7-0.08-0 Black", "Экран 1.77\" TFT (128x160) | Батарея 750 мАч | Поддержка 2-х SIM-карт", 200.7));
                items.add(new Item(null, categoryId, "GSM Samsung GT-E1202ZKISKZ BLX-D-1.5-0-0 Keystone 2i Duos Black", "Экран 1.5\" TFT (128x128) | Батарея 800 мАч | Поддержка 2-х SIM-карт", 300.7));
                break;
        }
        callback.onItemsLoaded(items);
    }

    @Override
    public void saveItems(List<Item> items, SaveItemsCallback callback) {
        // Not required for the external data source.
    }
}
