package com.kotkovets.fines.services.network;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by igork on 5/23/16.
 */
public class RequestRunnableExecutor {
    private static final int NTHREDS = 10;
    private ExecutorService executor;

    public RequestRunnableExecutor() {
        executor = Executors.newFixedThreadPool(NTHREDS);
    }

    public void add(FineRequestRunnable runnable) {
        executor.execute(runnable);
    }

}
