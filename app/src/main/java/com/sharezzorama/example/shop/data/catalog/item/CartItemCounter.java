package com.sharezzorama.example.shop.data.catalog.item;


import android.support.annotation.NonNull;

import com.sharezzorama.example.shop.data.BaseEntity;

public class CartItemCounter extends BaseEntity {

    private Item mItem;
    private int mCount;

    public CartItemCounter(@NonNull Item item) {
        mItem = item;
    }

    public void addOne() {
        mCount++;
    }

    public void removeOne() {
        if (mCount > 0) {
            mCount--;
        }
    }

    public Item getItem() {
        return mItem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        CartItemCounter that = (CartItemCounter) o;

        return mItem.equals(that.mItem);

    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + mItem.hashCode();
        return result;
    }
}
