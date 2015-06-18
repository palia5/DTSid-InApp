package be.ehb.dtsid_inapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 *
 * @author Dries, Kristof
 * @version 1.0
 *
 *
 */

public class MySQLiteHelper extends SQLiteOpenHelper
{
    //Constants
    public static final String TABLE_TEACHERS = "teachers";
    public static final String COL_TEACHERS_ID = "_id";
    public static final String COL_TEACHERS_NAME = "name";
    public static final String COL_TEACHERS_ACADYEAR = "acadyear";

    public static final String[] ALL_COLUMNS_TEACHERS = {COL_TEACHERS_ID,
            COL_TEACHERS_NAME,
            COL_TEACHERS_ACADYEAR};

    public static final String TABLE_EVENTS = "events";
    public static final String COL_EVENTS_ID = "_id";
    public static final String COL_EVENTS_NAME = "name";
    public static final String COL_EVENTS_ACADYEAR = "acadyear";

    public static final String[] ALL_COLUMNS_EVENTS = {COL_EVENTS_ID,
            COL_EVENTS_NAME,
            COL_EVENTS_ACADYEAR};

    public static final String TABLE_SCHOOLS = "schools";
    public static final String COL_SCHOOLS_ID = "_id";
    public static final String COL_SCHOOLS_NAME = "name";
    public static final String COL_SCHOOLS_ZIP = "zip";
    public static final String COL_SCHOOLS_CITY = "city";

    public static final String[] ALL_COLUMNS_SCHOOLS = {COL_SCHOOLS_ID,
            COL_SCHOOLS_NAME,
            COL_SCHOOLS_ZIP,
            COL_SCHOOLS_CITY};

    public static final String TABLE_SUBSCRIPTIONS = "subscriptions";
    public static final String COL_SUBSCRIPTIONS_ID = "_id";
    public static final String COL_SUBSCRIPTIONS_FIRSTNAME = "firstname";
    public static final String COL_SUBSCRIPTIONS_LASTNAME = "lastname";
    public static final String COL_SUBSCRIPTIONS_EMAIL = "email";
    public static final String COL_SUBSCRIPTIONS_STREET = "street";
    public static final String COL_SUBSCRIPTIONS_STREETNUMBER = "streetnumber";
    public static final String COL_SUBSCRIPTIONS_ZIP = "zip";
    public static final String COL_SUBSCRIPTIONS_CITY = "city";
    public static final String COL_SUBSCRIPTIONS_DIGX = "digx";
    public static final String COL_SUBSCRIPTIONS_WERKSTUDENT = "werkstudent";
    public static final String COL_SUBSCRIPTIONS_MULTEC = "multec";
    public static final String COL_SUBSCRIPTIONS_TIMESTAMP = "timestamp";
    public static final String COL_SUBSCRIPTIONS_TEACHER = "teacher";
    public static final String COL_SUBSCRIPTIONS_EVENT = "event";
    public static final String COL_SUBSCRIPTIONS_ISNEW = "isnew";
    public static final String COL_SUBSCRIPTIONS_SCHOOL = "school";

    public static final String[] ALL_COLUMNS_SUBSCRIPTION = {COL_SUBSCRIPTIONS_ID,
            COL_SUBSCRIPTIONS_FIRSTNAME,
            COL_SUBSCRIPTIONS_LASTNAME,
            COL_SUBSCRIPTIONS_EMAIL,
            COL_SUBSCRIPTIONS_STREET,
            COL_SUBSCRIPTIONS_STREETNUMBER,
            COL_SUBSCRIPTIONS_ZIP,
            COL_SUBSCRIPTIONS_CITY,
            COL_SUBSCRIPTIONS_DIGX,
            COL_SUBSCRIPTIONS_WERKSTUDENT,
            COL_SUBSCRIPTIONS_MULTEC,
            COL_SUBSCRIPTIONS_TIMESTAMP,
            COL_SUBSCRIPTIONS_TEACHER,
            COL_SUBSCRIPTIONS_EVENT,
            COL_SUBSCRIPTIONS_ISNEW,
            COL_SUBSCRIPTIONS_SCHOOL};

    public static final String TABLE_IMAGES = "images";
    public static final String COL_IMAGES_ID = "_id";
    public static final String COL_IMAGES_PRIORITY = "priority";
    public static final String COL_IMAGES_IMAGE = "image";

