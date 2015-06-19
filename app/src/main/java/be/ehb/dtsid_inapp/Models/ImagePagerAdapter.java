package be.ehb.dtsid_inapp.Models;

import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

/**
 * @author Kristof
 * @version 1.0
 */

public class ImagePagerAdapter extends PagerAdapter 
{

    List<Bitmap> imageArray;
    Context c;

    /**
     * Constructor
     * @param imageArray
     * @param c
     */
    public ImagePagerAdapter( List<Bitmap> imageArray, Context c) 
    {
        super();
        this.imageArray = imageArray;
        this.c = c;
    }

    @Override
    public Object instantiateItem(View container, int position) {
        ImageView iv = new ImageView(c);

        iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        iv.setScaleType(ImageView.ScaleType.FIT_XY);
        iv.setImageBitmap(imageArray.get(position));

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
    public int getCount() 
    {
        return imageArray.size();
    }
}
