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

import be.ehb.dtsid_inapp.Models.Event;
import be.ehb.dtsid_inapp.Models.School;
import be.ehb.dtsid_inapp.Models.Subscription;
import be.ehb.dtsid_inapp.Models.Teacher;

/**
 * Created by Kristof on 1/06/2015.
 */
public class DatabaseContract {

    private MySQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public DatabaseContract(Context context) {
        this.dbHelper = new MySQLiteHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public void close(){
        dbHelper.close();
    }

    public void createSubscription(Subscription newSub){
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
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_ISNEW, newSub.isNew());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_TEACHER, newSub.getTeacher().getId());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_EVENT, newSub.getEvent().getId());
        values.put(MySQLiteHelper.COL_SUBSCRIPTIONS_SCHOOL, newSub.getSchool().getId());

        db.insert(MySQLiteHelper.TABLE_SUBSCRIPTIONS, null, values);
    }

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
        c.close();
        return tempSchool;
    }

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
