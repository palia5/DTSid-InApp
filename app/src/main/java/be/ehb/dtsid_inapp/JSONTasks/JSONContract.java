package be.ehb.dtsid_inapp.JSONTasks;

import android.app.Application;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * Created by tomna_000 on 01/06/2015.
 */
public class JSONContract {

    public static final String BASEURL = "vdabsidin2.appspot.com";

    //YEAR calculator
    public static String yearCalc(){
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        Calendar currentTime = Calendar.getInstance();
        Calendar yearSwitch = Calendar.getInstance();
        yearSwitch.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
        yearSwitch.set(Calendar.MONTH, Calendar.OCTOBER);
        yearSwitch.set(Calendar.DAY_OF_MONTH, 1);

        if (currentTime.after(yearSwitch) || currentTime.equals(yearSwitch)){
            sb.append(yearFormat.format(currentTime.getTime()));
            yearSwitch.set(Calendar.YEAR, currentTime.get(Calendar.YEAR) + 1);
            sb.append(yearFormat.format(yearSwitch.getTime()));
        }
        else {
            yearSwitch.set(Calendar.YEAR, currentTime.get(Calendar.YEAR) - 1);
            sb.append(yearFormat.format(yearSwitch.getTime()));
            sb.append(yearFormat.format(currentTime.getTime()));
        }
        return sb.toString();
    }

    //GET requests
    public static final String ALL_TEACHERS = "/rest/teachers";
    public static final String ALL_EVENTS = "/rest/events";
    public static final String ALL_SCHOOLS = "/rest/schools";
    public static final String ALL_SUBSCRIPTIONS = "/rest/subscriptions";
    public static final String ALL_IMAGES = "/rest/images";
    public static final String YEAR_1415 = "/1415";


    //POST request
    public static final String POST_SUBSCRIPTION = "/rest/subscription";
}
