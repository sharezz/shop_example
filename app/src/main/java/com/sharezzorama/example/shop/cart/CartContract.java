package com.sharezzorama.example.shop.cart;

import com.sharezzorama.example.shop.mvp.BaseView;
import com.sharezzorama.example.shop.data.catalog.item.Item;

import java.util.Map;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface CartContract {

    interface View extends BaseView {

        void itemAdded(int pos);

        void itemUpdated(int pos);

        void itemRemoved(int pos);

        void showAll(CartLinkedHashMap data);
    }

    interface Presenter extends com.sharezzorama.example.shop.mvp.Presenter<View> {

        void addOrRemove(Item item);

        void increaseCount(Item item);

        void decreaseCount(Item item);

        void remove(Item item);

        void getAll();

    }

    public class CartView implements View{
        @Override
        public void itemAdded(int pos) {

        }

        @Override
        public void itemUpdated(int pos) {

        }

        @Override
        public void itemRemoved(int pos) {

        }

        @Override
        public void showAll(CartLinkedHashMap data) {

        }
    }
}
