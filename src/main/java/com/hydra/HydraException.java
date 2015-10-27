package com.hydra;

public class HydraException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3210072404720659234L;
    private String reason;
    /**
     * Creates a new HydraException
     * @param reason the exception reason
     */
	public HydraException(String reason) {
		super(reason);
		this.reason = reason;
	}

    /**
     *
     * @return the exception reason
     */
	public String getReason() {
		return reason;
	}

	
	
}
