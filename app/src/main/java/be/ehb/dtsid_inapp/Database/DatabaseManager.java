package be.ehb.dtsid_inapp.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseManager
{
    private static DatabaseManager instance;
    private static MySQLiteHelper dbHelper;
    private SQLiteDatabase db;

    public static synchronized void initializeInstance(Context context)
    {
        if(instance == null)
        {
            instance = new DatabaseManager();
            dbHelper = new MySQLiteHelper(context);
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
        db = dbHelper.getWritableDatabase();

        return db;
    }

    public synchronized void closeDatabase()
    {
        dbHelper.close();
        db.close();
    }
}