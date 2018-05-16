package com.clapoof.clapoof.storage.match.local;

import com.clapoof.clapoof.Base.DBHelper;
import com.clapoof.clapoof.storage.match.MatchData;

public class LocalMatchBaseData implements MatchData {


    private static LocalMatchBaseData INSTANCE;
    private final DBHelper dbHelper;

    public LocalMatchBaseData(DBHelper dbHelper) {
        this.dbHelper = dbHelper;
    }

    public static LocalMatchBaseData get(DBHelper dbHelper) {
        if (INSTANCE == null) {
            synchronized (LocalMatchBaseData.class) {
                if (INSTANCE == null)
                    return INSTANCE = new LocalMatchBaseData(dbHelper);
            }
        }
        return INSTANCE;
    }

    @Override
    public void getMatchData() {

    }

    @Override
    public void createTable() {

    }

    @Override
    public void destroyTable() {

    }
}
