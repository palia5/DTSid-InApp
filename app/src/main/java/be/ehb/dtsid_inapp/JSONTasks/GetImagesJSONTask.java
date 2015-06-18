package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_INT_PRIORITY;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_LONG_ID;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_NAME_IMAGE;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_NAME_IMAGES;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

/**
 *
 * @author Kristof
 * @version 1.0
 *
 *
 */

public class GetImagesJSONTask extends AsyncTask<String, Integer, Void> {

    private DepartmentLogin fragment;

    public GetImagesJSONTask(DepartmentLogin c)
    {
        fragment = c;
    }


    @Override
    protected Void doInBackground(String... params) {

        try {

            URL url = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(connection.getInputStream()));
            String jsonString = reader.readLine();

            if (params[0].contains(ALL_IMAGES)) {

                JSONObject rawImages = new JSONObject(jsonString);
                JSONArray imagesArray = rawImages.getJSONArray(JSON_NAME_IMAGES);
                ArrayList<Image> imageList = new ArrayList<>();
                String tempstring;
                String filename;
                FileOutputStream outputStream;

                for (int i = 0; i < imagesArray.length(); i++) {
                    JSONObject o = imagesArray.getJSONObject(i);
                    //tempstring = o.getString(JSON_NAME_IMAGE);

                    //bytearray opvullen vanuit de JSON
                    byte[] array = (byte[]) o.get(JSON_NAME_IMAGE);

                    //filename uniek maken voor elke afbeelding
                    filename = "picture_"+ i + ".jpeg";

                    //bytearray wegschrijven als jpeg met de filename
                    outputStream = fragment.getActivity().openFileOutput(filename, Context.MODE_PRIVATE);
                    outputStream.write(array);
                    outputStream.close();

                    //array opvullen met objecten die de prioriteit en locatie van de afbeeldingen bevatten
                    Image temp = new Image(o.getLong(JSON_LONG_ID), o.getInt(JSON_INT_PRIORITY),
                            filename);

                    imageList.add(temp);
                }
            }
        }
        catch (MalformedURLException| ProtocolException| JSONException e)
        {
            e.printStackTrace();
        }
        catch (UnknownHostException e)
        {
            Log.d("NO INTERNET", "THERE IS NO INTERNET");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        return null;
    }

}
