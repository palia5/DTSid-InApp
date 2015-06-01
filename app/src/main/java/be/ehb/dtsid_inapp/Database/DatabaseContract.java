package be.ehb.dtsid_inapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Kristof on 1/06/2015.
 */
public class DatabaseContract extends SQLiteOpenHelper{

    public static final String TABLE_TEACHERS ="teachers";
    public static final String COL_TEACHERS_ID ="_id";
    public static final String COL_TEACHERS_NAME = "name";
    public static final String COL_TEACHERS_ACADYEAR = "acadyear";

    public static final String[] ALL_TEACHERS_COLS = {COL_TEACHERS_ID, COL_TEACHERS_NAME, COL_TEACHERS_ACADYEAR};

    public static final String TABLE_EVENTS = "events";
    public static final String COL_EVENTS_ID = "_id";
    public static final String COL_EVENTS_NAME = "name";
    public static final String COL_EVENTS_ACADYEAR = "acadyear";

    public static final String[] ALL_EVENTS_COLS = {COL_EVENTS_ID, COL_EVENTS_NAME, COL_EVENTS_ACADYEAR};

    public static final String TABLE_SCHOOLS = "schools";
    public static final String COL_SCHOOLS_ID = "_id";
    public static final String COL_SCHOOLS_NAME = "name";
    public static final String COL_SCHOOLS_CITY = "city";
    public static final String COL_SCHOOLS_ZIP = "zip";

    public static final String[] ALL_SCHOOLS_COLS = {COL_SCHOOLS_ID, COL_SCHOOLS_NAME, COL_SCHOOLS_CITY, COL_SCHOOLS_ZIP};

    public static final String TABEL_SUBSCRIPTIONS = "subscriptions";
    public static final String COL_SUBSCRIPTIONS_ID = "_id";
    public static final String COL_SUBSCRIPTIONS_FIRSTNAME = "firstname";
    public static final String COL_SUBSCRIPTIONS_LASTNAME = "lastname";
    public static final String COL_SUBSCRIPTIONS_EMAIL = "email";
    public static final String COL_SUBSCRIPTIONS_STREET = "street";
    public static final String COL_SUBSCRIPTIONS_STREETNMBR = "streetnumber";
    public static final String COL_SUBSCRIPTIONS_ZIP = "zip";
    public static final String COL_SUBSCRIPTIONS_CITY = "city";
    public static final String COL_SUBSCRIPTIONS_INTERESTS = "interests";
    public static final String COL_SUBSCRIPTIONS_TIMESTAMP = "timestamp";
    public static final String COL_SUBSCRIPTIONS_ISNEW = "isnew";
    public static final String COL_SUBSCRIPTIONS_TEACHER = "teacher";
    public static final String COL_SUBSCRIPTIONS_EVENT = "event";
    public static final String COL_SUBSCRIPTIONS_SCHOOL = "school";

    public static final String[] ALL_SUBSCRIPTIONS_COLS = {COL_SUBSCRIPTIONS_ID,
            COL_SUBSCRIPTIONS_FIRSTNAME,
            COL_SUBSCRIPTIONS_LASTNAME,
            COL_SUBSCRIPTIONS_EMAIL,
            COL_SUBSCRIPTIONS_STREET,
            COL_SUBSCRIPTIONS_STREETNMBR,
            COL_SUBSCRIPTIONS_ZIP,
            COL_SUBSCRIPTIONS_CITY,
            COL_SUBSCRIPTIONS_INTERESTS,
            COL_SUBSCRIPTIONS_TIMESTAMP,
            COL_SUBSCRIPTIONS_ISNEW,
            COL_SUBSCRIPTIONS_TEACHER,
            COL_SUBSCRIPTIONS_EVENT,
            COL_SUBSCRIPTIONS_SCHOOL
    };

    public static final String TABLE_IMAGES = "events";
    public static final String COL_IMAGES_ID = "_id";
    public static final String COL_IMAGES_PRIORITY = "priority";
    public static final String COL_IMAGES_IMAGE = "image";

    public static final String[] ALL_IMAGES_COLS = {COL_IMAGES_ID, COL_IMAGES_PRIORITY, COL_IMAGES_IMAGE};


    public DatabaseContract(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(" CREATE TABLE teachers (" + "" +
                " _id LONG PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT NOT NULL, "+
                "acadyear INTEGER NOT NULL "+
                ") ");

        db.execSQL(" CREATE TABLE events (" + "" +
                " _id LONG PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT NOT NULL, "+
                "acadyear INTEGER NOT NULL "+
                ") ");

        db.execSQL(" CREATE TABLE schools (" + "" +
                " _id LONG PRIMARY KEY AUTOINCREMENT, "+
                "name TEXT NOT NULL, "+
                "city TEXT NOT NULL, "+
                "zip TEXT NOT NULL, "+
                ") ");

        db.execSQL(" CREATE TABLE subscriptions (" + "" +
                " _id LONG PRIMARY KEY AUTOINCREMENT, "+
                "firstname TEXT NOT NULL, "+
                "lastname TEXT NOT NULL, "+
                "email TEXT NOT NULL, "+
                "street TEXT, "+
                "streetnmbr TEXT, "+
                "zip TEXT, "+
                "city TEXT, "+
                "interests HASHMAP NOT NULL, "+
                "timestamp DATE NOT NULL, "+
                "isnew BOOLEAN NOT NULL, "+
                "teacher INTEGER, "+
                "FOREIGN KEY(teacher) REFERENCES teachers(_id), " +
                "event INTEGER, "+
                "FOREIGN KEY(event) REFERENCES events(_id), " +
                "school INTEGER "+
                "FOREIGN KEY(school) REFERENCES schools(_id), " +
                ") ");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
