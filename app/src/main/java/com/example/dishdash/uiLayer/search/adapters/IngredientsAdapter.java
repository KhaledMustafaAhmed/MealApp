package com.example.dishdash.uiLayer.search.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.ingredientsCustomPojo.IngredientItem;
import com.example.dishdash.uiLayer.search.interfaces.ISearchAdapter;
import com.example.dishdash.uiLayer.helper.ImageLoader;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
    private final Context context;
    private List<IngredientItem> ingredientList;
    private final ISearchAdapter iSearchAdapter;
    private ImageLoader imageLoader;
    private static final String INGREDIENT_IMAGE_URL = "https://www.themealdb.com/images/ingredients/";

    public IngredientsAdapter(Context context, List<IngredientItem> ingredientList, ISearchAdapter iSearchAdapter, ImageLoader imageLoader) {
        this.context = context;
        this.ingredientList = ingredientList;
        this.iSearchAdapter = iSearchAdapter;
        this.imageLoader = imageLoader;
        notifyDataSetChanged();
    }

    public void setIngredientList(List<IngredientItem> ingredientList) {
        this.ingredientList = ingredientList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public IngredientsAdapter.IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new IngredientsViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsAdapter.IngredientsViewHolder holder, int position) {
        holder.tv_ingredients_name_item.setText(ingredientList.get(position).getStrIngredient());

        imageLoader.loadImage(INGREDIENT_IMAGE_URL+ingredientList.get(position).getStrIngredient()+"-Medium.png", holder.iv_ingredients_image_item);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iSearchAdapter.onIngredientItemClick(ingredientList.get(position).getStrIngredient());
            }
        });
    }

    @Override
    public int getItemCount() {
        return ingredientList == null? 0: ingredientList.size();
    }

    public class IngredientsViewHolder extends RecyclerView.ViewHolder {
         ImageView iv_ingredients_image_item;
         TextView tv_ingredients_name_item;
        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_ingredients_name_item =(TextView) itemView.findViewById(R.id.tv_ingredients_name_item);
            iv_ingredients_image_item = (ImageView) itemView.findViewById(R.id.iv_ingredients_image_item);
        }
    }
}
