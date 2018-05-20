package com.ynov.android.events;

public class NetworkStateChanged {
	private boolean mIsInternetConnected;
	private boolean mIsBroadband;
	
    public NetworkStateChanged(boolean isInternetConnected, boolean isBroadband) {
        this.mIsInternetConnected = isInternetConnected;
        this.mIsBroadband = isBroadband;
    }

    public boolean isInternetConnected() {
        return this.mIsInternetConnected;
    }

	public boolean ismIsBroadband() {
		return mIsBroadband;
	}
}
