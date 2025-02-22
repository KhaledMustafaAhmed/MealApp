package com.example.dishdash.uiLayer.mealDetails;

import android.content.Context;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;

import java.util.List;

public class DetailedMealAdapter extends RecyclerView.Adapter<DetailedMealAdapter.DetailedMealAdapterViewHolder> {

    private List<Pair<String,String>> IngredientsAndMeasures;
    Context context;
    private static final String INGREDIENTS_BASE_URL = "https://www.themealdb.com/images/ingredients/";

    public DetailedMealAdapter(Context context,List<Pair<String,String>> ingredientsAndMeasures){
        this.IngredientsAndMeasures = ingredientsAndMeasures;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setIngredientsAndMeasures(List<Pair<String,String>> ingredientsAndMeasures) {
        this.IngredientsAndMeasures = ingredientsAndMeasures;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public DetailedMealAdapterViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new DetailedMealAdapterViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.ingrediants_measure_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull DetailedMealAdapterViewHolder holder, int position) {
        Glide.with(context)
                .load(INGREDIENTS_BASE_URL+IngredientsAndMeasures.get(position).first+"-Small.png")
                .into(holder.iv_popular_meal_item);
        holder.tv_popular_meal_item_ingrediant.setText(IngredientsAndMeasures.get(position).first);
        holder.tv_popular_meal_item_measure.setText(IngredientsAndMeasures.get(position).second);
    }

    @Override
    public int getItemCount() {
        return IngredientsAndMeasures == null? 0: IngredientsAndMeasures.size();
    }

    class DetailedMealAdapterViewHolder extends RecyclerView.ViewHolder {

        ImageView iv_popular_meal_item;
        TextView tv_popular_meal_item_ingrediant, tv_popular_meal_item_measure;

        public DetailedMealAdapterViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_popular_meal_item = itemView.findViewById(R.id.iv_popular_meal_item);
            tv_popular_meal_item_ingrediant = itemView.findViewById(R.id.tv_popular_meal_item_ingrediant);
            tv_popular_meal_item_measure = itemView.findViewById(R.id.tv_popular_meal_item_measure);
        }
    }
}
