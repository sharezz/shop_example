package com.sharezzorama.example.shop.items;

import com.sharezzorama.example.shop.mvp.BasePresenter;
import com.sharezzorama.example.shop.data.catalog.item.Item;
import com.sharezzorama.example.shop.data.catalog.item.ItemDataSource;
import com.sharezzorama.example.shop.data.catalog.item.ItemRepository;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemsPresenter extends BasePresenter<ItemContract.View> implements ItemContract.Presenter {
    private Long mCategoryId;
    private ItemRepository mItemRepository;

    public ItemsPresenter(ItemRepository mItemRepository, Long categoryId) {
        this.mItemRepository = mItemRepository;
        mCategoryId = categoryId;
    }

    @Override
    public void loadItems() {
        mItemRepository.getItems(mCategoryId, new ItemDataSource.LoadItemsCallback() {
            @Override
            public void onItemsLoaded(List<Item> items) {
                getView().showItems(items);
            }

            @Override
            public void onDataNotAvailable() {
                getView().showNoItems();
            }
        });
    }

    @Override
    protected Class<ItemContract.View> getViewClass() {
        return ItemContract.View.class;
    }
}