    public static final String[] ALL_COLUMNS_IMAGES = {COL_IMAGES_ID,
            COL_IMAGES_PRIORITY,
            COL_IMAGES_IMAGE};

    public static final String TABLE_GEMEENTES = "gemeentes";
    public static final String COL_GEMEENTES_ID = "_id";
    public static final String COL_GEMEENTES_POSTCODE = "postcode";
    public static final String COL_GEMEENTES_GEMEENTE = "gemeente";
    public static final String COL_GEMEENTES_PROVINCIE = "provincie";

    public static final String[] ALL_COLUMNS_GEMEENTES = {COL_GEMEENTES_ID,
            COL_GEMEENTES_POSTCODE,
            COL_GEMEENTES_GEMEENTE,
            COL_GEMEENTES_PROVINCIE};

    public MySQLiteHelper(Context context)
    {
        super(context, "DATABASE.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL(
                "CREATE TABLE " + TABLE_TEACHERS + " (" +
                        COL_TEACHERS_ID + " LONG PRIMARY KEY, " +
                        COL_TEACHERS_NAME + " TEXT NOT NULL, " +
                        COL_TEACHERS_ACADYEAR + " TEXT NOT NULL" +
                        ")");

        db.execSQL(
                "CREATE TABLE " + TABLE_EVENTS + " (" +
                        COL_EVENTS_ID + " LONG PRIMARY KEY, " +
                        COL_EVENTS_NAME + " TEXT NOT NULL, " +
                        COL_EVENTS_ACADYEAR + " TEXT NOT NULL" +
                        ")");

        db.execSQL(
                "CREATE TABLE " + TABLE_SCHOOLS + " (" +
                        COL_SCHOOLS_ID + " LONG PRIMARY KEY, " +
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
                        COL_SUBSCRIPTIONS_STREET + " TEXT, " +
                        COL_SUBSCRIPTIONS_STREETNUMBER + " TEXT, " +
                        COL_SUBSCRIPTIONS_ZIP + " TEXT, " +
                        COL_SUBSCRIPTIONS_CITY + " TEXT, " +
                        COL_SUBSCRIPTIONS_DIGX + " BOOLEAN, " +
                        COL_SUBSCRIPTIONS_MULTEC + " BOOLEAN, " +
                        COL_SUBSCRIPTIONS_WERKSTUDENT + " BOOLEAN, " +
                        COL_SUBSCRIPTIONS_TIMESTAMP + " LONG NOT NULL, " +
                        COL_SUBSCRIPTIONS_ISNEW + " BOOLEAN, " +
                        COL_SUBSCRIPTIONS_TEACHER + " LONG NOT NULL, " +
                        COL_SUBSCRIPTIONS_EVENT + " LONG NOT NULL, " +
                        COL_SUBSCRIPTIONS_SCHOOL + " LONG, " +
                        "FOREIGN KEY (" + COL_SUBSCRIPTIONS_TEACHER + ") REFERENCES " + TABLE_TEACHERS + "(_id), " +
                        "FOREIGN KEY (" + COL_SUBSCRIPTIONS_EVENT + ") REFERENCES " + TABLE_EVENTS + "(_id), " +
                        "FOREIGN KEY (" + COL_SUBSCRIPTIONS_SCHOOL + ") REFERENCES " + TABLE_SCHOOLS + "(_id) " +
                        ")");

        db.execSQL(
                "CREATE TABLE " + TABLE_IMAGES + " (" +
                        COL_IMAGES_ID + " LONG PRIMARY KEY, " +
                        COL_IMAGES_PRIORITY + " INTEGER NOT NULL, " +
                        COL_IMAGES_IMAGE + " STRING NOT NULL" +
                        ")");

        db.execSQL(
                "CREATE TABLE " + TABLE_GEMEENTES + " (" +
                        COL_GEMEENTES_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        COL_GEMEENTES_POSTCODE + " STRING NOT NULL, " +
                        COL_GEMEENTES_GEMEENTE + " STRING NOT NULL, " +
                        COL_GEMEENTES_PROVINCIE + " STRING NOT NULL" +
                        ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        //Wanneer upgraden?
    }
}