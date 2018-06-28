package com.movies.book.Base;

import android.content.Context;

import javax.inject.Inject;
import javax.inject.Singleton;

import com.movies.book.Base.annotations.ApplicationContext;

/**
 * Created by kautilya on 31/01/18.
 */
@Singleton
public class DataManager {

    private Context mContext;
    private DBHelper database;
    private SharedPrefs mSharedPrefs;

    @Inject
    public DataManager(@ApplicationContext Context mContext, DBHelper database, SharedPrefs mSharedPrefs) {
        this.mContext = mContext;
        this.database = database;
        this.mSharedPrefs = mSharedPrefs;
    }

    public Context getmContext() {
        return mContext;
    }

    public DBHelper getDatabase() {
        return database;
    }

    public SharedPrefs getSharedPrefs() {
        return mSharedPrefs;
    }
}
