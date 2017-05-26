package com.sharezzorama.example.shop.mvp;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface Presenter<V extends BaseView> {

    void attachView(V view);

    void detachView(V view);
}
