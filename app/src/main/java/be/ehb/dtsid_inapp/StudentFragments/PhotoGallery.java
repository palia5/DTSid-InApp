package be.ehb.dtsid_inapp.StudentFragments;

import android.app.Fragment;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.util.Log;
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

    ViewPager myPager;
    ImagePagerAdapter myImagePagerAdapter;

    public PhotoGallery() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, null);

        int images[] = {R.drawable.button_cancel, R.drawable.button_confirm, R.drawable.button_next};

        myPager = (ViewPager) v.findViewById(R.id.viewpager);
        myImagePagerAdapter = new ImagePagerAdapter(images, getActivity());
        myPager.setAdapter(myImagePagerAdapter);
        myPager.setPageTransformer(true, new ZoomOutPageTransformer());

        ImageTimer timer = new ImageTimer(myPager, images);
        //timer.restartTimer();
        timer.execute();
        //myPager.setCurrentItem(0);

        //myPager.onTouchEvent(((StudentActivity) getActivity()).rightTouched())

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

    private void nextImage(){
        myPager.setCurrentItem(myPager.getCurrentItem() + 1, true);
    }

    private class ImageTimer extends AsyncTask{
        boolean timerStatus;
        ViewPager mPager;
        int images[];
        int lengt;

        public ImageTimer(ViewPager myPager, int images[]){
            this.mPager = myPager;
            this.images = images;
            lengt = images.length;
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            int i = mPager.getCurrentItem();

            mPager.setCurrentItem(++i, true);
            Log.d("TEST_", "na setitem " + i);
            if (i> lengt) {
                i = 0;
                Log.d("TEST_", "in if "+ i);
            }
            else {
                i++;
                Log.d("TEST_", "in else " + i);
            }
        }

        @Override
        protected Object doInBackground(Object[] params) {
            timerStatus = true;
            lengt = images.length;
           // int i = 0;
            //loop
            //express infinite loop gemaakt
            do {

                try {
                    Thread.sleep(5000);
                    //Log.d("TEST_", "voor setitem " + i);

                    publishProgress();


                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }while (timerStatus = true);
            //end loop

            return null;
        }

        public void stopTimer()
        {
            timerStatus =false;
        }

        public void restartTimer()
        {
            timerStatus =true;
        }
    }

}














