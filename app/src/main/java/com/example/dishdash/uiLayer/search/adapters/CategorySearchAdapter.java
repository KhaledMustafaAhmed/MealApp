package com.example.dishdash.uiLayer.search.adapters;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class CategorySearchAdapter extends RecyclerView.Adapter<CategorySearchAdapter.CategorySearchViewHolder> {
    @NonNull
    @Override
    public CategorySearchAdapter.CategorySearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull CategorySearchAdapter.CategorySearchViewHolder holder, int position) {

    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class CategorySearchViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_category_image_item;
        TextView tv_category_name_item;
        public CategorySearchViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }
}
