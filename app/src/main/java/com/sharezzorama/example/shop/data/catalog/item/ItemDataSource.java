package com.sharezzorama.example.shop.data.catalog.item;

import java.util.List;

/**
 * Created by sharezzorama on 11/21/16.
 */

public interface ItemDataSource {

    interface LoadItemsCallback {

        void onItemsLoaded(List<Item> items);

        void onDataNotAvailable();
    }


    interface SaveItemsCallback {

        void onItemsSaved(List<Item> items);
    }

    void getItems(Long categoryId, LoadItemsCallback callback);

    void saveItems(List<Item> items, SaveItemsCallback callback);
}
