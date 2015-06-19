package be.ehb.dtsid_inapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * @author Dries, Tom, Kristof
 * @version 1.0
 */

public class DatabaseManager
{
    private int mOpenCounter;

    private static DatabaseManager instance;
    private static MySQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public static synchronized void initializeInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DatabaseManager();
            dbHelper = new MySQLiteHelper(context);

            instance.mOpenCounter = 0;

            Log.d("DatabaseInstance","Database Initialized");
        }
    }

    public static synchronized DatabaseManager getInstance()
    {
        if(instance == null)
            throw new IllegalStateException(DatabaseManager.class.getSimpleName() + " is not yet initialized, call initializeInstance() first!");

        return instance;
    }

    public synchronized SQLiteDatabase openDatabase()
    {
        mOpenCounter++;
        if(mOpenCounter == 1)
        {
            db = dbHelper.getWritableDatabase();

            Log.d("DatabaseInstance","Database Opened");
        }
        return db;
    }

    public synchronized void closeDatabase()
    {
        mOpenCounter--;
        if(mOpenCounter == 0)
        {
            dbHelper.close();
            db.close();

            Log.d("DatabaseInstance", "Database Closed");
        }
    }
}