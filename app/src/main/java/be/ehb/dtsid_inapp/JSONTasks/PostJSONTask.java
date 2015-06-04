package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Subscription;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;


public class PostJSONTask extends AsyncTask<Void, Integer, HashMap<String, Boolean>> {
    private DatabaseContract dbc;
    private ArrayList<Subscription> subscriptionArrayList;

    public PostJSONTask(Context c){
        dbc = new DatabaseContract(c);
    }


    @Override
    protected HashMap<String, Boolean> doInBackground(Void... params) {
        HashMap resultMap = new HashMap<String, Boolean>();
        //subscriptionArrayList = dbc.getAllSubscriptions;
        try {
            URL postUrl = new URL(BASEURL + POST_SUBSCRIPTION);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setRequestMethod("POST");
            postConnection.connect();

            //TEST le Post avec le TEST Subscription
            Subscription testSub = new Subscription("Maarten", "Van Uytsel", "maarten1997@ggmail.com",
                    "Doedoensstraat", "22", "2800", "Mechelen", false, true, false,
                    Calendar.getInstance().getTime(), dbc.getTeacherByID(4683438497988608l),
                    dbc.getEventByID(4814888656437248l), true, dbc.getSchoolByID(6312278001451008l));
            Gson gson = new Gson();
            String jsonString = gson.toJson(testSub);
            Log.d("JSON GSON", jsonString);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }




        return resultMap;
    }
}
