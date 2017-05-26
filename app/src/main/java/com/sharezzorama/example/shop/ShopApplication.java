package com.sharezzorama.example.shop;

import android.app.Application;

import com.sharezzorama.example.shop.cart.CartContract;
import com.sharezzorama.example.shop.cart.CartPresenter;
import com.sharezzorama.example.shop.category.CategoryContract;
import com.sharezzorama.example.shop.category.CategoryPresenter;
import com.sharezzorama.example.shop.data.cart.CartKeeper;
import com.sharezzorama.example.shop.data.cart.CartSimpleDataSource;
import com.sharezzorama.example.shop.data.catalog.category.CategoryRepository;
import com.sharezzorama.example.shop.data.catalog.category.external.CategoryFakeExternalDataSource;
import com.sharezzorama.example.shop.data.catalog.category.local.CategoryLocalDataSource;
import com.sharezzorama.example.shop.data.catalog.item.ItemRepository;
import com.sharezzorama.example.shop.data.catalog.item.external.ItemFakeExternalDataSource;
import com.sharezzorama.example.shop.data.catalog.item.local.ItemLocalDataSource;
import com.sharezzorama.example.shop.items.ItemContract;
import com.sharezzorama.example.shop.items.ItemsPresenter;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ShopApplication extends Application {
    private static ShopApplication sAppInstance;
    private CartKeeper mCartKeeper;
    private CartContract.Presenter mCartPresenter;
    private CategoryContract.Presenter mCategoryPresenter;
    private Map<Long, ItemContract.Presenter> mItemPresenters;

    public static ShopApplication getInstance() {
        return sAppInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        sAppInstance = this;
        mCartKeeper = new CartKeeper(CartSimpleDataSource.getInstance());
        mItemPresenters = new HashMap<>();
    }

    public CartKeeper getCartKeeper() {
        return mCartKeeper;
    }

    public CartContract.Presenter getCartPresenter() {
        if (mCartPresenter == null) {
            mCartPresenter = new CartPresenter(mCartKeeper.getDataSource());
        }
        return mCartPresenter;
    }

    public CategoryContract.Presenter getCategoryPresenter() {
        if (mCategoryPresenter == null) {
            mCategoryPresenter = new CategoryPresenter(
                    CategoryRepository.getInstance(CategoryFakeExternalDataSource.getInstance(),
                            CategoryLocalDataSource.getInstance(this)));
        }
        return mCategoryPresenter;
    }

    public ItemContract.Presenter getItemPresenter(long categoryId) {
        if (mItemPresenters.get(categoryId) == null) {
            mItemPresenters.put(categoryId,
                    new ItemsPresenter(ItemRepository.getInstance(ItemFakeExternalDataSource.getInstance(),
                    ItemLocalDataSource.getInstance(this)),
                    categoryId));
        }
        return mItemPresenters.get(categoryId);
    }

}
