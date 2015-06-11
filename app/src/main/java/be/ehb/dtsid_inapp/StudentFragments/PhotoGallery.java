package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Models.ImagePagerAdapter;
import be.ehb.dtsid_inapp.Models.ZoomOutPageTransformer;
import be.ehb.dtsid_inapp.R;

public class PhotoGallery extends Fragment {
    public PhotoGallery() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, null);

        int images[] = {R.drawable.the_queen, R.drawable.the_queen_amused, R.drawable.the_queen_owyeah};

        ViewPager myPager = (ViewPager) v.findViewById(R.id.viewpager);
        myPager.setAdapter(new ImagePagerAdapter(images, getActivity()));
        myPager.setPageTransformer(true, new ZoomOutPageTransformer());
        myPager.setCurrentItem(0);

        //myPager.onTouchEvent()

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                ((StudentActivity) getActivity()).rightTouched();
                return true;
            }
        });

        return v;
    }

    private Bitmap loadImageFromStorage(String path) {

        try {
            File f = new File(path, "profile.jpg");
            Bitmap b = BitmapFactory.decodeStream(new FileInputStream(f));
            return b;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private class ImageTimer extends AsyncTask{

        public ImageTimer(){

        }

        @Override
        protected Object doInBackground(Object[] params) {

            //loop
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //end loop

            return null;
        }
    }

}














