package com.ynov.android;

import org.greenrobot.eventbus.Subscribe;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import com.ynov.android.events.NetworkDummyEvent;
// Imports for the events
import com.ynov.android.events.NetworkPlayerEvent;
import com.ynov.android.events.NetworkGameEvent;
import com.ynov.android.events.NetworkMatchEvent;
import com.ynov.android.events.NetworkPlayersmatchEvent;

import com.ynov.android.service.LocalNetworkService;

public class NetworkCallActivity extends MyActivity {

	private final static String TAG = "NetworkCallActivity";

	private LocalNetworkService s;
	private TextView tvMessage;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_network_call);

		tvMessage = (TextView) findViewById(R.id.activity_network_call_textView_message);

		final Context context = getApplicationContext();
		final int duration =Toast.LENGTH_SHORT;

		((Button) findViewById(R.id.activity_network_call_button_call_dummy)).setOnClickListener( new OnClickListener() {

			@Override
			public void onClick(View v) {

				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onClick callDummyNetworkCall");
				}
				if (s != null) {
					s.callDummyNetworkCall();
					if (BuildConfig.DEBUG) {
						Log.d(TAG, "onClick called callDummyNetworkCall");


						CharSequence text = "You just called a dummy!";
						Toast toast = Toast.makeText(context, text, duration);
						toast.show();
					}
				}
			}
		});
		
   	 		((Button) findViewById(R.id.activity_network_call_button_call_player)).setOnClickListener( new OnClickListener() {
		
			@Override
			public void onClick(View v) {
		
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onClick callLoadPlayer");
				}
				if (s != null) {
					s.callLoadPlayer();
					if (BuildConfig.DEBUG) {
						Log.d(TAG, "onClick called callLoadPlayer");
					}
				}
			}
		});
		((Button) findViewById(R.id.activity_network_call_button_call_game)).setOnClickListener( new OnClickListener() {
		
			@Override
			public void onClick(View v) {
		
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onClick callLoadGame");
				}
				if (s != null) {
					s.callLoadGame();
					if (BuildConfig.DEBUG) {
						Log.d(TAG, "onClick called callLoadGame");
					}
				}
			}
		});
		((Button) findViewById(R.id.activity_network_call_button_call_match)).setOnClickListener( new OnClickListener() {
		
			@Override
			public void onClick(View v) {
		
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onClick callLoadMatch");
				}
				if (s != null) {
					s.callLoadMatch();
					if (BuildConfig.DEBUG) {
						Log.d(TAG, "onClick called callLoadMatch");
					}
				}
			}
		});
		((Button) findViewById(R.id.activity_network_call_button_call_playersmatch)).setOnClickListener( new OnClickListener() {
		
			@Override
			public void onClick(View v) {
		
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "onClick callLoadPlayersmatch");
				}
				if (s != null) {
					s.callLoadPlayersmatch();
					if (BuildConfig.DEBUG) {
						Log.d(TAG, "onClick called callLoadPlayersmatch");
					}
				}
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		
		Intent intent= new Intent(this, LocalNetworkService.class);
		bindService(intent, mConnection, Context.BIND_AUTO_CREATE);

		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onResume bindService");
		}
	}

	@Override
	protected void onPause() {
		unbindService(mConnection);

		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onPause unbindService");
		}
		super.onPause();
	}

	private ServiceConnection mConnection = new ServiceConnection() {

		public void onServiceConnected(ComponentName className, IBinder binder) {
			LocalNetworkService.MyBinder b = (LocalNetworkService.MyBinder) binder;
			s = b.getService();
			Toast.makeText(NetworkCallActivity.this, "Connected", Toast.LENGTH_SHORT).show();
		}

		public void onServiceDisconnected(ComponentName className) {
			s = null;
		}
	};

	// method that will be called when someone posts an event NetworkDummyEvent
	@Subscribe
	public void onNetworkEvent(NetworkDummyEvent event) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onNetworkEvent NetworkDummyEvent");
		}
		tvMessage.setText("NetworkDummyEvent CALLED");
	}
	
	// method that will be called when someone posts an event NetworkPlayerEvent
	@Subscribe
	public void onNetworkEvent(NetworkPlayerEvent event) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onNetworkEvent NetworkPlayerEvent");
		}
		tvMessage.setText("NetworkPlayerEvent CALLED Succed = " + Boolean.toString(event.hasSucced));
	}
	// method that will be called when someone posts an event NetworkGameEvent
	@Subscribe
	public void onNetworkEvent(NetworkGameEvent event) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onNetworkEvent NetworkGameEvent");
		}
		tvMessage.setText("NetworkGameEvent CALLED Succed = " + Boolean.toString(event.hasSucced));
	}
	// method that will be called when someone posts an event NetworkMatchEvent
	@Subscribe
	public void onNetworkEvent(NetworkMatchEvent event) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onNetworkEvent NetworkMatchEvent");
		}
		tvMessage.setText("NetworkMatchEvent CALLED Succed = " + Boolean.toString(event.hasSucced));
	}
	// method that will be called when someone posts an event NetworkPlayersmatchEvent
	@Subscribe
	public void onNetworkEvent(NetworkPlayersmatchEvent event) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onNetworkEvent NetworkPlayersmatchEvent");
		}
		tvMessage.setText("NetworkPlayersmatchEvent CALLED Succed = " + Boolean.toString(event.hasSucced));
	}
}

