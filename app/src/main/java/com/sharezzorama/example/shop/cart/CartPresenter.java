package com.sharezzorama.example.shop.cart;

import com.sharezzorama.example.shop.mvp.BasePresenter;
import com.sharezzorama.example.shop.data.cart.CartDataSource;
import com.sharezzorama.example.shop.data.catalog.item.Item;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartPresenter extends BasePresenter<CartContract.View> implements CartContract.Presenter {

    private final CartDataSource mDataSource;

    public CartPresenter(CartDataSource dataSource) {
        mDataSource = dataSource;
    }

    @Override
    public void addOrRemove(Item item) {
        if (mDataSource.getAll().containsKey(item)) {
            getView().itemRemoved(mDataSource.getAll().remove(item));
        } else {
            mDataSource.add(item);
            getView().itemAdded(mDataSource.getAll().size() - 1);
        }
    }

    @Override
    public void increaseCount(Item item) {
        Integer count = mDataSource.getAll().get(item);
        getView().itemUpdated(mDataSource.edit(item, count + 1));
    }

    @Override
    public void decreaseCount(Item item) {
        Integer count = mDataSource.getAll().get(item);
        if (count > 1) {
            getView().itemUpdated(mDataSource.edit(item, count - 1));
        }
    }

    @Override
    public void remove(Item item) {
        getView().itemRemoved(mDataSource.remove(item));
    }

    @Override
    public void getAll() {
        getView().showAll(mDataSource.getAll());
    }

    @Override
    protected Class<CartContract.View> getViewClass() {
        return CartContract.View.class;
    }
}
