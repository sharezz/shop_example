package com.sharezzorama.example.shop.cart;

import com.sharezzorama.example.shop.data.cart.CartKeeper;
import com.sharezzorama.example.shop.data.cart.CartDataSource;
import com.sharezzorama.example.shop.data.catalog.item.Item;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartPresenter implements CartContract.Presenter {

    private final CartDataSource mDataSource;
    private CartContract.View mCartView;

    public CartPresenter(CartKeeper cartKeeper, CartContract.View cartView) {
        mDataSource = cartKeeper.getDataSource();
        mCartView = cartView;
        cartView.setPresenter(this);
    }

    @Override
    public void addOrRemove(Item item) {
        if (mDataSource.getAll().containsKey(item)) {
            mDataSource.getAll().remove(item);
            mCartView.itemRemoved(item);
        } else {
            mDataSource.add(item);
            mCartView.itemAdded(item);
        }
    }

    @Override
    public void increaseCount(Item item) {
        Integer count = mDataSource.getAll().get(item);
        mDataSource.edit(item, count + 1);
        mCartView.updateAll(mDataSource.getAll());
        mCartView.itemUpdated(item);
    }

    @Override
    public void decreaseCount(Item item) {
        Integer count = mDataSource.getAll().get(item);
        if (count > 1) {
            mDataSource.edit(item, count - 1);
            mCartView.updateAll(mDataSource.getAll());
            mCartView.itemUpdated(item);
        }
    }

    @Override
    public void remove(Item item) {
        mDataSource.remove(item);
        mCartView.updateAll(mDataSource.getAll());
        mCartView.itemRemoved(item);
    }

    @Override
    public void getAll() {
        mCartView.showAll(mDataSource.getAll());
    }

    @Override
    public void start() {
        getAll();
    }
}
