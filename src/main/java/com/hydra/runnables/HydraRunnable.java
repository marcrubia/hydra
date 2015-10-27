package com.hydra.runnables;

import com.hydra.Hydra;

public abstract class HydraRunnable implements Runnable {
	
	protected String message;
	protected int id;

    /**
     * Creates a new HydraRunnable instance with the next available id
     * @param message the runnable identification message
     */
	public HydraRunnable(String message) {
		this.message = message;
		this.id = Hydra.INSTANCE.nextThread();
	}

    /**
     * Creates a new HydraRunnable instance with the next available id and null message
     */
	public HydraRunnable() {
		this.id = Hydra.INSTANCE.nextThread();
	}
	
	
	@Override
	public void run() {
		try {
			beforeCallback();
			if (action()) finishCallback();
		} catch (Exception e) {
			errorCallback(e);
		}
	}

    /**
     * Default before method for HydraRunnable instances, just log the thread message and id. It is the first method called in the runnable body.
     */
	public void beforeCallback() {
		if (message != null) Hydra.INSTANCE.log("HDYRA STARTING MESSAGE: " + message + " ID: " + id);
		else Hydra.INSTANCE.log("HDYRA STARTING ID: " + id);
	}

    /**
     * Default after method for HydraRunnable instances, just log the thread message and id. It is the last method called in the runnable body and it's only called if the action method is true.
     */
	public void finishCallback() {
		if (message != null) Hydra.INSTANCE.log("HDYRA SUCCESS MESSAGE: " + message + " ID: " + id);
		else Hydra.INSTANCE.log("HDYRA THREAD SUCCESS ID: " + id);
	}

    /**
     * Default callback that occurs when one of the runnable body calls (before, action, finish) crashes. Logs the thread message, id and the exception message.
     * @param e
     */
	public void errorCallback(Exception e) {
		if (message != null) Hydra.INSTANCE.log("HDYRA ERROR MESSAGE: " + message + " ID: " + id + " EXCEPTION " +e);
		else Hydra.INSTANCE.log("HDYRA THREAD ERROR ID: " + id + " EXCEPTION " +e);
	}

    /**
     *
     * @return whether the finishCallback method should be executed or not
     */
	public abstract boolean action();
	
}
