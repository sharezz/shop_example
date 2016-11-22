package com.sharezzorama.example.shop.data.catalog.item;

import android.util.Log;

import com.sharezzorama.example.shop.data.catalog.item.external.ItemFakeExternalDataSource;
import com.sharezzorama.example.shop.data.catalog.item.local.ItemLocalDataSource;

import java.text.MessageFormat;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemRepository implements ItemDataSource {
    private static ItemRepository INSTANCE;
    private ItemLocalDataSource mLocalDataSource;
    private ItemFakeExternalDataSource mExternalDataSource;

    private ItemRepository(ItemFakeExternalDataSource mExternalDataSource, ItemLocalDataSource mLocalDataSource) {
        this.mExternalDataSource = mExternalDataSource;
        this.mLocalDataSource = mLocalDataSource;
    }

    public static ItemRepository getInstance(ItemFakeExternalDataSource externalDataSource, ItemLocalDataSource localDataSource) {
        if (INSTANCE == null) {
            INSTANCE = new ItemRepository(externalDataSource, localDataSource);
        }
        return INSTANCE;
    }

    @Override
    public void getItems(final Long categoryId, final LoadItemsCallback callback) {
        mLocalDataSource.getItems(categoryId, new LoadItemsCallback() {
            @Override
            public void onItemsLoaded(List<Item> items) {
                callback.onItemsLoaded(items);
            }

            @Override
            public void onDataNotAvailable() {
                getItemsFromExternalDataSource(categoryId, callback);
            }
        });
    }

    @Override
    public void saveItems(List<Item> items, SaveItemsCallback callback) {
        mLocalDataSource.saveItems(items, callback);
    }

    private void getItemsFromExternalDataSource(Long categoryId, final LoadItemsCallback callback) {
        mExternalDataSource.getItems(categoryId, new LoadItemsCallback() {
            @Override
            public void onItemsLoaded(List<Item> items) {
                saveItems(items, new SaveItemsCallback() {
                    @Override
                    public void onItemsSaved(List<Item> items) {
                        Log.d(this.getClass().getSimpleName(), MessageFormat.format("Saved {0} items", items.size()));
                    }
                });
                callback.onItemsLoaded(items);
            }

            @Override
            public void onDataNotAvailable() {
                callback.onDataNotAvailable();
            }
        });
    }
}
