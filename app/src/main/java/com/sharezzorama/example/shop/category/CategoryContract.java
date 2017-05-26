package com.sharezzorama.example.shop.category;

import com.sharezzorama.example.shop.mvp.BaseView;
import com.sharezzorama.example.shop.data.catalog.category.Category;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface CategoryContract {

    interface View extends BaseView {

        void showCategories(List<Category> categories);

        void showNoCategories();
    }

    interface Presenter extends com.sharezzorama.example.shop.mvp.Presenter<View> {

        void loadCategories();

    }
}
