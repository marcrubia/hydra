package com.hydra.runnables.ui;

import android.os.Handler;
import android.os.Message;

public abstract class HydraUIHandler extends Handler {
	
	public static final int SUCCESS = 42;
	public static final int ERROR = 66;
	
	public abstract void onSuccess();
	
	public abstract void onError();
	
	@Override
	public void handleMessage(Message msg) {
		try {
			if (msg.what == SUCCESS) onSuccess();
			else if (msg.what == ERROR) onError();
		} catch (Exception e) {
		}
	}
	
}
