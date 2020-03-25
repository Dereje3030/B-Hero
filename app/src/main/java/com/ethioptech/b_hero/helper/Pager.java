package com.ethioptech.b_hero.helper;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.ethioptech.b_hero.fragments.HomeFragment;
import com.ethioptech.b_hero.fragments.RequestFragement;

public class Pager extends FragmentStatePagerAdapter {
    int tabCount;
    public Pager(@NonNull FragmentManager fm, int behavior) {
        super(fm, behavior);
        tabCount=behavior;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                HomeFragment tab1 = new HomeFragment();
                return tab1;
            case 1:
                RequestFragement tab2 = new RequestFragement();
                return tab2;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return tabCount;
    }
}
