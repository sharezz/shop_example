package com.sharezzorama.example.shop;

import android.support.v4.app.Fragment;

import com.sharezzorama.example.shop.data.cart.CartKeeper;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class BaseFragment extends Fragment {

    protected CartKeeper getCartKeeper() {
        return ((ShopApplication) getActivity().getApplication()).getCartKeeper();
    }
}
