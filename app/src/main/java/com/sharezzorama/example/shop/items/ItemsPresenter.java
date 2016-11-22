package com.sharezzorama.example.shop.items;

import com.sharezzorama.example.shop.data.catalog.item.Item;
import com.sharezzorama.example.shop.data.catalog.item.ItemDataSource;
import com.sharezzorama.example.shop.data.catalog.item.ItemRepository;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemsPresenter implements ItemContract.Presenter {
    private ItemContract.View mItemsView;
    private Long mCategoryId;
    private ItemRepository mItemRepository;

    public ItemsPresenter(ItemRepository mItemRepository, ItemContract.View mItemsView, Long categoryId) {
        this.mItemRepository = mItemRepository;
        this.mItemsView = mItemsView;
        mCategoryId = categoryId;
        mItemsView.setPresenter(this);
    }

    @Override
    public void loadItems() {
        mItemRepository.getItems(mCategoryId, new ItemDataSource.LoadItemsCallback() {
            @Override
            public void onItemsLoaded(List<Item> items) {
                mItemsView.showItems(items);
            }

            @Override
            public void onDataNotAvailable() {
                mItemsView.showNoItems();
            }
        });
    }

    @Override
    public void start() {
        loadItems();
    }
}
