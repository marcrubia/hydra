package com.hydra.runnables.ui;

import com.hydra.runnables.HydraRunnable;


public abstract class HydraUIRunnable extends HydraRunnable {

    private HydraUIHandler handler;

    /**
     * Creates a new HydraUIRunnable instance with the next available id
     */
    public HydraUIRunnable() {
        super();
    }

    /**
     * Creates a new HydraUIRunnable instance with the next available id
     *
     * @param handler the handler that will be used for the callbacks
     */
    public HydraUIRunnable(HydraUIHandler handler) {
        super();
        this.handler = handler;
    }

    /**
     * Creates a new HydraUIRunnable instance with the next available id
     *
     * @param message the runnable identification message
     */
    public HydraUIRunnable(String message) {
        super(message);
    }

    /**
     * @param message the runnable identification message
     * @param handler the handler that will be used for the callbacks
     */
    public HydraUIRunnable(String message, HydraUIHandler handler) {
        super(message);
        this.handler = handler;
    }


    @Override
    public void run() {
        try {
            beforeCallback();
            if (action()) {
                finishCallback();
                if (handler != null) handler.sendEmptyMessage(HydraUIHandler.SUCCESS);
            }
        } catch (Exception e) {
            errorCallback(e);
            if (handler != null) handler.sendEmptyMessage(HydraUIHandler.ERROR);
        }
    }

}
