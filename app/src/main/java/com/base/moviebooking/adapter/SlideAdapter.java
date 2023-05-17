package com.base.moviebooking.adapter;

 import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.base.moviebooking.R;
 import com.base.moviebooking.entity.Slide;
 import com.bumptech.glide.Glide;

 import java.util.List;

public class SlideAdapter extends PagerAdapter {
    private Context mContext;
    private List<Slide> mListSlide;

    public SlideAdapter(Context mContext, List<Slide> mListSlide) {
        this.mContext = mContext;
        this.mListSlide = mListSlide;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
       View view = LayoutInflater.from(container.getContext()).inflate(R.layout.item_slide,container,false);
        ImageView imgSlide = view.findViewById(R.id.img_slide);
        Slide slide = mListSlide.get(position);
        if(slide !=null){
            Glide.with(mContext).load(slide.getResourceId()).into(imgSlide);
        }
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View)object);
    }

    @Override
    public int getCount() {
        if(mListSlide!=null){
            return mListSlide.size();
        }
        return 0;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==object;
    }
}
