package com.movies.book.storage.movieList.local;

import android.database.Cursor;
import android.provider.BaseColumns;
import android.text.TextUtils;

import com.movies.book.Base.DBHelper;
import com.movies.book.api.response.MoviesResponse;
import com.movies.book.storage.movieList.MovieData;

import java.util.ArrayList;
import java.util.List;

import static com.movies.book.storage.movieList.local.LocalMovieDataBase.MovieTable.MOVIE_ADULT;

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
        while (movieCursor.moveToNext()) {

            MoviesResponse.MovieEntity movieEntity = new MoviesResponse.MovieEntity();

            movieEntity.setReleaseDate((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_RELEASE_DATE))));
            movieEntity.setOverview((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_OVER_VIEW))));
            movieEntity.setAdult(Boolean.parseBoolean(movieCursor.getString(movieCursor.getColumnIndex(MOVIE_ADULT))));
            movieEntity.setBackdropPath((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_BACKDROP))));
            List<Integer> genersId = new ArrayList<>();
            for (String text : TextUtils.split(movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_GENRE_IDS)), ",")) {
                genersId.add(Integer.parseInt(text));
            }
            movieEntity.setGenreIds(genersId);
            movieEntity.setOriginalTitle((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_ORIGIN_TITLE))));
            movieEntity.setOriginalLanguage((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_ORIGINAL_LANGUAGE))));
            movieEntity.setPosterPath((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_POSTER_PATH))));
            movieEntity.setPopularity((movieCursor.getDouble(movieCursor.getColumnIndex(MovieTable.MOVIE_POPULARITY))));
            movieEntity.setTitle((movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_TITLE))));
            movieEntity.setVoteAverage((movieCursor.getDouble(movieCursor.getColumnIndex(MovieTable.MOVIE_VOTE_AVERAGE))));
            movieEntity.setVideo(Boolean.parseBoolean(movieCursor.getString(movieCursor.getColumnIndex(MovieTable.MOVIE_VIDEO))));
            movieEntity.setId((movieCursor.getInt(movieCursor.getColumnIndex(MovieTable.MOVIE_ID))));
            movieEntity.setVoteCount((movieCursor.getInt(movieCursor.getColumnIndex(MovieTable.MOVIE_VOTE_COUNT))));
            data.add(movieEntity);
        }
        return data;
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
