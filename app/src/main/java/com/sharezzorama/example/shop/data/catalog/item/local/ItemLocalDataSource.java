package com.sharezzorama.example.shop.data.catalog.item.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.sharezzorama.example.shop.data.ShopDbHelper;
import com.sharezzorama.example.shop.data.catalog.item.Item;
import com.sharezzorama.example.shop.data.catalog.item.ItemDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class ItemLocalDataSource implements ItemDataSource {

    private static ItemLocalDataSource INSTANCE;
    private ShopDbHelper mDbHelper;

    private ItemLocalDataSource(Context context) {
        mDbHelper = new ShopDbHelper(context);
    }

    public static ItemLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new ItemLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getItems(final Long categoryId, final LoadItemsCallback callback) {
        Log.d(this.getClass().getSimpleName(), "getItems()");
        new ItemsAsyncLoader(callback).execute(categoryId);
    }

    @Override
    public void saveItems(final List<Item> items, final SaveItemsCallback callback) {
        new ItemsAsyncSaver(callback).execute(items);
    }

    private class ItemsAsyncLoader extends AsyncTask<Long, Void, List<Item>> {

        private LoadItemsCallback mCallback;

        ItemsAsyncLoader(LoadItemsCallback callback) {
            mCallback = callback;
        }

        @Override
        protected List<Item> doInBackground(Long... longs) {
            final Long categoryIdParam = longs[0];
            List<Item> items = new ArrayList<>();
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
            String[] projection = {
                    ItemPersistenceContract.ItemEntry._ID,
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_CATEGORY_ID,
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_NAME,
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_DESCRIPTION,
                    ItemPersistenceContract.ItemEntry.COLUMN_NAME_PRICE
            };

            String selection = ItemPersistenceContract.ItemEntry.COLUMN_NAME_CATEGORY_ID + " = ?";
            String[] selectionArgs = {String.valueOf(categoryIdParam)};

            Cursor cursor = db.query(ItemPersistenceContract.ItemEntry.TABLE_NAME, projection, selection, selectionArgs, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Long id = cursor.getLong(cursor.getColumnIndexOrThrow(ItemPersistenceContract.ItemEntry._ID));
                    Long categoryId = cursor.getLong(cursor.getColumnIndexOrThrow(ItemPersistenceContract.ItemEntry.COLUMN_NAME_CATEGORY_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(ItemPersistenceContract.ItemEntry.COLUMN_NAME_NAME));
                    String description = cursor.getString(cursor.getColumnIndexOrThrow(ItemPersistenceContract.ItemEntry.COLUMN_NAME_DESCRIPTION));
                    Double price = cursor.getDouble(cursor.getColumnIndexOrThrow(ItemPersistenceContract.ItemEntry.COLUMN_NAME_PRICE));
                    Item item = new Item(id, categoryId, name, description, price);
                    items.add(item);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return items;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            if (items.isEmpty()) {
                mCallback.onDataNotAvailable();
            } else {
                mCallback.onItemsLoaded(items);
            }
        }
    }

    private class ItemsAsyncSaver extends AsyncTask<List<Item>, Void, List<Item>> {
        private SaveItemsCallback mCallback;

        private ItemsAsyncSaver(SaveItemsCallback callback) {
            mCallback = callback;
        }

        @Override
        protected List<Item> doInBackground(List<Item>... lists) {
            List<Item> items = lists[0];
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            for (Item item : items) {
                ContentValues values = new ContentValues();
                values.put(ItemPersistenceContract.ItemEntry._ID, item.getId());
                values.put(ItemPersistenceContract.ItemEntry.COLUMN_NAME_CATEGORY_ID, item.getCategoryId());
                values.put(ItemPersistenceContract.ItemEntry.COLUMN_NAME_NAME, item.getName());
                values.put(ItemPersistenceContract.ItemEntry.COLUMN_NAME_DESCRIPTION, item.getDescription());
                values.put(ItemPersistenceContract.ItemEntry.COLUMN_NAME_PRICE, item.getPrice());
                boolean isNew = item.getId() == null;
                if (isNew) {
                    long id = db.insert(ItemPersistenceContract.ItemEntry.TABLE_NAME, null, values);
                    item.setId(id);
                } else {
                    String selection = ItemPersistenceContract.ItemEntry._ID + " = ?";
                    String[] selectionArgs = {String.valueOf(item.getId())};
                    db.update(ItemPersistenceContract.ItemEntry.TABLE_NAME, values, selection, selectionArgs);
                }
            }
            db.close();
            return items;
        }

        @Override
        protected void onPostExecute(List<Item> items) {
            mCallback.onItemsSaved(items);
        }
    }

}
