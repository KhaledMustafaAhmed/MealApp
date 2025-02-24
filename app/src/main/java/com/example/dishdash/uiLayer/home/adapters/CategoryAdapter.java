package com.example.dishdash.uiLayer.home.adapters;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.categoryCustomPojo.CategoryItem;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;

import java.util.List;
import java.util.zip.Inflater;

public class CategoryAdapter extends RecyclerView.Adapter<CategoryAdapter.CategoryViewHolder> {
    private static final String TAG = "CategoryAdapter";
    private static final String CATEGORY_IMAGE_URL = "https://www.themealdb.com/images/category/";
    private Context context;
    private List<CategoryItem> categoryList;

    public CategoryAdapter(Context context, List<CategoryItem> categoryList){
        this.context =context;
        this.categoryList = categoryList;
    }

    public void setCategoryList(List<CategoryItem> categoryList) {
        this.categoryList = categoryList;
        notifyDataSetChanged();
        Log.d(TAG, "setCategoryList: in set adapter");
    }

    @NonNull
    @Override
    public CategoryAdapter.CategoryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapter.CategoryViewHolder holder, int position) {
        Glide.with(context)
                .load(CATEGORY_IMAGE_URL+categoryList.get(position).getStrCategory().toLowerCase()+".png")
                .into(holder.iv_category_image_item);
        Log.d(TAG, "onBindViewHolder: ");
        holder.tv_category_name_item.setText(categoryList.get(position).getStrCategory());
    }

    @Override
    public int getItemCount() {
        return categoryList == null? 0: categoryList.size();
    }

    public class CategoryViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_category_image_item;
        TextView tv_category_name_item;
        public CategoryViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_category_image_item =(ImageView) itemView.findViewById(R.id.iv_category_image_item);
            tv_category_name_item =(TextView) itemView.findViewById(R.id.tv_category_name_item);
        }
    }
}
