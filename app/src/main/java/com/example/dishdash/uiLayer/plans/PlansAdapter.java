package com.example.dishdash.uiLayer.plans;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.entities.PlannedMeal;
import com.example.dishdash.uiLayer.helper.ImageLoader;

import java.util.List;

public class PlansAdapter extends RecyclerView.Adapter<PlansAdapter.PlansViewHolder> {

    private final Context context;
    private List<PlannedMeal> plannedMeals;
    private final IPlansAdapter iPlansAdapter;
    private ImageLoader imageLoader;


    public PlansAdapter(Context context, IPlansAdapter iPlansAdapter, List<PlannedMeal> plannedMeals, ImageLoader imageLoader) {
        this.context = context;
        this.plannedMeals = plannedMeals;
        this.iPlansAdapter = iPlansAdapter;
        this.imageLoader = imageLoader;
        notifyDataSetChanged();
    }

    public void setPlannedMeals(List<PlannedMeal> plannedMeals) {
        this.plannedMeals = plannedMeals;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PlansAdapter.PlansViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new PlansViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.weekly_plan_meal_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull PlansAdapter.PlansViewHolder holder, int position) {

        imageLoader.loadImage(plannedMeals.get(position).getMealsItem().getStrMealThumb(), holder.iv_weekly_plan_meal_item);

        holder.tv_week_plan_date.setText(plannedMeals.get(position).getDate());

        holder.iv_del_week_plan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPlansAdapter.onDelPlanClicked(plannedMeals.get(position).getMeal_id(), plannedMeals.get(position).getDate());
            }
        });

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iPlansAdapter.onPlanItemClicked(plannedMeals.get(position).getMeal_id());
            }
        });
    }

    @Override
    public int getItemCount() {
        return plannedMeals == null? 0: plannedMeals.size();
    }

    public class PlansViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_weekly_plan_meal_item, iv_del_week_plan;
        TextView tv_week_plan_date;
        public PlansViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_weekly_plan_meal_item = (ImageView) itemView.findViewById(R.id.iv_weekly_plan_meal_item);
            iv_del_week_plan = (ImageView) itemView.findViewById(R.id.iv_del_week_plan);
            tv_week_plan_date = (TextView) itemView.findViewById(R.id.tv_week_plan_date);
        }
    }
}
