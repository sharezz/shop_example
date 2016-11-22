package com.sharezzorama.example.shop;

import android.app.Application;

import com.sharezzorama.example.shop.data.cart.CartKeeper;
import com.sharezzorama.example.shop.data.cart.CartSimpleDataSource;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ShopApplication extends Application {
    private CartKeeper mCartKeeper;

    @Override
    public void onCreate() {
        super.onCreate();
        mCartKeeper = new CartKeeper(CartSimpleDataSource.getInstance());
    }

    public CartKeeper getCartKeeper() {
        return mCartKeeper;
    }

}
