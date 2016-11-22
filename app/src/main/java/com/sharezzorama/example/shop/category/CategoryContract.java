package com.sharezzorama.example.shop.category;

import com.sharezzorama.example.shop.BasePresenter;
import com.sharezzorama.example.shop.BaseView;
import com.sharezzorama.example.shop.data.catalog.category.Category;

import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public interface CategoryContract {

    interface View extends BaseView<Presenter> {

        void showCategories(List<Category> categories);

        void showNoCategories();
    }

    interface Presenter extends BasePresenter {

        void loadCategories();

    }
}
