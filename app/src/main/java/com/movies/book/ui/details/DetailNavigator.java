package com.movies.book.ui.details;

import com.movies.book.Base.Classes.BaseNavigator;
import com.movies.book.api.response.MovieDetailResponse;

public interface DetailNavigator extends BaseNavigator {
    void updateData(MovieDetailResponse body);
}
