package com.ynov.android;

import org.greenrobot.eventbus.Subscribe;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.widget.CheckBox;
import com.ynov.android.events.NetworkStateChanged;
import com.ynov.android.tools.NetworkTool;

public class NetworkStatusActivity extends MyActivity {

	CheckBox cbConnected, cbBroadband;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_status);
		
		cbConnected = (CheckBox) findViewById(R.id.activity_network_checkBox_isconnected);
		cbBroadband = (CheckBox) findViewById(R.id.activity_network_checkBox_isbroadband);
		
		ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
		
        cbConnected.setChecked(NetworkTool.isConnected(activeNetwork));
    	cbBroadband.setChecked(NetworkTool.isBroadband(activeNetwork));
	}
	
	// method that will be called when someone posts an event NetworkStateChanged
	@Subscribe
    public void onNetworkEvent(NetworkStateChanged event) {
    	cbConnected.setChecked(event.isInternetConnected());
    	cbBroadband.setChecked(event.ismIsBroadband());
    }
}

