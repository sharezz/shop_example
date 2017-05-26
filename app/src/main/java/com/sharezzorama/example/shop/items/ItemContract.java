package com.sharezzorama.example.shop.items;

import com.sharezzorama.example.shop.mvp.BaseView;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface ItemContract {

    interface View extends BaseView {

        void showItems(List<Item> items);

        void showNoItems();
    }

    interface Presenter extends com.sharezzorama.example.shop.mvp.Presenter<View> {

        void loadItems();

    }
}
