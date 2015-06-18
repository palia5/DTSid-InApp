package be.ehb.dtsid_inapp.JSONTasks;

import android.app.Activity;
import android.util.Log;
import android.widget.ImageView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.concurrent.ExecutionException;

/**
 *
 * @author Tom
 * @version 1.0
 *
 *
 */

public class JSONContract
{
    //public static final String BASEURL = "http://vdabsidin2.appspot.com";

    public static final String DEPTCODE_URL = "http://deptcodes.appspot.com/deptcode/";

    public static String generateBaseURL(String secret, Activity activity) throws ExecutionException, InterruptedException {
        GetBaseUrl getBaseUrl = new GetBaseUrl(activity);
        String codeUrl = DEPTCODE_URL + secret;
        getBaseUrl.execute(codeUrl);
        String baseUrl = getBaseUrl.get();
//        Log.d("TEST JSONcontract", baseUrl);
        return baseUrl;
    }

    //YEAR calculator
    public static String yearCalc()
    {
        SimpleDateFormat yearFormat = new SimpleDateFormat("yy");
        StringBuilder sb = new StringBuilder();
        sb.append("/");
        Calendar currentTime = Calendar.getInstance();
        Calendar yearSwitch = Calendar.getInstance();
        yearSwitch.set(Calendar.YEAR, currentTime.get(Calendar.YEAR));
        yearSwitch.set(Calendar.MONTH, Calendar.OCTOBER);
        yearSwitch.set(Calendar.DAY_OF_MONTH, 1);

        if (currentTime.after(yearSwitch) || currentTime.equals(yearSwitch))
        {
            sb.append(yearFormat.format(currentTime.getTime()));
            yearSwitch.set(Calendar.YEAR, currentTime.get(Calendar.YEAR) + 1);
            sb.append(yearFormat.format(yearSwitch.getTime()));
        }
        else
        {
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


    public static final String JSON_NAME_EVENTS = "events";
    public static final String JSON_NAME_TEACHERS = "teachers";
    public static final String JSON_NAME_SCHOOLS = "schools";
    public static final String JSON_NAME_SUBSCRIPTIONS = "subscriptions";
    public static final String JSON_NAME_IMAGES = "images";

    public static final String JSON_NAME_INTERESTS = "interests";
    public static final String JSON_NAME_TEACHER = "teacher";
    public static final String JSON_NAME_EVENT = "event";
    public static final String JSON_NAME_SCHOOL = "school";
    public static final String JSON_NAME_IMAGE = "image";


    public static final String JSON_STRING_NAME = "name";
    public static final String JSON_INT_ACADYEAR = "acadyear";
    public static final String JSON_STRING_GEMEENTE = "gemeente";
    public static final String JSON_STRING_POSTCODE = "postcode";
    public static final String JSON_STRING_FIRSTNAME = "firstName";
    public static final String JSON_STRING_LASTNAME = "lastName";
    public static final String JSON_STRING_EMAIL = "email";
    public static final String JSON_STRING_STREET = "street";
    public static final String JSON_STRING_STREETNUMBER = "streetNumber";
    public static final String JSON_STRING_ZIP = "zip";
    public static final String JSON_STRING_CITY = "city";
    public static final String JSON_STRING_DIGX = "digx";
    public static final String JSON_STRING_MULTEC = "multec";
    public static final String JSON_STRING_WERKSTUDENT = "werkstudent";
    public static final String JSON_LONG_TIMESTAMP = "timestamp";
    public static final String JSON_LONG_ID = "id";
    public static final String JSON_BOOL_NEW = "new";
    public static final String JSON_INT_PRIORITY = "priority";

    //POST request
    public static final String POST_SUBSCRIPTION = "/rest/subscription";
    public static final String POST_SUBSCRIPTION_START = "{\"subscription\":";
    public static final String POST_SUBSCRIPTION_END = "}";
}
