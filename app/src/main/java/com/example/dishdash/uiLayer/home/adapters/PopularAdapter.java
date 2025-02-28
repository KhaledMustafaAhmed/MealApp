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
import com.example.dishdash.dataLayer.model.pojo.mealsList.MeaList;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularItem;
import com.example.dishdash.dataLayer.model.pojo.popularCustomPojo.PopularList;
import com.example.dishdash.uiLayer.home.interfaces.IPopular;

import java.util.List;

public class PopularAdapter extends RecyclerView.Adapter<PopularAdapter.PopularViewHolder> {
    private static final String TAG = "PopularAdapter";
    private Context context;
    private List<PopularItem> popularList;

    private IPopular iPopular;
    public PopularAdapter(Context context, IPopular iPopular, List<PopularItem> popularList){
        this.context = context;
        this.popularList = popularList;
        this.iPopular = iPopular;
        notifyDataSetChanged();
    }

    public void setPopularList( List<PopularItem> popularList) {
        Log.e(TAG, "setPopularList: ");
        this.popularList = popularList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PopularAdapter.PopularViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.e(TAG, "onCreateViewHolder: " );
        return new PopularViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.popular_item,parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PopularAdapter.PopularViewHolder holder, int position) {
        if(popularList.get(position).getStrMealThumb() == null){
            Log.i(TAG, "onBindViewHolder: ");
            holder.iv_popular_meal_item.setImageResource(R.drawable.placeholderpic);
        }else{
            Glide.with(context)
                    .load(popularList.get(position).getStrMealThumb())
                    .into(holder.iv_popular_meal_item);
        }
        holder.tv_popular_meal_item.setText(popularList.get(position).getStrMeal());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPopular.onPopularMealClicked(popularList.get(position).getIdMeal());
            }
        });
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "getItemCount: ");
        return popularList == null?0:popularList.size();
    }

    public class PopularViewHolder extends RecyclerView.ViewHolder{
        ImageView iv_popular_meal_item;
        TextView tv_popular_meal_item;
        public PopularViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i(TAG, "PopularViewHolder: ");
            iv_popular_meal_item = itemView.findViewById(R.id.iv_popular_meal_item);
            tv_popular_meal_item = itemView.findViewById(R.id.tv_popular_meal_item);
        }
    }
}
