package com.example.dishdash.uiLayer.mealsByCountry.classes;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.uiLayer.mealsByCategory.interfaces.ICategoryMealsView;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsAdapter;
import com.example.dishdash.uiLayer.mealsByCountry.interfaces.ICountryMealsViews;

import java.util.List;

public class CountryMealsAdapter extends RecyclerView.Adapter<CountryMealsAdapter.CountryMealsViewHolder> {
    private ICountryMealsAdapter iCountryMealsAdapter;

    private List<PopularItem> popularList;

    private Context context;

    public CountryMealsAdapter(ICountryMealsAdapter iCountryMealsAdapter,Context context, List<PopularItem> popularList){
        this.iCountryMealsAdapter = iCountryMealsAdapter;
        this.popularList =popularList;
        this.context = context;
        notifyDataSetChanged();
    }

    public void setPopularList(List<PopularItem> popularList) {
        this.popularList = popularList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryMealsAdapter.CountryMealsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryMealsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_meals_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryMealsAdapter.CountryMealsViewHolder holder, int position) {
        holder.tv_meal_by_country_item.setText(popularList.get(position).getStrMeal());

        Glide.with(context)
                .load(popularList.get(position).getStrMealThumb())
                .into(holder.iv_meal_by_country_item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCountryMealsAdapter.onCountryMealClicked(popularList.get(position).getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        return popularList == null? 0: popularList.size();
    }

    public class CountryMealsViewHolder extends RecyclerView.ViewHolder {
        TextView tv_meal_by_country_item;
        ImageView iv_meal_by_country_item;
        public CountryMealsViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_meal_by_country_item = (ImageView) itemView.findViewById(R.id.iv_meal_by_country_item);
            tv_meal_by_country_item = (TextView) itemView.findViewById(R.id.tv_meal_by_country_item);
        }
    }
}
