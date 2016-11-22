package com.sharezzorama.example.shop.items;

import com.sharezzorama.example.shop.BasePresenter;
import com.sharezzorama.example.shop.BaseView;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface ItemContract {

    interface View extends BaseView<Presenter> {

        void showItems(List<Item> items);

        void showNoItems();
    }

    interface Presenter extends BasePresenter {

        void loadItems();

    }
}
