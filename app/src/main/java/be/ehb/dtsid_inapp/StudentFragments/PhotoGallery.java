package be.ehb.dtsid_inapp.StudentFragments;

import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import be.ehb.dtsid_inapp.Activities.StudentActivity;
import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.R;

public class PhotoGallery extends Fragment
{
    StudentActivity activity;
    ImageView testPhoto;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View v = inflater.inflate(R.layout.fragment_photo_gallery, null);

        activity = (StudentActivity) this.getActivity();

        DatabaseContract dbc = new DatabaseContract(activity.getApplicationContext());

//        Log.d("IMAGE PHOTO", "" + dbc.getAllBitmaps().get(1));

//        Log.d("IMAGES SIZE", "" + dbc.getAllImages().size());
//        Log.d("BITMAPS SIZE", "" + dbc.getAllBitmaps().size());

        //testPhoto.setImageBitmap(dbc.getAllBitmaps().get(1));

        dbc.close();

        v.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                activity.rightTouched();
                return true;
            }
        });
        return v;
    }
}
