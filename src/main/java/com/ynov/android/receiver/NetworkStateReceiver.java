package com.ynov.android.receiver;

import org.greenrobot.eventbus.EventBus;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.ynov.android.BuildConfig;
import com.ynov.android.events.NetworkStateChanged;
import com.ynov.android.tools.NetworkTool;

public class NetworkStateReceiver extends BroadcastReceiver {

	private final static String TAG = "NetworkStateReceiver";

	// post event if there is no Internet connection
	@Override
	public void onReceive(Context context, Intent intent) {

		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onReceive " + intent);
		}
		if(intent.getExtras()!=null) {
			NetworkInfo ni=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_NETWORK_INFO);

			if (BuildConfig.DEBUG) {
				Log.d(TAG, "onReceive DetailedState = " + ni.getDetailedState().name());
				Log.d(TAG, "onReceive State = " + ni.getState().name());
				Log.d(TAG, "onReceive ExtraInfo = " + ni.getExtraInfo());
				Log.d(TAG, "onReceive Reason = " + ni.getReason());
				Log.d(TAG, "onReceive isAvailable = " + Boolean.toString(ni.isAvailable()));
				Log.d(TAG, "onReceive isConnected = " + Boolean.toString(ni.isConnected()));
				Log.d(TAG, "onReceive isConnectedOrConnecting = " + Boolean.toString(ni.isConnectedOrConnecting()));
				Log.d(TAG, "onReceive isFailover = " + Boolean.toString(ni.isFailover()));
				Log.d(TAG, "onReceive isRoaming = " + Boolean.toString(ni.isRoaming()));
				Log.d(TAG, "onReceive describeContents = " + ni.describeContents());
				Log.d(TAG, "onReceive ExtraInfo = " + ni.getExtraInfo());
				Log.d(TAG, "onReceive Subtype = " + ni.getSubtype());
				Log.d(TAG, "onReceive SubtypeName = " + ni.getSubtypeName());
				Log.d(TAG, "onReceive Type = " + ni.getType());
				Log.d(TAG, "onReceive TypeName = " + ni.getTypeName());

				Log.d(TAG, "onReceive EXTRA_EXTRA_INFO = " + intent.getStringExtra(ConnectivityManager.EXTRA_EXTRA_INFO));
				Log.d(TAG, "onReceive EXTRA_IS_FAILOVER = " + Boolean.toString(intent.getBooleanExtra(ConnectivityManager.EXTRA_IS_FAILOVER,Boolean.FALSE)));
				Log.d(TAG, "onReceive EXTRA_NETWORK_TYPE = " + intent.getIntExtra(ConnectivityManager.EXTRA_NETWORK_TYPE,-1));
				Log.d(TAG, "onReceive EXTRA_NO_CONNECTIVITY = " + Boolean.toString(intent.getBooleanExtra(ConnectivityManager.EXTRA_NO_CONNECTIVITY,Boolean.FALSE)));
				NetworkInfo ni2=(NetworkInfo) intent.getExtras().get(ConnectivityManager.EXTRA_OTHER_NETWORK_INFO);
				if(ni2 != null) {
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO DetailedState = " + ni2.getDetailedState().name());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO State = " + ni2.getState().name());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO ExtraInfo = " + ni2.getExtraInfo());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO Reason = " + ni2.getReason());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO isAvailable = " + Boolean.toString(ni2.isAvailable()));
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO isConnected = " + Boolean.toString(ni2.isConnected()));
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO isConnectedOrConnecting = " + Boolean.toString(ni2.isConnectedOrConnecting()));
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO isFailover = " + Boolean.toString(ni2.isFailover()));
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO isRoaming = " + Boolean.toString(ni2.isRoaming()));
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO describeContents = " + ni2.describeContents());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO ExtraInfo = " + ni2.getExtraInfo());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO Subtype = " + ni2.getSubtype());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO SubtypeName = " + ni2.getSubtypeName());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO Type = " + ni2.getType());
					Log.d(TAG, "onReceive EXTRA_OTHER_NETWORK_INFO TypeName = " + ni2.getTypeName());
				}
				Log.d(TAG, "onReceive EXTRA_REASON = " + intent.getStringExtra(ConnectivityManager.EXTRA_REASON));
			}


			if( NetworkTool.isConnected(ni)) {
				// there is Internet connection

				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onReceive CONNECTED");
					Log.d(TAG, "onReceive BROADBAND " + (NetworkTool.isBroadband(ni) ? "TRUE":"FALSE"));
				}

				EventBus.getDefault().post(new NetworkStateChanged(true,NetworkTool.isBroadband(ni)));

			} else {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onReceive NOT CONNECTED");
				}
				// no Internet connection, send network state changed
				EventBus.getDefault().post(new NetworkStateChanged(false, false));
			}
		}
	}
}
