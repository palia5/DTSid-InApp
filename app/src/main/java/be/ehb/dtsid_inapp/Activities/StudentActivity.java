package be.ehb.dtsid_inapp.Activities;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.LinearLayout;


import java.util.List;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.Models.Teacher;
import be.ehb.dtsid_inapp.Models.ZoomOutPageTransformer;
import be.ehb.dtsid_inapp.R;
import be.ehb.dtsid_inapp.StudentFragments.PhotoGallery;
import be.ehb.dtsid_inapp.StudentFragments.StudentRegistration;

public class StudentActivity extends AppCompatActivity
{
    private DatabaseContract dbc;
    private Boolean isInMainScreen = true;
    private Boolean isInSecondReg;
    private Teacher teacher;
    private Event event;
    StudentRegistration registrationFragment;
    PhotoGallery photoFragment;
    private ViewPager mPager;
    private PagerAdapter mPagerAdapter;
    private Subscription currentSubscription = null;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        isInSecondReg = false;

        dbc = new DatabaseContract(getApplicationContext());

        teacher = dbc.getTeacherByID(getIntent().getLongExtra("Teacher_id", 0));
        event = dbc.getEventByID(getIntent().getLongExtra("Event_id", 0));

        dbc.close();

        FragmentManager fm = getFragmentManager();
        FragmentTransaction ft = fm.beginTransaction();

        registrationFragment = new StudentRegistration();
        photoFragment = new PhotoGallery();

        ft.add(R.id.fragm_left_registration, registrationFragment);
        ft.add(R.id.fragm_right_images, photoFragment);

        ft.commit();
    }

    @Override
    public void onBackPressed()
    {
        if(isInMainScreen)
        {
            super.onBackPressed();
        }
        else
        {
            if (isInSecondReg) 
            {
                isInMainScreen = false;
                getFragmentManager().beginTransaction()
                        .replace(R.id.fragm_left_registration, registrationFragment)
                        .commit();
                registrationFragment.setEnabled(true);
                isInSecondReg = false;
                Log.d("MainScreen ", isInMainScreen.toString());
            }
            else 
            {
                changeWeightOfFragments(50, 50);
                registrationFragment.setEnabled(false);
                isInMainScreen = true;
            }
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

    private void changeWeightOfFragments(final float weightLeftFragment, final float weigthRightFragment)
    {
        //Set registration weight
        final FrameLayout flRegistration = (FrameLayout) findViewById(R.id.fragm_left_registration);

        //flRegistration.setLayoutParams(lpRegistration);

        Animation lAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                LinearLayout.LayoutParams lpRegistration = new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        50 + (weightLeftFragment - 50) * interpolatedTime);
                flRegistration.setLayoutParams(lpRegistration);
            }
        };

        //Set images weight
        final FrameLayout flImages = (FrameLayout) findViewById(R.id.fragm_right_images);

        //flImages.setLayoutParams(lpImages);

        Animation rAnim = new Animation() {
            @Override
            protected void applyTransformation(float interpolatedTime, Transformation t) {
                LinearLayout.LayoutParams lpImages = new LinearLayout.LayoutParams(
                        0,
                        ViewGroup.LayoutParams.MATCH_PARENT,
                        50 + (weightLeftFragment - 50) * interpolatedTime);
                flImages.setLayoutParams(lpImages);
            }
        };
        lAnim.setDuration(500);
        rAnim.setDuration(500);
        flRegistration.startAnimation(lAnim);
        flImages.startAnimation(rAnim);
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public Event getEvent() {
        return event;
    }

    public Subscription getCurrentSubscription() {
        return currentSubscription;
    }

    public void setCurrentSubscription(Subscription currentSubscription) {
        this.currentSubscription = currentSubscription;
    }


    public Boolean getIsInSecondReg() {
        return isInSecondReg;
    }

    public void setIsInSecondReg(Boolean isInSecondReg) {
        this.isInSecondReg = isInSecondReg;
    }
}