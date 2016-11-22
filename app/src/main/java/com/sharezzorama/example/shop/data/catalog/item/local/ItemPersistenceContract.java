package com.sharezzorama.example.shop.data.catalog.item.local;

import android.provider.BaseColumns;

/**
 * Created by sharezzorama on 11/22/16.
 */

public final class ItemPersistenceContract {

    private ItemPersistenceContract() {
    }

    public static abstract class ItemEntry implements BaseColumns {
        public static final String TABLE_NAME = "items";
        public static final String COLUMN_NAME_CATEGORY_ID = "category_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_DESCRIPTION = "description";
        public static final String COLUMN_NAME_PRICE = "price";
    }
}
