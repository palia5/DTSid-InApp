package be.ehb.dtsid_inapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.Image;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;

public class DatabaseContract
{
    //Variables
    private SQLiteDatabase db;

    //Constructor
    public DatabaseContract(Context context)
    {
        DatabaseManager.initializeInstance(context);
        db = DatabaseManager.getInstance().openDatabase();
    }

    //Methods
    public void close()
    {
        DatabaseManager.getInstance().closeDatabase();
    }

    public void createSubscription(Subscription newSub)
    {
        ContentValues values = new ContentValues();
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_FIRSTNAME, newSub.getFirstName());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_LASTNAME, newSub.getLastName());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_EMAIL, newSub.getEmail());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_STREET, newSub.getStreet());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_STREETNUMBER, newSub.getStreetNumber());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_ZIP, newSub.getZip());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_CITY, newSub.getCity());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_DIGX, newSub.getDigx());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_WERKSTUDENT, newSub.getWerkstudent());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_MULTEC, newSub.getMultec());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP, Long.toString(newSub.getTimestamp().getTime()));

        /*Log.d("DBC Create", newSub.getFirstName() + newSub.getTimestampLong());
        Log.d("DBC Create", newSub.getFirstName() + newSub.getTimestamp());
        Log.d("DBC Create", newSub.getFirstName() + newSub.getTimestamp().toString());

*/

        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW, newSub.getNew());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TEACHER, newSub.getTeacher().getId());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_EVENT, newSub.getEvent().getId());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_SCHOOL, newSub.getSchool().getId());

        Log.d("DBC Create", Boolean.toString(newSub.getNew()));
        db.insert(MySQLiteHelper.TABLE_SUBSCRIPTIONS, null, values);
    }

    //Getters
    public Teacher getTeacherByID(long id)
    {
        Teacher tempTeacher;

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_TEACHERS,
                MySQLiteHelper.ALL_COLUMNS_TEACHERS,
                MySQLiteHelper.COL_TEACHERS_ID + " = " + id,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        tempTeacher = cursorToTeacher(c);

        c.close();
        return tempTeacher;
    }
    public List<Teacher> getAllTeachers()
    {
        List<Teacher> teachers = new ArrayList<>();

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_TEACHERS,
                MySQLiteHelper.ALL_COLUMNS_TEACHERS,
                null,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            teachers.add(cursorToTeacher(c));
            c.moveToNext();
        }

        c.close();
        return teachers;
    }
    public Event getEventByID(long id)
    {
        Event tempEvent;

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_EVENTS,
                MySQLiteHelper.ALL_COLUMNS_EVENTS,
                MySQLiteHelper.COL_EVENTS_ID + " = " + id,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        tempEvent = cursorToEvent(c);

        c.close();
        return tempEvent;
    }
    public List<Event> getAllEvents()
    {
        List<Event> events = new ArrayList<>();

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_EVENTS,
                MySQLiteHelper.ALL_COLUMNS_EVENTS,
                null,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            events.add(cursorToEvent(c));
            c.moveToNext();
        }

        c.close();
        return events;
    }
    public School getSchoolByID(long id)
    {
        School tempSchool;

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_SCHOOLS,
                MySQLiteHelper.ALL_COLUMNS_SCHOOLS,
                MySQLiteHelper.COL_SCHOOLS_ID + " = " + id,
                null,
                null,
                null,
                null,
                null
                );
        c.moveToFirst();

        tempSchool = cursorToSchool(c);

        c.close();
        return tempSchool;
    }
    public List<School> getAllSchools()
    {
        List<School> schools = new ArrayList<>();

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_SCHOOLS,
                MySQLiteHelper.ALL_COLUMNS_SCHOOLS,
                null,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            schools.add(cursorToSchool(c));
            c.moveToNext();
        }

        c.close();
        return schools;
    }
    public Subscription getSubscriptionByID(int id)
    {
        Subscription tempSubscription;

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_SUBSCRIPTIONS,
                MySQLiteHelper.ALL_COLUMNS_SUBSCRIPTION,
                MySQLiteHelper.COL_SUBSCRIPTIONS_ID + " = " + id,
                null,
                null,
                null,
                null,
                null
        );

        c.moveToFirst();

        try
        {
            tempSubscription = cursorToSubscription(c);

            c.close();
            return tempSubscription;
        }
        catch (ParseException e)
        {
            e.printStackTrace();

            c.close();
            return null;
        }
    }
    public List<Subscription> getAllSubscriptions()
    {
        List<Subscription> subscriptions = new ArrayList<>();

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_SUBSCRIPTIONS,
                MySQLiteHelper.ALL_COLUMNS_SUBSCRIPTION,
                null,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            try
            {
                subscriptions.add(cursorToSubscription(c));
            }
            catch (ParseException e)
            {
                e.printStackTrace();
            }
            c.moveToNext();
        }

        c.close();
        return subscriptions;
    }

    public List<Image> getAllImages()
    {
        List<Image> images = new ArrayList<>();

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_IMAGES,
                MySQLiteHelper.ALL_COLUMNS_IMAGES,
                null,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            images.add(cursorToImage(c));
            c.moveToNext();
        }

        c.close();
        return images;
    }

    public List<Bitmap> getAllBitmaps()
    {
        List<Bitmap> bitmaps = new ArrayList<>();

        Cursor c = db.query(false,
                MySQLiteHelper.TABLE_IMAGES,
                MySQLiteHelper.ALL_COLUMNS_IMAGES,
                null,
                null,
                null,
                null,
                null,
                null);

        c.moveToFirst();

        while(!c.isAfterLast())
        {
            bitmaps.add(cursorToBitmap(c));

            c.moveToNext();
        }

        c.close();
        return bitmaps;

    }

    //Setters
    public void setAllEvents(List<Event> events)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < events.size() ; i++)
        {
            values.put(MySQLiteHelper.COL_EVENTS_ID, events.get(i).getId());
            values.put(MySQLiteHelper.COL_EVENTS_NAME, events.get(i).getName());
            values.put(MySQLiteHelper.COL_EVENTS_ACADYEAR, events.get(i).getAcadyear());

            db.insert(MySQLiteHelper.TABLE_EVENTS, null, values);
        }
    }
    public void setAllSchools(List<School> schools)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < schools.size() ; i++)
        {
            values.put(MySQLiteHelper.COL_SCHOOLS_ID, schools.get(i).getId());
            values.put(MySQLiteHelper.COL_SCHOOLS_NAME, schools.get(i).getName());
            values.put(MySQLiteHelper.COL_SCHOOLS_ZIP, schools.get(i).getZip());
            values.put(MySQLiteHelper.COL_SCHOOLS_CITY, schools.get(i).getCity());

            db.insert(MySQLiteHelper.TABLE_SCHOOLS, null, values);
        }
    }
    public void setAllSubscriptions(List<Subscription> subscriptions)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < subscriptions.size() ; i++)
        {
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_FIRSTNAME, subscriptions.get(i).getFirstName());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_LASTNAME, subscriptions.get(i).getLastName());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_EMAIL, subscriptions.get(i).getEmail());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_STREET, subscriptions.get(i).getStreet());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_STREETNUMBER, subscriptions.get(i).getStreetNumber());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_ZIP, subscriptions.get(i).getZip());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_CITY, subscriptions.get(i).getCity());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_DIGX, subscriptions.get(i).getDigx());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_MULTEC, subscriptions.get(i).getMultec());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_WERKSTUDENT, subscriptions.get(i).getWerkstudent());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP, subscriptions.get(i).getTimestamp().getTime());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TEACHER, subscriptions.get(i).getTeacher().getId());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_EVENT, subscriptions.get(i).getEvent().getId());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW, subscriptions.get(i).getNew());
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_SCHOOL, subscriptions.get(i).getSchool().getId());

            db.insert(MySQLiteHelper.TABLE_SUBSCRIPTIONS, null, values);
        }
    }
    public void setAllTeachers(List<Teacher> teachers)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < teachers.size() ; i++)
        {
            values.put(MySQLiteHelper.COL_TEACHERS_ID, teachers.get(i).getId());
            values.put(MySQLiteHelper.COL_TEACHERS_NAME, teachers.get(i).getName());
            values.put(MySQLiteHelper.COL_TEACHERS_ACADYEAR, teachers.get(i).getAcadyear());

            db.insert(MySQLiteHelper.TABLE_TEACHERS, null, values);
        }
    }
    public void setAllImages(List<Image> images)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < images.size() ; i++)
        {
            values.put(MySQLiteHelper.COL_IMAGES_ID, images.get(i).getId());
            values.put(MySQLiteHelper.COL_IMAGES_PRIORITY, images.get(i).getPriority());
            values.put(MySQLiteHelper.COL_IMAGES_IMAGE, images.get(i).getImage());

            db.insert(MySQLiteHelper.TABLE_IMAGES, null, values);
        }
    }

    //Cursor to ...
    private Teacher cursorToTeacher(Cursor c)
    {
        Teacher temp = new Teacher();

        temp.setId(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_TEACHERS_ID)));
        temp.setName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_TEACHERS_NAME)));
        temp.setAcadyear(c.getInt(c.getColumnIndex(MySQLiteHelper.COL_TEACHERS_ACADYEAR)));

        return temp;
    }
    private Event cursorToEvent(Cursor c)
    {
        Event temp = new Event();

        temp.setId(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_EVENTS_ID)));
        temp.setName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_EVENTS_NAME)));
        temp.setAcadyear(c.getInt(c.getColumnIndex(MySQLiteHelper.COL_EVENTS_ACADYEAR)));

        return temp;
    }
    private School cursorToSchool(Cursor c)
    {
        School temp = new School();

        temp.setId(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SCHOOLS_ID)));
        temp.setName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SCHOOLS_NAME)));
        temp.setCity(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SCHOOLS_CITY)));
        temp.setZip(c.getInt(c.getColumnIndex(MySQLiteHelper.COL_SCHOOLS_ZIP)));

        return temp;
    }
    private Image cursorToImage(Cursor c)
    {
        Image temp = new Image();

        temp.setId(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_IMAGES_ID)));
        temp.setPriority(c.getInt(c.getColumnIndex(MySQLiteHelper.COL_IMAGES_PRIORITY)));
        temp.setImage(c.getBlob(c.getColumnIndex(MySQLiteHelper.COL_IMAGES_IMAGE)));

        return temp;
    }
    private Bitmap cursorToBitmap(Cursor c)
    {
        byte[] data = c.getBlob(c.getColumnIndex(MySQLiteHelper.COL_IMAGES_IMAGE));
        Log.d("CHECK_DATA", data.toString());
        return BitmapFactory.decodeByteArray(data, 0, data.length);
    }
    private Subscription cursorToSubscription(Cursor c) throws ParseException
    {
        Subscription temp = new Subscription();

        temp.setId(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ID)));
        temp.setFirstName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_FIRSTNAME)));
        temp.setLastName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_LASTNAME)));
        temp.setEmail(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_EMAIL)));
        temp.setStreet(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_STREET)));
        temp.setStreetNumber(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_STREETNUMBER)));
        temp.setZip(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ZIP)));
        temp.setCity(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_CITY)));

        //Boolean is opgeslaan als 1 of 0
        Log.d("TEST", c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_FIRSTNAME)));
        Log.d("TEST", c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW)));
        temp.setDigx(((c.getInt(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_DIGX))) == 1) ? true : false);
        temp.setMultec(((c.getInt(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_MULTEC))) == 1) ? true : false);
        temp.setWerkstudent(((c.getInt(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_WERKSTUDENT))) == 1) ? true : false);

        /*String dateString = c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
        Date dt = sdf.parse(dateString);*/
        temp.setTimestamp(new Date(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP))));
        //Log.d("TEST", c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP)) + "");

        Long teachID = c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_TEACHER));
        Teacher tempTeacher = getTeacherByID(teachID);
        temp.setTeacher(tempTeacher);

        Long eventID = c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_EVENT));
        Event tempEvent = getEventByID(eventID);
        temp.setEvent(tempEvent);

        Long schoolID = c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_SCHOOL));
        School tempSchool = getSchoolByID(schoolID);
        temp.setSchool(tempSchool);

        temp.setNew(((c.getInt(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW))) == 1 )?true:false);
        //Log.d("DBC", temp.getFirstName() + Boolean.toString(temp.getNew()));

        temp.setInterests();
        temp.setTimestampLong();
        return temp;
    }
}