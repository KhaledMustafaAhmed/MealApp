package com.example.dishdash.uiLayer.home.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.dishdash.R;
import com.example.dishdash.dataLayer.model.pojo.areaCustomPojo.CountryItem;
import com.example.dishdash.uiLayer.home.interfaces.ICountry;

import java.util.List;

public class CountryAdapter extends RecyclerView.Adapter<CountryAdapter.CountryViewHolder> {

    private List<CountryItem> countryList;
   private  ICountry iCountry;

    public CountryAdapter(ICountry iCountry,List<CountryItem> countryList){
        this.countryList = countryList;
        this.iCountry = iCountry;
        notifyDataSetChanged();
    }

    public void setCountryList(List<CountryItem> countryList) {
        this.countryList = countryList;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public CountryAdapter.CountryViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new CountryViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.country_item, parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CountryAdapter.CountryViewHolder holder, int position) {
        showAreaFlag(holder,position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                iCountry.onCountryItemClick(countryList.get(position).getStrArea());
            }
        });
    }

    @Override
    public int getItemCount() {
        return countryList == null? 0: countryList.size();
    }

    public class CountryViewHolder extends RecyclerView.ViewHolder {
        ImageView iv_country_image_item;
        public CountryViewHolder(@NonNull View itemView) {
            super(itemView);
            iv_country_image_item =(ImageView) itemView.findViewById(R.id.iv_country_image_item);
        }
    }

    private void showAreaFlag(@NonNull CountryAdapter.CountryViewHolder holder, int position){
        switch (countryList.get(position).getStrArea().toLowerCase()){
            case "american": holder.iv_country_image_item.setImageResource(R.drawable.american); break;
            case "british": holder.iv_country_image_item.setImageResource(R.drawable.british); break;
            case "canadian": holder.iv_country_image_item.setImageResource(R.drawable.canadian); break;
            case "chinese": holder.iv_country_image_item.setImageResource(R.drawable.chinese); break;
            case "croatian": holder.iv_country_image_item.setImageResource(R.drawable.croatian); break;
            case "dutch": holder.iv_country_image_item.setImageResource(R.drawable.dutch); break;
            case "egyptian": holder.iv_country_image_item.setImageResource(R.drawable.egyptian); break;
            case "filipino": holder.iv_country_image_item.setImageResource(R.drawable.filipino); break;
            case "french": holder.iv_country_image_item.setImageResource(R.drawable.french); break;
            case "greek": holder.iv_country_image_item.setImageResource(R.drawable.greek); break;
            case "indian": holder.iv_country_image_item.setImageResource(R.drawable.indian); break;
            case "irish": holder.iv_country_image_item.setImageResource(R.drawable.irish); break;
            case "italian": holder.iv_country_image_item.setImageResource(R.drawable.italian); break;
            case "jamaican": holder.iv_country_image_item.setImageResource(R.drawable.jamaican); break;
            case "japanese": holder.iv_country_image_item.setImageResource(R.drawable.japanese); break;
            case "kenyan": holder.iv_country_image_item.setImageResource(R.drawable.kenyan); break;
            case "malaysian": holder.iv_country_image_item.setImageResource(R.drawable.malaysian); break;
            case "mexican": holder.iv_country_image_item.setImageResource(R.drawable.mexican); break;
            case "moroccan": holder.iv_country_image_item.setImageResource(R.drawable.moroccan); break;
            case "norwegian": holder.iv_country_image_item.setImageResource(R.drawable.norwegian); break;
            case "polish": holder.iv_country_image_item.setImageResource(R.drawable.polish); break;
            case "portuguese": holder.iv_country_image_item.setImageResource(R.drawable.portuguese); break;
            case "russian": holder.iv_country_image_item.setImageResource(R.drawable.russian); break;
            case "spanish": holder.iv_country_image_item.setImageResource(R.drawable.spanish); break;
            case "thai": holder.iv_country_image_item.setImageResource(R.drawable.thai); break;
            case "tunisian": holder.iv_country_image_item.setImageResource(R.drawable.tunisian); break;
            case "turkish": holder.iv_country_image_item.setImageResource(R.drawable.turkish); break;
            case "ukrainian": holder.iv_country_image_item.setImageResource(R.drawable.ukrainian); break;
            case "uruguayan": holder.iv_country_image_item.setImageResource(R.drawable.uruguayan); break;
            case "vietnamese": holder.iv_country_image_item.setImageResource(R.drawable.vietnamese); break;
        }
    }

}
