package com.example.dishdash.uiLayer.mealsByCategory.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsAdapter;
import com.example.dishdash.uiLayer.helper.ImageLoader;

import java.util.List;

public class CategoryMealsAdapter extends RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealsViewHolder> {
    private List<PopularItem> meals;
    private final Context context;
    private final ICategoryMealsAdapter iCategoryMealsAdapter;
    private ImageLoader imageLoader;

    public CategoryMealsAdapter(ICategoryMealsAdapter iCategoryMealsAdapter ,Context context, List<PopularItem> meals, ImageLoader imageLoader){
        this.iCategoryMealsAdapter = iCategoryMealsAdapter;
        this.context = context;
        this.meals = meals;
        this.imageLoader = imageLoader;
        notifyDataSetChanged();
    }

    public void setMeals(List<PopularItem> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CategoryMealsAdapter.CategoryMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CategoryMealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_meal_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMealsAdapter.CategoryMealsViewHolder holder, int position) {

        imageLoader.loadImage(meals.get(position).getStrMealThumb(), holder.iv_meal_by_category_item);

        holder.tv_meal_by_category_item.setText(meals.get(position).getStrMeal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCategoryMealsAdapter.onCategoryMealClicked(meals.get(position).getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return meals == null? 0: meals.size();
    }

    public class CategoryMealsViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_meal_by_category_item;
        TextView tv_meal_by_category_item;
        public CategoryMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_meal_by_category_item = (ImageView) itemView.findViewById(R.id.iv_meal_by_category_item);
            tv_meal_by_category_item = (TextView) itemView.findViewById(R.id.tv_meal_by_category_item);
        }
    }
}
