package com.clapoof.clapoof.storage.match.cloud;

import com.clapoof.clapoof.storage.match.MatchData;

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
