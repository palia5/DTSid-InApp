package be.ehb.dtsid_inapp.Activities;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Spinner;

import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.StudentFragments.PhotoGallery;
import be.ehb.dtsid_inapp.StudentFragments.StudentRegistration;

public class StudentActivity extends AppCompatActivity
{
    private Boolean isInMainScreen = true;

    private StudentRegistration registrationFragment;
    private PhotoGallery photoGalleryFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);


        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        registrationFragment = new StudentRegistration();
        photoGalleryFragment =  new PhotoGallery();

        ft.add(R.id.fragm_left_registration, registrationFragment);
        ft.add(R.id.fragm_right_images,photoGalleryFragment);
        ft.commit();
    }

    @Override
    public void onBackPressed()
    {
        if(isInMainScreen)
            super.onBackPressed();
        else
        {
            changeWeightOfFragments(50, 50);
            registrationFragment.disableEditTeksts();
            isInMainScreen = true;
        }
    }

    public void leftTouched()
    {
        isInMainScreen = false;
        changeWeightOfFragments(100, 0);
    }

    public void rightTouched()
    {
        isInMainScreen = false;
        changeWeightOfFragments(0, 100);
    }

    private void changeWeightOfFragments(float weightLeftFragment, float weigthRightFragment)
    {
        //Set registration weight
        FrameLayout flRegistration = (FrameLayout) findViewById(R.id.fragm_left_registration);
        LinearLayout.LayoutParams lpRegistration = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                weightLeftFragment);
        flRegistration.setLayoutParams(lpRegistration);

        //Set images weight
        FrameLayout flImages = (FrameLayout) findViewById(R.id.fragm_right_images);
        LinearLayout.LayoutParams lpImages = new LinearLayout.LayoutParams(
                0,
                ViewGroup.LayoutParams.MATCH_PARENT,
                weigthRightFragment);
        flImages.setLayoutParams(lpImages);


    }

}
