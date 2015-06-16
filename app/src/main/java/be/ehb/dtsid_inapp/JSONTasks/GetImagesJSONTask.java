package be.ehb.dtsid_inapp.JSONTasks;

import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.Base64;
import android.util.JsonReader;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Database.DatabaseContract;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.TeacherFragments.DepartmentLogin;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_INT_PRIORITY;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_LONG_ID;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_NAME_IMAGE;
import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.JSON_NAME_IMAGES;

import static be.ehb.dtsid_inapp.JSONTasks.JSONContract.*;

public class GetImagesJSONTask extends AsyncTask<String, Integer, Void> {

    private DepartmentLogin fragment;

    private DatabaseContract dbc;

    public GetImagesJSONTask(DepartmentLogin c)
    {
        fragment = c;
        dbc = new DatabaseContract(fragment.getActivity().getApplicationContext());
    }

    @Override
    protected Void doInBackground(String... params)
    {
        try
        {
            URL urlImages = new URL(params[0]);
            HttpURLConnection connection = (HttpURLConnection) urlImages.openConnection();
            connection.setRequestMethod("GET");
            connection.connect();

            JsonReader reader = new JsonReader(
                    new InputStreamReader(connection.getInputStream()));

            List<Image> imageList = new ArrayList<>();

            reader.beginObject();
            reader.nextName();
            reader.beginArray();
            while(reader.hasNext())
            {
                reader.beginObject();

                Image newImage = new Image();

                reader.nextName();
                newImage.setPriority(reader.nextInt());

                reader.nextName();
                newImage.setId(reader.nextLong());

                String fileName = "picture_" + imageList.size() + ".jpeg";
                newImage.setImage(fileName);

                reader.nextName();
                String image = reader.nextString();

                FileOutputStream outputStream;
                outputStream = fragment.getActivity().openFileOutput(fileName, Context.MODE_PRIVATE);

                byte[] encodedImage = Base64.decode(image, Base64.DEFAULT);

                outputStream.write(encodedImage);
                outputStream.close();

                imageList.add(newImage);

                reader.endObject();
            }

            connection.disconnect();
            reader.close();

            dbc.setAllImages(imageList);

            dbc.close();

            fragment.everythingIsLoaded(true);
        }
        catch (MalformedURLException| ProtocolException e)
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
