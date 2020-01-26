package com.application.obvious.detail;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.application.obvious.model.ImageList;

import java.util.ArrayList;

public class DetailAdapter extends FragmentStatePagerAdapter {
    private ArrayList<ImageList.Image> imageList;

    public DetailAdapter(FragmentManager fm, ArrayList<ImageList.Image> images) {
        super(fm);
        this.imageList = images;
    }

    @Override
    public Fragment getItem(int position) {
        ImageList.Image image = imageList.get(position);
        return ImageFragment.newInstance(image, image.getTitle());
    }

    @Override
    public int getCount() {
        return imageList.size();
    }
}