package com.clapoof.clapoof.Base.rx;

import io.reactivex.Scheduler;

/**
 * Created by kautilya on 01/02/18.
 */

public interface SchedulerProvider {

    Scheduler ui();

    Scheduler computation();

    Scheduler io();
}
