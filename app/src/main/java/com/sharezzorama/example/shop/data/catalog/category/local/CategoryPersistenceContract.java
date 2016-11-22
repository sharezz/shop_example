package com.sharezzorama.example.shop.data.catalog.category.local;

import android.provider.BaseColumns;

/**
 * Created by sharezzorama on 11/22/16.
 */

public final class CategoryPersistenceContract {

    private CategoryPersistenceContract() {
    }

    public static abstract class CategoryEntry implements BaseColumns {
        public static final String TABLE_NAME = "category";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_NAME = "name";
    }
}
