package be.ehb.dtsid_inapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.sql.Blob;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;


public class DatabaseContract {

    //Variables
    private MySQLiteHelper dbHelper;
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
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP, newSub.getTimestamp().toString());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW, newSub.getNew());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TEACHER, newSub.getTeacher().getId());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_EVENT, newSub.getEvent().getId());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_SCHOOL, newSub.getSchool().getId());

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

        tempSchool = cursorToSchool(c);
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

    //Setters
    public void setAllEvents(List<Event> events)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < events.size() ; i++)
        {
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
            values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TIMESTAMP, subscriptions.get(i).getTimestamp().toString());
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
            values.put(MySQLiteHelper.COL_TEACHERS_NAME, teachers.get(i).getName());
            values.put(MySQLiteHelper.COL_TEACHERS_ACADYEAR, teachers.get(i).getAcadyear());

            db.insert(MySQLiteHelper.TABLE_TEACHERS, null, values);
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

    private Bitmap cursorToImage(Cursor c)
    {
        byte[] data = c.getBlob(c.getColumnIndex(MySQLiteHelper.COL_IMAGES_IMAGE));

        Bitmap temp = BitmapFactory.decodeByteArray(data,0, data.length);

        return temp;
    }

    private Subscription cursorToSubscription(Cursor c) throws ParseException {
        Subscription temp = new Subscription();

        temp.setId(c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ID)));
        temp.setFirstName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_FIRSTNAME)));
        temp.setLastName(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_LASTNAME)));
        temp.setEmail(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_EMAIL)));
        temp.setStreet(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_STREET)));
        temp.setStreetNumber(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_STREETNUMBER)));
        temp.setZip(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ZIP)));
        temp.setCity(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_CITY)));
        temp.setDigx(Boolean.parseBoolean(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_DIGX))));
        temp.setMultec(Boolean.parseBoolean(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_MULTEC))));
        temp.setWerkstudent(Boolean.parseBoolean(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_WERKSTUDENT))));

        String dateString = c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_STREET));
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/YYYY", Locale.getDefault());
        Date dt = sdf.parse(dateString);
        temp.setTimestamp(dt);

        Long teachID = c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_TEACHER));
        Teacher tempTeacher = getTeacherByID(teachID);
        temp.setTeacher(tempTeacher);

        Long eventID = c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_EVENT));
        Event tempEvent = getEventByID(eventID);
        temp.setEvent(tempEvent);

        Long schoolID = c.getLong(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_SCHOOL));
        School tempSchool = getSchoolByID(schoolID);
        temp.setSchool(tempSchool);

        temp.setNew(Boolean.parseBoolean(c.getString(c.getColumnIndex(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW))));

        return temp;
    }

}
