package com.movies.book.Base;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.movies.book.Base.annotations.ApplicationContext;
import com.movies.book.Base.annotations.DatabaseInfo;


@Singleton
public class DBHelper extends SQLiteOpenHelper {
    private static DBHelper instance = null;

    private static final String LOG_TAG = DBHelper.class.getName();
    private static final String DATABASE_NAME = "WardApp";
    private static final int DATABASE_VERSION = 1;

    @Inject
    public DBHelper(@ApplicationContext Context context,
                    @DatabaseInfo String dbName,
                    @DatabaseInfo Integer version) {
        super(context, dbName, null, version);
        if (!dataBaseExist(context)) {
            context.openOrCreateDatabase(DATABASE_NAME, context.MODE_PRIVATE, null);
        }
        instance = this;
    }

    public static synchronized DBHelper get() {
        return instance;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createAllTables(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        deleteAllTables(db);
        onCreate(db);
    }

    private static void deleteAllTables(SQLiteDatabase db) {

    }

    private static void createAllTables(SQLiteDatabase db) {

        db.beginTransaction();
        try {

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    public static void reCreateAllTables() {
        SQLiteDatabase db = DBHelper.get().getWritableDatabase();
        deleteAllTables(db);
        createAllTables(db);
    }

    public boolean dataBaseExist(Context context) {
        return context.getDatabasePath(DATABASE_NAME).exists();
    }

}
