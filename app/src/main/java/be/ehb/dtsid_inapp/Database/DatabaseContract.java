package be.ehb.dtsid_inapp.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import be.ehb.dtsid_inapp.Models.MySQLiteHelper;
import be.ehb.dtsid_inapp.Models.Subscription;

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


}
