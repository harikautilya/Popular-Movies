package com.movies.book.ui.main.fragment.top;

import com.movies.book.Base.Classes.BaseNavigator;
import com.movies.book.api.response.MoviesResponse;

import java.util.List;

public interface TopNavigator extends BaseNavigator {
    void populateList(List<MoviesResponse.MovieEntity> results);
}
