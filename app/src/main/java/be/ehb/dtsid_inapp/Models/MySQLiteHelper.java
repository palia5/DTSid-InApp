package be.ehb.dtsid_inapp.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class MySQLiteHelper extends SQLiteOpenHelper
{
    //Constants
    public static final String TABLE_EVENTS = "events";
    public static final String COL_EVENTS_ID = "_id";
    public static final String COL_EVENTS_NAME = "name";
    public static final String COL_EVENTS_ACADYEAR = "acadyear";
    public static final String[] ALL_COLUMNS_EVENTS = {COL_EVENTS_ID, COL_EVENTS_NAME, COL_EVENTS_ACADYEAR};

    public static final String TABLE_SCHOOLS = "schools";
    public static final String COL_SCHOOLS_ID = "_id";
    public static final String COL_SCHOOLS_NAME = "name";
    public static final String COL_SCHOOLS_ZIP = "zip";
    public static final String COL_SCHOOLS_CITY = "city";
    public static final String[] ALL_COLUMNS_SCHOOLS = {COL_SCHOOLS_ID, COL_SCHOOLS_NAME, COL_SCHOOLS_ZIP, COL_SCHOOLS_CITY};

    public static final String TABLE_SUBSCRIPTIONS = "subscriptions";
    public static final String COL_SUBSCRIPTIONS_ID = "_id";
    public static final String COL_SUBSCRIPTIONS_FIRSTNAME = "firstname";
    public static final String COL_SUBSCRIPTIONS_LASTNAME = "lastname";
    public static final String COL_SUBSCRIPTIONS_EMAIL = "email";
    public static final String COL_SUBSCRIPTIONS_STREET = "street";
    public static final String COL_SUBSCRIPTIONS_STREETNUMBER = "streetnumber";
    public static final String COL_SUBSCRIPTIONS_ZIP = "zip";
    public static final String COL_SUBSCRIPTIONS_CITY = "city";
    public static final String COL_SUBSCRIPTIONS_INTERESTS = "interests";
    public static final String COL_SUBSCRIPTIONS_TIMESTAMP = "timestamp";
    public static final String COL_SUBSCRIPTIONS_TEACHER = "teacher";
    public static final String COL_SUBSCRIPTIONS_EVENT = "event";
    public static final String COL_SUBSCRIPTIONS_ISNEW = "isnew";
    public static final String COL_SUBSCRIPTIONS_SCHOOL = "school";
    public static final String[] ALL_COLUMNS_SUBSCRIPTION = {COL_SUBSCRIPTIONS_ID, COL_SUBSCRIPTIONS_FIRSTNAME, COL_SUBSCRIPTIONS_LASTNAME, COL_SUBSCRIPTIONS_EMAIL, COL_SUBSCRIPTIONS_STREET, COL_SUBSCRIPTIONS_STREETNUMBER, COL_SUBSCRIPTIONS_ZIP, COL_SUBSCRIPTIONS_CITY, COL_SUBSCRIPTIONS_INTERESTS, COL_SUBSCRIPTIONS_TIMESTAMP, COL_SUBSCRIPTIONS_TEACHER, COL_SUBSCRIPTIONS_EVENT, COL_SUBSCRIPTIONS_ISNEW, COL_SUBSCRIPTIONS_SCHOOL};

    public static final String TABLE_TEACHERS = "teachers";
    public static final String COL_TEACHERS_ID = "_id";
    public static final String COL_TEACHERS_NAME = "name";
    public static final String COL_TEACHERS_ACADYEAR = "acadyear";
    public static final String[] ALL_COLUMNS_TEACHERS = {COL_TEACHERS_ID, COL_TEACHERS_NAME, COL_TEACHERS_ACADYEAR};

    public MySQLiteHelper(Context context)
    {
        super(context, "DATABASE.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "CREATE TABLE " + TABLE_EVENTS + " (" +
                COL_EVENTS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_EVENTS_NAME + " TEXT NOT NULL, " +
                COL_EVENTS_ACADYEAR + " TEXT NOT NULL" +
                ")");
        db.execSQL(
                "CREATE TABLE " + TABLE_SCHOOLS + " (" +
                COL_SCHOOLS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SCHOOLS_NAME + " TEXT NOT NULL, " +
                COL_SCHOOLS_ZIP + " TEXT NOT NULL, " +
                COL_SCHOOLS_CITY + " TEXT NOT NULL" +
                ")");
        db.execSQL(
                "CREATE TABLE " + TABLE_SUBSCRIPTIONS + " (" +
                COL_SUBSCRIPTIONS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_SUBSCRIPTIONS_FIRSTNAME + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_LASTNAME + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_EMAIL + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_STREET + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_STREETNUMBER + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_ZIP + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_CITY + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_INTERESTS + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_TIMESTAMP + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_TEACHER + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_EVENT + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_ISNEW + " TEXT NOT NULL, " +
                COL_SUBSCRIPTIONS_SCHOOL + " TEXT NOT NULL" +
                ")");
        db.execSQL(
                "CREATE TABLE " + TABLE_TEACHERS + " (" +
                COL_TEACHERS_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COL_TEACHERS_NAME + " TEXT NOT NULL, " +
                COL_TEACHERS_ACADYEAR + " TEXT NOT NULL" +
                ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Wanneer upgraden?
    }
}