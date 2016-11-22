package com.sharezzorama.example.shop.cart;

import com.sharezzorama.example.shop.BasePresenter;
import com.sharezzorama.example.shop.BaseView;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface CartContract {

    interface View extends BaseView<Presenter> {

        void itemAdded(Item item);

        void itemUpdated(Item item);

        void itemRemoved(Item item);

        void showAll(Map<Item, Integer> data);

        void updateAll(Map<Item, Integer> data);
    }

    interface Presenter extends BasePresenter {

        void addOrRemove(Item item);

        void increaseCount(Item item);

        void decreaseCount(Item item);

        void remove(Item item);

        void getAll();

    }
}
