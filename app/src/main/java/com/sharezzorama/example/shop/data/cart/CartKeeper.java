package com.sharezzorama.example.shop.data.cart;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CartKeeper {
    private CartDataSource mCartDataSource;

    public CartKeeper(CartDataSource mCartDataSource) {
        this.mCartDataSource = mCartDataSource;
    }

    public CartDataSource getDataSource() {
        return mCartDataSource;
    }
}
