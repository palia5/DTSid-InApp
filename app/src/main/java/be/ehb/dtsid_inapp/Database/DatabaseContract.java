package be.ehb.dtsid_inapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.util.ArrayList;
import java.util.List;

import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;

public class DatabaseContract
{
    //Variables
    private MySQLiteHelper dbHelper;
    private SQLiteDatabase db;

    //Constructor
    public DatabaseContract(Context context)
    {
        this.dbHelper = new MySQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    //Methods
    public void close()
    {
        dbHelper.close();
        db.close();
    }
    public void createSubscription(Subscription newSub)
    {
        ContentValues values = new ContentValues();
        values.put(dbHelper.COL_SUBSCRIPTIONS_FIRSTNAME, newSub.getFirstName());
        values.put(dbHelper.COL_SUBSCRIPTIONS_LASTNAME, newSub.getLastName());
        values.put(dbHelper.COL_SUBSCRIPTIONS_EMAIL, newSub.getEmail());
        values.put(dbHelper.COL_SUBSCRIPTIONS_STREET, newSub.getStreet());
        values.put(dbHelper.COL_SUBSCRIPTIONS_STREETNUMBER, newSub.getStreetNumber());
        values.put(dbHelper.COL_SUBSCRIPTIONS_ZIP, newSub.getZip());
        values.put(dbHelper.COL_SUBSCRIPTIONS_CITY, newSub.getCity());
        values.put(dbHelper.COL_SUBSCRIPTIONS_DIGX, newSub.getDigx());
        values.put(dbHelper.COL_SUBSCRIPTIONS_WERKSTUDENT, newSub.getWerkstudent());
        values.put(dbHelper.COL_SUBSCRIPTIONS_MULTEC, newSub.getMultec());
        values.put(dbHelper.COL_SUBSCRIPTIONS_TIMESTAMP, newSub.getTimestamp().toString());
        values.put(dbHelper.COL_SUBSCRIPTIONS_ISNEW, newSub.isNew());
        values.put(dbHelper.COL_SUBSCRIPTIONS_TEACHER, newSub.getTeacher().getId());
        values.put(dbHelper.COL_SUBSCRIPTIONS_EVENT, newSub.getEvent().getId());
        values.put(dbHelper.COL_SUBSCRIPTIONS_SCHOOL, newSub.getSchool().getId());

        db.insert(dbHelper.TABLE_SUBSCRIPTIONS, null, values);
    }

    //Getters
    public Teacher getTeacherByID(long id)
    {
        Teacher tempTeacher;

        Cursor c = db.query(false,
                dbHelper.TABLE_TEACHERS,
                dbHelper.ALL_COLUMNS_TEACHERS,
                dbHelper.COL_TEACHERS_ID + " = " + id,
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
                dbHelper.TABLE_TEACHERS,
                dbHelper.ALL_COLUMNS_TEACHERS,
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
                dbHelper.TABLE_EVENTS,
                dbHelper.ALL_COLUMNS_EVENTS,
                dbHelper.COL_EVENTS_ID + " = " + id,
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
                dbHelper.TABLE_EVENTS,
                dbHelper.ALL_COLUMNS_EVENTS,
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
                dbHelper.TABLE_SCHOOLS,
                dbHelper.ALL_COLUMNS_SCHOOLS,
                dbHelper.COL_SCHOOLS_ID + " = " + id,
                null,
                null,
                null,
                null,
                null
                );

        tempSchool = cursorToSchool(c);

        c.close();
        return tempSchool;
    }
    public List<School> getAllSchools()
    {
        List<School> schools = new ArrayList<>();

        Cursor c = db.query(false,
                dbHelper.TABLE_SCHOOLS,
                dbHelper.ALL_COLUMNS_SCHOOLS,
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
            values.put(dbHelper.COL_EVENTS_NAME, events.get(i).getName());
            values.put(dbHelper.COL_EVENTS_ACADYEAR, events.get(i).getAcadyear());

            db.insert(dbHelper.TABLE_EVENTS, null, values);
        }
    }
    public void setAllSchools(List<School> schools)
    {
        ContentValues values = new ContentValues();

        for(int i = 0 ; i < schools.size() ; i++)
        {
            values.put(dbHelper.COL_SCHOOLS_NAME, schools.get(i).getName());
            values.put(dbHelper.COL_SCHOOLS_ZIP, schools.get(i).getZip());
            values.put(dbHelper.COL_SCHOOLS_CITY, schools.get(i).getCity());

            db.insert(dbHelper.TABLE_EVENTS, null, values);
        }
    }

    //Cursor to ...
    private Teacher cursorToTeacher(Cursor c)
    {
        Teacher temp = new Teacher();

        temp.setId(c.getLong(c.getColumnIndex(dbHelper.COL_TEACHERS_ID)));
        temp.setName(c.getString(c.getColumnIndex(dbHelper.COL_TEACHERS_NAME)));
        temp.setAcadyear(c.getInt(c.getColumnIndex(dbHelper.COL_TEACHERS_ACADYEAR)));

        return temp;
    }
    private Event cursorToEvent(Cursor c)
    {
        Event temp = new Event();

        temp.setId(c.getLong(c.getColumnIndex(dbHelper.COL_EVENTS_ID)));
        temp.setName(c.getString(c.getColumnIndex(dbHelper.COL_EVENTS_NAME)));
        temp.setAcadyear(c.getInt(c.getColumnIndex(dbHelper.COL_EVENTS_ACADYEAR)));

        return temp;
    }
    private School cursorToSchool(Cursor c)
    {
        School temp = new School();

        temp.setId(c.getLong(c.getColumnIndex(dbHelper.COL_SCHOOLS_ID)));
        temp.setName(c.getString(c.getColumnIndex(dbHelper.COL_SCHOOLS_NAME)));
        temp.setCity(c.getString(c.getColumnIndex(dbHelper.COL_SCHOOLS_CITY)));
        temp.setZip(c.getInt(c.getColumnIndex(dbHelper.COL_SCHOOLS_ZIP)));

        return temp;
    }
    private Bitmap cursorToImage(Cursor c)
    {
        byte[] data = c.getBlob(c.getColumnIndex(dbHelper.COL_IMAGES_IMAGE));

        Bitmap temp = BitmapFactory.decodeByteArray(data,0, data.length);

        return temp;
    }
}
