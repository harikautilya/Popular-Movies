package com.clapoof.clapoof.storage;

import com.clapoof.clapoof.storage.match.MatchRepo;

public interface BaseDataRepo {

    MatchRepo provideMatchRepo();
}
