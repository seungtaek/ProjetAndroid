package com.ynov.android.tools;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class NetworkTool {

	// NEED : <uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>
	public static boolean isNetworkAvailable(Context ctx){

		ConnectivityManager cm = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo ni = cm.getActiveNetworkInfo();
		return ni != null && ni.isConnectedOrConnecting();

	}

	public static boolean isConnected(NetworkInfo ni){
		return ni != null && ni.isConnectedOrConnecting();
		//ni!=null && ni.getState()==NetworkInfo.State.CONNECTED
	}

	public static boolean isBroadband(NetworkInfo ni){
		if(ni == null) return false;
		
		boolean isBroadband = false;
		switch (ni.getType()) {
		case ConnectivityManager.TYPE_BLUETOOTH:

			break;
		case ConnectivityManager.TYPE_ETHERNET:
			isBroadband = true;
			break;
		case ConnectivityManager.TYPE_MOBILE:

			break;
		case ConnectivityManager.TYPE_WIFI:
			isBroadband = true;
			break;
		case ConnectivityManager.TYPE_WIMAX:
			isBroadband = true;
			break;

		default:
			break;
		}

		return isBroadband;
	}
}
