package com.example.restoanchik;

import android.util.Log;
import android.app.Application;

import com.example.restoanchik.data.database.GetTableDatabase;
import com.example.restoanchik.data.repository.DatabaseRepository;

public class App extends Application{
    private static GetTableDatabase database;
    public static DatabaseRepository databaseRepository;

    @Override
    public void onCreate() {
        super.onCreate();
        database = GetTableDatabase.getInstance(getApplicationContext());

        if (database == null) {
            Log.e("App", "Database initialization failed.");
            return;
        }

        databaseRepository = new DatabaseRepository(database);

        if (databaseRepository == null) {
            Log.e("App", "DatabaseRepository initialization failed.");
            return;
        }

        Log.d("App", "Database and DatabaseRepository initialized successfully.");
    }

    public static GetTableDatabase getDatabase() {
        return database;
    }

    public static DatabaseRepository getDatabaseRepository() {
        return databaseRepository;
    }
}
