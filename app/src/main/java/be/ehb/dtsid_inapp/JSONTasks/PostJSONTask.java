package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
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
        HashMap resultMap = new HashMap<>();
        //subscriptionArrayList = dbc.getAllSubscriptions;
        try {

            //TEST le Post avec le TEST Subscription
            Subscription testSub = new Subscription(null, "Maarten", "Van Uytsel", "maarten1997@ggmail.com",
                    "Doedoensstraat", "22", "2800", "Mechelen", false, true, false,
                    Calendar.getInstance().getTime(), dbc.getTeacherByID(4683438497988608l),
                    dbc.getEventByID(4814888656437248l), true, dbc.getSchoolByID(6312278001451008l));

            GsonBuilder gb = new GsonBuilder();
            gb.excludeFieldsWithoutExposeAnnotation();
            Gson gson = gb.create();
            String jsonString = gson.toJson(testSub);

            URL postUrl = new URL(BASEURL + POST_SUBSCRIPTION);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setDoOutput(true);
            postConnection.setDoInput(true);
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            postConnection.setFixedLengthStreamingMode(jsonString.getBytes().length);
            postConnection.connect();

            OutputStream os = postConnection.getOutputStream();

            os.write(jsonString.getBytes());
            os.flush();

            Log.d("JSON GSON", "{\"subscription\":" + jsonString + "}");
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return resultMap;
    }
}
