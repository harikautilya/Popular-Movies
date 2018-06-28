package com.movies.book.storage;

import com.movies.book.Base.DBHelper;
import com.movies.book.storage.match.MatchRepo;
import com.movies.book.storage.match.cloud.CloudMatchBaseData;
import com.movies.book.storage.match.local.LocalMatchBaseData;

import javax.inject.Inject;

public class BaseDataPackage implements BaseDataRepo {

    private MatchRepo matchRepo;
    private DBHelper dbHelper;

    @Inject
    public BaseDataPackage(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    @Override
    public MatchRepo provideMatchRepo() {
        if (matchRepo == null)
            return matchRepo = new MatchRepo(LocalMatchBaseData.get(dbHelper), CloudMatchBaseData.get());
        else
            return matchRepo;
    }
}
