package com.sharezzorama.example.shop.category;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sharezzorama.example.shop.R;
import com.sharezzorama.example.shop.data.catalog.category.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sharezzorama on 11/22/16.
 */

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private List<Category> mCategories = new ArrayList<>();
    private CategoryClickListener mCategoryClickListener;

    public CategoryAdapter(CategoryClickListener categoryClickListener) {
        mCategoryClickListener = categoryClickListener;
    }

    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_category, parent, false);
        return new CategoryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(CategoryViewHolder holder, int position) {
        final Category category = mCategories.get(position);
        holder.mNameView.setText(category.getName());
        if (mCategoryClickListener != null) {
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mCategoryClickListener.onCategoryClick(category);
                }
            });
        }
    }

    public List<Category> getCategories() {
        return mCategories;
    }

    public void setCategories(List<Category> categories) {
        mCategories.clear();
        mCategories.addAll(categories);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return mCategories != null ? mCategories.size() : 0;
    }

    class CategoryViewHolder extends RecyclerView.ViewHolder {
        private TextView mNameView;

        public CategoryViewHolder(View itemView) {
            super(itemView);
            mNameView = (TextView) itemView.findViewById(R.id.categoryName);
        }
    }

    public interface CategoryClickListener {
        void onCategoryClick(Category category);
    }
}
