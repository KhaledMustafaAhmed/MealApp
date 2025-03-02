package com.example.dishdash.uiLayer.helper;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

public class GlideImageLoader implements ImageLoader{
    private Context context;

    public GlideImageLoader(Context context) {
        this.context = context;
    }

    @Override
    public void loadImage(String url, ImageView imageView) {
        Glide.with(context)
                .load(url)
                .into(imageView);
    }
}
