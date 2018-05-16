package com.clapoof.clapoof.storage.match;

import com.clapoof.clapoof.storage.BaseDataPackage;

public class MatchRepo extends BaseDataPackage  implements MatchData{

    private MatchData remoteData;
    private MatchData cloudData;

    public MatchRepo(MatchData remoteData, MatchData cloudData) {
        this.remoteData = remoteData;
        this.cloudData = cloudData;
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
