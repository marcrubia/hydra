package com.hydra;

import java.util.concurrent.atomic.AtomicInteger;

import android.util.Log;

public enum Hydra {
	
	INSTANCE;
	
	private final static String TAG = "HYDRA";
	private HydraExecutor hydraExecutor;
	
	private AtomicInteger threadSequenceGenerator;
	
	Hydra() {
		hydraExecutor = new HydraExecutor();
		threadSequenceGenerator = new AtomicInteger();
		log("HYDRA CREATED");
	}

    /**
     *
     * @return the current executor used by the enum instance
     */
	public HydraExecutor getHydraExecutor() {
		return hydraExecutor;
	}

    /**
     *
     * @return the next atomic integer to be used as thread id
     */
	public int nextThread() {
		return threadSequenceGenerator.incrementAndGet();
	}

    public void log(Object obj) {
        Log.d(TAG, obj.toString());
    }

}
