package com.ethioptech.b_hero.helper;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.PagerAdapter;

import com.ethioptech.b_hero.R;

public class SliderAdapter extends PagerAdapter {
    Context mContext;
    LayoutInflater mLayoutInflater;

    public SliderAdapter(Context mContext) {
        this.mContext = mContext;
    }
    public int[] slideImages={R.drawable.img1,R.drawable.bloodbag};
    public String[] slideTitles={"Donate Blood","Request Blood"};
    public String[] slideDescriptions={"Your blood donation can give a \n " +
            "precious smile to someone's face","Lorem Ipsum is simply dummy text \n" +
            "of the printing and typesetting"};
    @Override
    public int getCount() {
        return slideTitles.length;
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view ==(ConstraintLayout)object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        mLayoutInflater=(LayoutInflater)mContext.getSystemService(mContext.LAYOUT_INFLATER_SERVICE);
        View view=mLayoutInflater.inflate(R.layout.slide_layout,container,false);
        ImageView slideImageView=view.findViewById(R.id.slideImageView);
        TextView slideTitle=view.findViewById(R.id.slideTitle);
        TextView slideDescription=view.findViewById(R.id.slideDescription);
        slideImageView.setImageResource(slideImages[position]);
        slideTitle.setText(slideTitles[position]);
        slideDescription.setText(slideDescriptions[position]);
        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((ConstraintLayout)object);
    }
}
