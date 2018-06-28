package com.movies.book.storage;

import com.movies.book.storage.match.MatchRepo;

public interface BaseDataRepo {

    MatchRepo provideMatchRepo();
}
