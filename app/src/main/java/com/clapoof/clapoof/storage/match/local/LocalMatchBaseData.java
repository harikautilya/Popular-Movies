package com.clapoof.clapoof.storage.match.local;

import com.clapoof.clapoof.storage.match.MatchData;

public class LocalMatchBaseData implements MatchData {


    private static LocalMatchBaseData INSTANCE;

    public static LocalMatchBaseData get() {
        if (INSTANCE == null) {
            synchronized (LocalMatchBaseData.class) {
                if (INSTANCE == null)
                    return INSTANCE = new LocalMatchBaseData();
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
