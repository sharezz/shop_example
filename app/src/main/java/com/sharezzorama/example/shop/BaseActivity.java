package com.sharezzorama.example.shop;

import android.support.v7.app.AppCompatActivity;

import com.sharezzorama.example.shop.data.cart.CartKeeper;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class BaseActivity extends AppCompatActivity {

    protected CartKeeper getCartKeeper() {
        return ((ShopApplication) getApplication()).getCartKeeper();
    }

}
