package com.movies.book.storage.match.cloud;

import com.movies.book.storage.match.MatchData;

public class CloudMatchBaseData implements MatchData {

    private static CloudMatchBaseData INSTANCE;

    public static CloudMatchBaseData get() {
        if (INSTANCE == null) {
            synchronized (CloudMatchBaseData.class) {
                if (INSTANCE == null)
                    return INSTANCE = new CloudMatchBaseData();
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
