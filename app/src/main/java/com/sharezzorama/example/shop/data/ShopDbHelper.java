package com.sharezzorama.example.shop.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.sharezzorama.example.shop.data.catalog.category.local.CategoryPersistenceContract;
import com.sharezzorama.example.shop.data.catalog.item.local.ItemPersistenceContract;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ShopDbHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;

    public static final String DATABASE_NAME = "shop.db";

    private static final String TEXT_TYPE = " TEXT";

    private static final String INTEGER_TYPE = " INTEGER";

    private static final String DOUBLE_TYPE = " DOUBLE";

    private static final String COMMA_SEP = ",";

    private static final String SQL_CREATE_TABLE_CATEGORY =
            "CREATE TABLE " + CategoryPersistenceContract.CategoryEntry.TABLE_NAME + " (" +
                    CategoryPersistenceContract.CategoryEntry._ID + INTEGER_TYPE + " PRIMARY KEY," +
                    CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID + INTEGER_TYPE + COMMA_SEP +
                    CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_NAME + TEXT_TYPE +
                    " )";

    private static final String SQL_CREATE_TABLE_ITEMS =
            "CREATE TABLE " + ItemPersistenceContract.ItemEntry.TABLE_NAME + " (" +
                    ItemPersistenceContract.ItemEntry._ID + INTEGER_TYPE + " PRIMARY KEY," +
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_CATEGORY_ID + INTEGER_TYPE + COMMA_SEP +
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_NAME + TEXT_TYPE + COMMA_SEP +
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_DESCRIPTION + TEXT_TYPE + COMMA_SEP +
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_PRICE + DOUBLE_TYPE +
                    " )";

    public ShopDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_CATEGORY);
        sqLiteDatabase.execSQL(SQL_CREATE_TABLE_ITEMS);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
