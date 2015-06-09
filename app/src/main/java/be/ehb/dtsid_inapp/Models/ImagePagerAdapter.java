package be.ehb.dtsid_inapp.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.StudentFragments.PhotoGallery;

/**
 * Created by Kristof on 8/06/2015.
 */

public class ImagePagerAdapter extends PagerAdapter{

    int imageArray[];
    Context c;


    public ImagePagerAdapter( int[] imageArray, Context c) {
        super();
        this.imageArray = imageArray;
        this.c = c;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ImageView iv = new ImageView(c);

        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setBackgroundResource(imageArray[position]);

        ((ViewPager)container).addView(iv);

        return iv;
    }

    @Override
    public void destroyItem(View container, int position, Object object) {
        ((ViewPager)container).removeView((View)object);
    }

    @Override
    public boolean isViewFromObject(View view, Object o) {
        return view == (View)o;
    }

    @Override
    public int getCount() {
        //return images.length;
        return imageArray.length;
    }
}
