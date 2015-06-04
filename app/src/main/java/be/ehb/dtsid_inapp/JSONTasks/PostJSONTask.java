package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Subscription;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;


public class PostJSONTask extends AsyncTask<Void, Integer, HashMap<String, Boolean>> {
    private DatabaseContract dbc;
    private List<Subscription> subscriptionList;

    public PostJSONTask(Context c){
        dbc = new DatabaseContract(c);
    }


    @Override
    protected HashMap<String, Boolean> doInBackground(Void... params) {
        HashMap resultMap = new HashMap<String, Boolean>();
        subscriptionList = dbc.getAllSubscriptions();
        ArrayList<Subscription> checkList = new ArrayList<>();
        try {

            //TEST le Post avec le TEST Subscription
            Subscription testSub = new Subscription(null, "Maarten", "Van Uytsel", "maarten1997@ggmail.com",
                    "Doedoensstraat", "22", "2800", "Mechelen", false, true, false,
                    Calendar.getInstance().getTime(), dbc.getTeacherByID(4683438497988608l),
                    dbc.getEventByID(4814888656437248l), true, dbc.getSchoolByID(6312278001451008l));

            dbc.createSubscription(testSub);


            URL postUrl = new URL(BASEURL + POST_SUBSCRIPTION);
            HttpURLConnection postConnection = (HttpURLConnection) postUrl.openConnection();
            postConnection.setDoOutput(true);
            postConnection.setDoInput(true);
            postConnection.setRequestMethod("POST");
            postConnection.setRequestProperty("Content-Type", "application/json");
            postConnection.setRequestProperty("Accept", "application/json");
            //postConnection.setFixedLengthStreamingMode(jsonString.getBytes().length);
            postConnection.connect();
            OutputStream os = postConnection.getOutputStream();

            for (int i = 0; i < subscriptionList.size(); i++) {

                Subscription tempSub = subscriptionList.get(i);

                if (tempSub.getNew()) {
                    Log.d("POST Task", tempSub.getFirstName());
                    tempSub.setNew(false);
                    GsonBuilder gb = new GsonBuilder();
                    gb.excludeFieldsWithoutExposeAnnotation();
                    Gson gson = gb.create();
                    String jsonString = POST_SUBSCRIPTION_START + gson.toJson(tempSub)
                            + POST_SUBSCRIPTION_END;

                    os.write(jsonString.getBytes());
                    checkList.add(tempSub);
                }
            }
            os.flush();
            os.close();

// GETTING IT ALL FROM THE REST AGAIN!!!

            URL url = new URL(BASEURL + ALL_SUBSCRIPTIONS);
            HttpURLConnection getConnection = (HttpURLConnection) url.openConnection();
            getConnection.setRequestMethod("GET");
            getConnection.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(getConnection.getInputStream()));
            String jsonGetString = reader.readLine();

            JSONObject rawSubs = new JSONObject(jsonGetString);
            JSONArray subsArray = rawSubs.getJSONArray(JSON_NAME_SUBSCRIPTIONS);
            ArrayList<Subscription> subsList = new ArrayList<>();

            for (int i = 0; i< subsArray.length(); i++){
                JSONObject o = subsArray.getJSONObject(i);
                JSONObject oInterests = o.getJSONObject(JSON_NAME_INTERESTS);
                JSONObject oTeacher = o.getJSONObject(JSON_NAME_TEACHER);
                JSONObject oSchool = o.getJSONObject(JSON_NAME_SCHOOL);
                JSONObject oEvent = o.getJSONObject(JSON_NAME_EVENT);

                Subscription temp = new Subscription(o.getString(JSON_STRING_FIRSTNAME),
                        o.getString(JSON_STRING_LASTNAME), o.getString(JSON_STRING_EMAIL),
                        o.getString(JSON_STRING_STREET), o.getString(JSON_STRING_STREETNUMBER),
                        o.getString(JSON_STRING_ZIP), o.getString(JSON_STRING_CITY),
                        Boolean.parseBoolean(oInterests.getString(JSON_STRING_DIGX)),
                        Boolean.parseBoolean(oInterests.getString(JSON_STRING_MULTEC)),
                        Boolean.parseBoolean(oInterests.getString(JSON_STRING_WERKSTUDENT)),
                        new Date(o.getLong(JSON_LONG_TIMESTAMP)),
                        dbc.getTeacherByID(oTeacher.getLong(JSON_LONG_ID)),
                        dbc.getEventByID(oEvent.getLong(JSON_LONG_ID)),
                        o.getBoolean(JSON_BOOL_NEW),
                        dbc.getSchoolByID(oSchool.getLong(JSON_LONG_ID)));

                for (int j = 0; j < checkList.size(); j++) {
                    if (temp.getTimestamp().equals(checkList.get(j).getTimestamp()) &&
                    temp.getEmail().equals(checkList.get(j).getEmail())) {
                        checkList.remove(j);
                    }
                }
            }

            if (checkList.size() > 0) {
                for (int k = 0; k < checkList.size(); k++) {
                    Log.d("Failed to upload: ", checkList.get(k).toString());
                    // terug aan locale db meegeven.
                    // isNew terug op true zetten.
                }
            }



        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        return resultMap;
    }
}
