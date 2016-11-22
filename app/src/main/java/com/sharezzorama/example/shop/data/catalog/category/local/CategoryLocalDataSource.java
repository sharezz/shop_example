package com.sharezzorama.example.shop.data.catalog.category.local;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;

import com.sharezzorama.example.shop.data.ShopDbHelper;
import com.sharezzorama.example.shop.data.catalog.category.Category;
import com.sharezzorama.example.shop.data.catalog.category.CategoryDataSource;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoryLocalDataSource implements CategoryDataSource {
    private static CategoryLocalDataSource INSTANCE;
    private ShopDbHelper mDbHelper;

    private CategoryLocalDataSource(Context context) {
        mDbHelper = new ShopDbHelper(context);
    }

    public static CategoryLocalDataSource getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = new CategoryLocalDataSource(context);
        }
        return INSTANCE;
    }

    @Override
    public void getCategories(final LoadCategoriesCallback callback) {
        Log.d(this.getClass().getSimpleName(), "getCategories()");
        new CategoryAsyncLoader(callback).execute();
    }

    @Override
    public void saveCategories(final List<Category> categories, final SaveCategoriesCallback callback) {
        new CategoryAsyncSaver(callback).execute(categories);
    }

    private class CategoryAsyncLoader extends AsyncTask<Void, Void, List<Category>> {
        private LoadCategoriesCallback mCallback;

        private CategoryAsyncLoader(LoadCategoriesCallback mCallback) {
            this.mCallback = mCallback;
        }

        @Override
        protected List<Category> doInBackground(Void... voids) {
            List<Category> categories = new ArrayList<>();
            SQLiteDatabase db = mDbHelper.getReadableDatabase();
            String[] projection = {
                    CategoryPersistenceContract.CategoryEntry._ID,
                    CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID,
                    CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_NAME
            };

            Cursor cursor = db.query(CategoryPersistenceContract.CategoryEntry.TABLE_NAME, projection, null, null, null, null, null);
            if (cursor != null && cursor.getCount() > 0) {
                while (cursor.moveToNext()) {
                    Long id = cursor.getLong(cursor.getColumnIndexOrThrow(CategoryPersistenceContract.CategoryEntry._ID));
                    Long categoryId = cursor.getLong(cursor.getColumnIndexOrThrow(CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID));
                    String name = cursor.getString(cursor.getColumnIndexOrThrow(CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_NAME));
                    Category category = new Category(id, categoryId, name);
                    categories.add(category);
                }
            }
            if (cursor != null) {
                cursor.close();
            }
            db.close();
            return categories;
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            if (categories.isEmpty()) {
                mCallback.onDataNotAvailable();
            } else {
                mCallback.onItemsLoaded(categories);
            }
        }
    }

    private class CategoryAsyncSaver extends AsyncTask<List<Category>, Void, List<Category>> {
        private SaveCategoriesCallback mCallback;

        private CategoryAsyncSaver(SaveCategoriesCallback mCallback) {
            this.mCallback = mCallback;
        }

        @Override
        protected List<Category> doInBackground(List<Category>... lists) {
            List<Category> categories = lists[0];
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
            for (Category category : categories) {
                ContentValues values = new ContentValues();
                values.put(CategoryPersistenceContract.CategoryEntry._ID, category.getId());
                values.put(CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_CATEGORY_ID, category.getCategoryId());
                values.put(CategoryPersistenceContract.CategoryEntry.COLUMN_NAME_NAME, category.getName());
                boolean isNew = category.getId() == null;
                if (isNew) {
                    long id = db.insert(CategoryPersistenceContract.CategoryEntry.TABLE_NAME, null, values);
                    category.setId(id);
                } else {
                    String selection = CategoryPersistenceContract.CategoryEntry._ID + " = ?";
                    String[] selectionArgs = {String.valueOf(category.getId())};
                    db.update(CategoryPersistenceContract.CategoryEntry.TABLE_NAME, values, selection, selectionArgs);
                }
            }
            db.close();
            return categories;
        }

        @Override
        protected void onPostExecute(List<Category> categories) {
            mCallback.onCategoriesSaved(categories);
        }
    }
}
