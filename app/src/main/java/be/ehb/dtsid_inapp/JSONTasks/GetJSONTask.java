package be.ehb.dtsid_inapp.JSONTasks;

import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Models.Teacher;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

/**
 * Created by tomna_000 on 01/06/2015.
 */
public class GetJSONTask extends AsyncTask<String, Integer, Void> {
    @Override
    protected Void doInBackground(String... params) {
        if (params[0].contains(ALL_TEACHERS)){
            try {
                URL url = new URL(params[0]);
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();
                connection.setRequestMethod("GET");
                connection.connect();

                BufferedReader reader = new BufferedReader(
                        new InputStreamReader(connection.getInputStream()));
                String jsonString = reader.readLine();
                JSONObject rawTeachers = new JSONObject(jsonString);
                JSONArray teachersArray = rawTeachers.getJSONArray(JSON_NAME_TEACHERS);
                ArrayList<Teacher> teacherList = new ArrayList<>();

                for (int i = 0; i < teachersArray.length(); i++){
                    JSONObject o = teachersArray.getJSONObject(i);
                    Teacher temp = new Teacher(o.getString(JSON_STRING_NAME), o.getInt(JSON_INT_ACADYEAR));
                    Log.d("TEACHER", temp.getName());
                    //teacherList.add(temp);
                }

                //DAO.addTeacherList(teacherList);


            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
