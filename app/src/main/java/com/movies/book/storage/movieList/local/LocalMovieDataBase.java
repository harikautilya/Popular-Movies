package com.movies.book.storage.movieList.local;

import android.content.ContentValues;
import android.database.Cursor;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.movies.book.Base.DBHelper;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.storage.movieList.MovieData;

import java.util.ArrayList;
import java.util.List;

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

public class LocalMovieDataBase implements MovieData {

    private static LocalMovieDataBase INSTANCE;

    private final DBHelper dbHelper;

    public static LocalMovieDataBase get(DBHelper dbHelper) {
        if (INSTANCE == null) {
            synchronized (LocalMovieDataBase.class) {
                if (INSTANCE == null)
                    return INSTANCE = new LocalMovieDataBase(dbHelper);
            }
        }
        return INSTANCE;
    }

    public LocalMovieDataBase(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }


    @Override
    public List<MoviesResponse.MovieEntity> getMovieList() {
        Cursor movieCursor = dbHelper.getMovieList();
        movieCursor.moveToFirst();
        List<MoviesResponse.MovieEntity> data = new ArrayList<>();
        if (movieCursor.getCount() == 0) {
            movieCursor.close();
            return data;
        }
        do {
            data.add(parseCursor(movieCursor));
        } while (movieCursor.moveToNext());
        movieCursor.close();
        return data;
    }

    MoviesResponse.MovieEntity parseCursor(Cursor movieCursor) {
        MoviesResponse.MovieEntity movieEntity = new MoviesResponse.MovieEntity();

        movieEntity.setReleaseDate((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_RELEASE_DATE))));
        movieEntity.setOverview((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_OVER_VIEW))));
        movieEntity.setAdult(Boolean.parseBoolean(movieCursor.getString(movieCursor.getColumnIndex(MOVIE_ADULT))));
        movieEntity.setBackdropPath((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_BACKDROP))));
        List<Integer> genersId = new ArrayList<>();
        for (String text : TextUtils.split(movieCursor.getString(movieCursor.getColumnIndex(MOVIE_GENRE_IDS)), ",")) {
            genersId.add(Integer.parseInt(text));
        }
        movieEntity.setGenreIds(genersId);
        movieEntity.setOriginalTitle((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_ORIGIN_TITLE))));
        movieEntity.setOriginalLanguage((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_ORIGINAL_LANGUAGE))));
        movieEntity.setPosterPath((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_POSTER_PATH))));
        movieEntity.setPopularity((movieCursor.getDouble(movieCursor.getColumnIndex(MOVIE_POPULARITY))));
        movieEntity.setTitle((movieCursor.getString(movieCursor.getColumnIndex(MOVIE_TITLE))));
        movieEntity.setVoteAverage((movieCursor.getDouble(movieCursor.getColumnIndex(MOVIE_VOTE_AVERAGE))));
        movieEntity.setVideo(Boolean.parseBoolean(movieCursor.getString(movieCursor.getColumnIndex(MOVIE_VIDEO))));
        movieEntity.setId((movieCursor.getInt(movieCursor.getColumnIndex(MOVIE_ID))));
        movieEntity.setVoteCount((movieCursor.getInt(movieCursor.getColumnIndex(MOVIE_VOTE_COUNT))));
        return movieEntity;
    }

    @Override
    public long saveMovie(MoviesResponse.MovieEntity movieEntity) {


        ContentValues contentValues = new ContentValues();
        contentValues.put(MOVIE_RELEASE_DATE, movieEntity.getReleaseDate());
        contentValues.put(MOVIE_OVER_VIEW, movieEntity.getOverview());
        contentValues.put(MOVIE_ADULT, movieEntity.getAdult() + "");
        contentValues.put(MOVIE_BACKDROP, movieEntity.getBackdropPath());
        contentValues.put(MOVIE_GENRE_IDS, TextUtils.join(",", movieEntity.getGenreIds()));
        contentValues.put(MOVIE_ORIGIN_TITLE, movieEntity.getOriginalTitle());
        contentValues.put(MOVIE_ORIGINAL_LANGUAGE, movieEntity.getOriginalLanguage());
        contentValues.put(MOVIE_POSTER_PATH, movieEntity.getPosterPath());
        contentValues.put(MOVIE_POPULARITY, movieEntity.getPopularity());
        contentValues.put(MOVIE_TITLE, movieEntity.getTitle());
        contentValues.put(MOVIE_VOTE_AVERAGE, movieEntity.getVoteAverage());
        contentValues.put(MOVIE_VIDEO, movieEntity.getVideo());
        contentValues.put(MOVIE_ID, movieEntity.getId());
        contentValues.put(MOVIE_VOTE_COUNT, movieEntity.getVoteCount());
        return dbHelper.saveMovie(contentValues);
    }

    @Override
    public int deleteMovie(long id) {
        return dbHelper.deleteMovie(id);
    }

    @Override
    public void toggle(MoviesResponse.MovieEntity movieEntity) {
        if (!checkMovie(movieEntity.getId())) {
            saveMovie(movieEntity);
        } else {
            deleteMovie(movieEntity.getId());
        }
    }

    @Override
    public boolean checkMovie(int id) {
        Cursor cursor;
        (cursor = dbHelper.getMovie(id)).moveToFirst();
        if (cursor.getCount() == 0) {
            cursor.close();
            return false;
        } else {
            cursor.close();
            return true;
        }

    }


    public interface MovieTable extends BaseColumns {
        String TABLE_NAME = "movie";
        String MOVIE_RELEASE_DATE = "release_date";
        String MOVIE_OVER_VIEW = "overview";
        String MOVIE_ADULT = "adult";
        String MOVIE_BACKDROP = "backdrop_path";
        String MOVIE_GENRE_IDS = "genre_ids";
        String MOVIE_ORIGIN_TITLE = "original_title";
        String MOVIE_ORIGINAL_LANGUAGE = "original_language";
        String MOVIE_POSTER_PATH = "poster_path";
        String MOVIE_POPULARITY = "popularity";
        String MOVIE_TITLE = "title";
        String MOVIE_VOTE_AVERAGE = "vote_average";
        String MOVIE_VIDEO = "video";
        String MOVIE_ID = "id";
        String MOVIE_VOTE_COUNT = "vote_count";

    }
}
