package be.ehb.dtsid_inapp.StudentFragments;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.net.URI;
import java.util.List;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.Models.ImagePagerAdapter;
import be.ehb.dtsid_inapp.Models.ZoomOutPageTransformer;
import be.ehb.dtsid_inapp.R;

public class PhotoGallery extends Fragment
{
    public PhotoGallery()
    {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, null);

        int images[] = {R.mipmap.the_queen, R.mipmap.the_queen_amused, R.mipmap.the_queen_owyeah };

        ViewPager myPager = (ViewPager) v.findViewById(R.id.viewpager);
        myPager.setAdapter(new ImagePagerAdapter( images, getActivity()));
        myPager.setPageTransformer(true, new ZoomOutPageTransformer());
        myPager.setCurrentItem(0);


//        Log.d("IMAGE PHOTO", "" + dbc.getAllBitmaps().get(1));

//        Log.d("IMAGES SIZE", "" + dbc.getAllImages().size());
//        Log.d("BITMAPS SIZE", "" + dbc.getAllBitmaps().size());

        //testPhoto.setImageBitmap(dbc.getAllBitmaps().get(1));


        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((StudentActivity)getActivity()).rightTouched();
                return true;
            }
        });

        return v;
    }
}
