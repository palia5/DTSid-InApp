package be.ehb.dtsid_inapp.Models;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import be.ehb.dtsid_inapp.StudentFragments.PhotoGallery;

/**
 * Created by doortje on 8/06/15.
 */
public class ImagePageAdapter extends FragmentStatePagerAdapter {

    private Image images [] = {new Image (), new Image (),new Image()};


    public ImagePageAdapter (FragmentManager fragmentManager)
    {super (fragmentManager);}


    @Override
    public Fragment getItem(int position) {
        PhotoGallery photoGallery = new PhotoGallery(images[position]);
        return photoGallery;
    }

    @Override
    public int getCount() {
        return images.lenght;
    }
}
