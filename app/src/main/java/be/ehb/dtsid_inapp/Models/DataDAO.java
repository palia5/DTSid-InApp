package be.ehb.dtsid_inapp.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DataDAO
{
    private MySQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public DataDAO(Context context)
    {
        this.dbHelper = new MySQLiteHelper(context);
        this.db = dbHelper.getWritableDatabase();
    }

    public void close()
    {
        dbHelper.close();
        db.close();
        Log.d("Close", "Database en helper closed");
    }

    public void addStudent()
    {

    }
}