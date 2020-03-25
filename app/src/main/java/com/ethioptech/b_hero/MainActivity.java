package com.ethioptech.b_hero;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ethioptech.b_hero.helper.SliderAdapter;

public class MainActivity extends AppCompatActivity {
    private ViewPager mViewPager;
    private SliderAdapter mSliderAdapter;
    private LinearLayout mLinearLayout;
    private TextView[] mDots;
    private Button btnNext;
    private Button btnBack;
    private int mCurrentPage=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewPager = findViewById(R.id.slider_view);
        mLinearLayout = findViewById(R.id.dots);
        btnBack=findViewById(R.id.btn_previous);
        btnNext=findViewById(R.id.btn_next);
        mSliderAdapter = new SliderAdapter(this);
        mViewPager.setAdapter(mSliderAdapter);
        addDots(0);
        mViewPager.setOnPageChangeListener(changeListener);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(btnNext.getText().toString().toUpperCase().equals("NEXT")){
                    mViewPager.setCurrentItem(mCurrentPage+1);
                }
                else if(btnNext.getText().toString().toUpperCase().equals("GET STARTED")) {
                    Intent intent=new Intent(MainActivity.this,LoginActivity.class);
                    startActivity(intent);
                }
            }
        });
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mViewPager.setCurrentItem(mCurrentPage-1);
            }
        });
    }
    public void addDots(int position){
        mDots=new TextView[2];
        mLinearLayout.removeAllViews();
        for (int i=0;i<mDots.length;i++){
            mDots[i]=new TextView(this);
            mDots[i].setText(Html.fromHtml("&#8226;"));
            mDots[i].setTextSize(35);
            mDots[i].setTextColor(getResources().getColor(R.color.transparentWhite));
            mLinearLayout.addView(mDots[i]);
        }
        if(mDots.length>0){
            mDots[position].setTextColor(getResources().getColor(R.color.selected));
        }
    }
    ViewPager.OnPageChangeListener changeListener=new ViewPager.OnPageChangeListener() {
        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

        }

        @Override
        public void onPageSelected(int position) {

            addDots(position);
            mCurrentPage=position;
            if(position==0){
                btnNext.setEnabled(true);
                btnBack.setEnabled(false);
                btnBack.setVisibility(View.INVISIBLE);
                btnNext.setText("Next");
                btnBack.setText("");
            }
            else if (position==mDots.length-1){
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);
                btnNext.setText("Get Started");
                btnBack.setText("Back");
            }
            else{
                btnNext.setEnabled(true);
                btnBack.setEnabled(true);
                btnBack.setVisibility(View.VISIBLE);
                btnNext.setText("Next");
                btnBack.setText("Back");
            }
        }

        @Override
        public void onPageScrollStateChanged(int state) {

        }
    };
}