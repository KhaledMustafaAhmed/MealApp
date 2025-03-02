package com.example.dishdash.uiLayer.favourites;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.entities.FavouriteMeal;
import com.example.dishdash.uiLayer.helper.ImageLoader;

import java.util.List;

public class FavouriteMealsAdapter extends RecyclerView.Adapter<FavouriteMealsAdapter.FavouriteViewHolder> {

    private List<FavouriteMeal> favouriteMeals;
    private final Context context;
    private final IFavouriteAdapter iFavouriteAdapter;
    private ImageLoader imageLoader;

    public FavouriteMealsAdapter(List<FavouriteMeal> favouriteMeals, Context context, IFavouriteAdapter iFavouriteAdapter, ImageLoader imageLoader) {
        this.favouriteMeals = favouriteMeals;
        this.context = context;
        this.iFavouriteAdapter = iFavouriteAdapter;
        this.imageLoader = imageLoader;
        notifyDataSetChanged();
    }

    public void setMealsList(List<FavouriteMeal> favouriteMeals) {
        this.favouriteMeals = favouriteMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public FavouriteMealsAdapter.FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new FavouriteViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteMealsAdapter.FavouriteViewHolder holder, int position) {
        holder.tv_fav_meal_item.setText(favouriteMeals.get(position).getMealsItem().getStrMeal());

        imageLoader.loadImage(favouriteMeals.get(position).getMealsItem().getStrMealThumb(), holder.iv_fav_meal_item);

        holder.iv_delete_fav_item.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFavouriteAdapter.onDeleteFavItemClicked(favouriteMeals.get(position).getMealsItem().getIdMeal());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iFavouriteAdapter.onFavItemClicked(favouriteMeals.get(position).getMeal_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return favouriteMeals == null? 0: favouriteMeals.size();
    }

    public static class FavouriteViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_fav_meal_item, iv_delete_fav_item;
        TextView tv_fav_meal_item;
        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_fav_meal_item = (ImageView) itemView.findViewById(R.id.iv_fav_meal_item);
            tv_fav_meal_item = (TextView) itemView.findViewById(R.id.tv_fav_meal_item);
            iv_delete_fav_item = (ImageView) itemView.findViewById(R.id.iv_delete_fav_item);
        }
    }
}
