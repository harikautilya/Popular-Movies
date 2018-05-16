package com.clapoof.clapoof.storage;

import com.clapoof.clapoof.Base.DBHelper;
import com.clapoof.clapoof.storage.match.MatchRepo;
import com.clapoof.clapoof.storage.match.cloud.CloudMatchBaseData;
import com.clapoof.clapoof.storage.match.local.LocalMatchBaseData;

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
