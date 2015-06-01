package be.ehb.dtsid_inapp.Models;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import java.util.List;

public class DataDAO
{
    //Variables
    private MySQLiteHelper dbHelper;
    private SQLiteDatabase db;
    private EventList events;
    private SchoolList schools;
    private SubscriptionList subscriptions;
    private TeacherList teachers;

    //Constructor
    public DataDAO(Context context)
    {
        this.dbHelper = new MySQLiteHelper(context);
        this.db = dbHelper.getWritableDatabase();

        events = new EventList();
        schools = new SchoolList();
        subscriptions = new SubscriptionList();
        teachers = new TeacherList();
    }

    //Methods
    public void close()
    {
        //Send local database to online database!!!!!!!!!!!
        dbHelper.close();
        db.close();
        Log.d("Close", "Database en helper closed");
    }

    public void addSubscription()
    {

    }

    //Getters and setters
    public List<Event> getAllEvents()
    {
        return events.getEvents();
    }
    public List<School> getAllSchools()
    {
        return schools.getSchools();
    }
    public List<Subscription> getAllSubscriptions()
    {
        return subscriptions.getSubscriptions();
    }
    public List<Teacher> getAllTeachers()
    {
        return teachers.getTeachers();
    }
    public void setEvents(List<Event> events)
    {
        this.events.setEvents(events);
    }
    public void setSchools(List<School> schools)
    {
        this.schools.setSchools(schools);
    }
    public void setSubscriptions(List<Subscription> subscriptions)
    {
        this.subscriptions.setSubscriptions(subscriptions);
    }
    public void setTeachers(List<Teacher> teachers)
    {
        this.teachers.setTeachers(teachers);
    }
}