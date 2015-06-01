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

    public static final String TABLE_EVENTS = "events";
    public static final String COL_EVENTS_ID = "_id";
    public static final String COL_EVENTS_NAME = "name";
    public static final String COL_EVENTS_ACADYEAR = "acadyear";

    public static final String TABLE_SCHOOLS = "schools";
    public static final String COL_SCHOOLS_ID = "_id";
    public static final String COL_SCHOOLS_NAME = "name";
    public static final String COL_SCHOOLS_CITY = "city";
    public static final String COL_SCHOOLS_ZIP = "zip";

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

    public static final String TABLE_IMAGES = "events";
    public static final String COL_IMAGES_ID = "_id";
    public static final String COL_IMAGES_PRIORITY = "priority";
    public static final String COL_IMAGES_IMAGE = "image";


    public DatabaseContract(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
