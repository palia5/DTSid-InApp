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
import android.widget.ImageView;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.Models.ImagePagerAdapter;
import be.ehb.dtsid_inapp.Models.ZoomOutPageTransformer;
import be.ehb.dtsid_inapp.R;

public class PhotoGallery extends Fragment
{
    StudentActivity activity;

    ViewPager myPager;
    ImagePagerAdapter myImagePagerAdapter;
    DatabaseContract dbc;

    public PhotoGallery() {
        super();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, null);

        activity = (StudentActivity) this.getActivity();

/*
        photoIV.setOnTouchListener(new View.OnTouchListener()
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                activity.rightTouched();
                return true;
            }
        });
*/
        dbc = new DatabaseContract(getActivity().getApplicationContext());
        List<Image> imageList = dbc.getAllImages();
        dbc.close();

        List<Bitmap> bitmaps = new ArrayList<>();

        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 4;
        options.inJustDecodeBounds = false;

        for(int i = 0 ; i < imageList.size() ; i++)
        {
            try
            {
                FileInputStream inputStream = getActivity().openFileInput(imageList.get(i).getImage());
                byte[] input = new byte[inputStream.available()];
                while(inputStream.read(input) != -1){}

                bitmaps.add(BitmapFactory.decodeByteArray(input,0,input.length, options));
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }

        myPager = (ViewPager) v.findViewById(R.id.viewpager);
        myImagePagerAdapter = new ImagePagerAdapter(bitmaps, activity.getApplicationContext());

        myPager.setAdapter(myImagePagerAdapter);
        myPager.setPageTransformer(true, new ZoomOutPageTransformer());

        ImageTimer timer = new ImageTimer(myPager, bitmaps);
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

    private class ImageTimer extends AsyncTask
    {
        boolean timerStatus;
        ViewPager mPager;
        List<Bitmap> images;
        int length;

        public ImageTimer(ViewPager myPager, List<Bitmap> images){
            this.mPager = myPager;
            this.images = images;
            length = images.size();
        }

        @Override
        protected void onProgressUpdate(Object[] values) {
            super.onProgressUpdate(values);
            int i = mPager.getCurrentItem();

            mPager.setCurrentItem(++i, true);
            Log.d("TEST_", "na setitem " + i);
            if (i>= length) {
                i = 0;
                mPager.setCurrentItem(i, true);
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
            length = images.size();
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














