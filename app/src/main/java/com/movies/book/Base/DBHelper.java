package com.movies.book.Base;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.movies.book.Base.annotations.ApplicationContext;
import com.movies.book.Base.annotations.DatabaseInfo;
import com.movies.book.utils.TableUtils;

import java.util.Arrays;
import java.util.Collections;

import javax.inject.Inject;
import javax.inject.Singleton;

import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_ADULT;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_BACKDROP;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_GENRE_IDS;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_ID;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_ORIGINAL_LANGUAGE;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_ORIGIN_TITLE;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_OVER_VIEW;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_POPULARITY;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_POSTER_PATH;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_RELEASE_DATE;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_TITLE;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_VIDEO;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_VOTE_AVERAGE;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_VOTE_COUNT;
import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.TABLE_NAME;


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
            context.openOrCreateDatabase(DATABASE_NAME, Context.MODE_PRIVATE, null);
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
            db.execSQL(TableUtils.createTable(TABLE_NAME,
                    Arrays.asList(
                            MOVIE_RELEASE_DATE,
                            MOVIE_OVER_VIEW,
                            MOVIE_ADULT,
                            MOVIE_BACKDROP,
                            MOVIE_GENRE_IDS,
                            MOVIE_ORIGIN_TITLE,
                            MOVIE_ORIGINAL_LANGUAGE,
                            MOVIE_POSTER_PATH,
                            MOVIE_TITLE,
                            MOVIE_VIDEO
                    ), Arrays.asList(MOVIE_POPULARITY,
                            MOVIE_VOTE_AVERAGE,
                            MOVIE_VOTE_COUNT), MOVIE_ID));
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

    public Cursor getMovieList() {
        return getReadableDatabase().query(TABLE_NAME, null, null, null, null, null, null);

    }

    public long saveMovie(ContentValues contentValues) {
        return getWritableDatabase().insert(TABLE_NAME, null, contentValues);
    }

    public int deleteMovie(long id) {
        return getWritableDatabase().delete(TABLE_NAME, MOVIE_ID + "=?", Collections.singletonList(id + "").toArray(new String[1]));
    }
}
