package com.ynov.android.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.util.Log;

import com.ynov.android.BuildConfig;
import com.ynov.android.api.APIServiceInterface;
import com.ynov.android.api.GameReply;
import com.ynov.android.api.MatchReply;
import com.ynov.android.api.PlayerReply;
import com.ynov.android.api.PlayersmatchReply;
import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.GameDao;
import com.ynov.android.database.MatchDao;
import com.ynov.android.database.PlayerDao;
import com.ynov.android.database.PlayersmatchDao;
import com.ynov.android.events.NetworkDummyEvent;
import com.ynov.android.events.NetworkGameEvent;
import com.ynov.android.events.NetworkMatchEvent;
import com.ynov.android.events.NetworkPlayerEvent;
import com.ynov.android.events.NetworkPlayersmatchEvent;
import com.ynov.android.model.Game;
import com.ynov.android.model.Match;
import com.ynov.android.model.Player;
import com.ynov.android.model.Playersmatch;

import org.greenrobot.eventbus.EventBus;

import java.sql.SQLException;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import okhttp3.logging.HttpLoggingInterceptor.Level;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LocalNetworkService extends Service {

	private final static String TAG = "LocalNetworkService";

	private APIServiceInterface apiServiceInterface;
	private final IBinder mBinder = new MyBinder();

	// For the database
	private PlayerDao playerDao;
	private GameDao gameDao;
	private MatchDao matchDao;
	private PlayersmatchDao playersmatchDao;

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onStartCommand ");
		}
		return Service.START_NOT_STICKY;
	}

	private void initServer(String serverUrl){
		OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
		HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
		logging.setLevel(Level.BODY);

		OkHttpClient client = clientBuilder
				.addInterceptor(logging)
				.build();

		Retrofit retrofit = new Retrofit.Builder()
		.baseUrl(serverUrl)
		.addConverterFactory(GsonConverterFactory.create())
		.client(client)
		.build();

		apiServiceInterface = retrofit.create(APIServiceInterface.class);
	}
	
	//	@Override
	//	public void onCreate() {
	//		super.onCreate();
	//		EventBus.getDefault().register(this); // register EventBus
	//		
	//		if (BuildConfig.DEBUG) {
	//			Log.d(TAG, "onCreate ");
	//		}
	//	};
	//	
	//	@Override
	//	public void onDestroy() {
	//		
	//		if (BuildConfig.DEBUG) {
	//			Log.d(TAG, "onDestroy ");
	//		}
	//		
	//		EventBus.getDefault().unregister(this); // unregister EventBus
	//		super.onDestroy();
	//	};

	@Override
	public IBinder onBind(Intent arg0) {

		if (BuildConfig.DEBUG) {
			Log.d(TAG, "onBind ");
		}

		if(apiServiceInterface == null){
			initServer("http://tristan-salaun.com/ynovapi/");
		}

		// Database
		DatabaseManager.init(this);
		this.playerDao = DatabaseManager.getInstance().getHelper().getPlayerDao();
		this.gameDao = DatabaseManager.getInstance().getHelper().getGameDao();
		this.matchDao = DatabaseManager.getInstance().getHelper().getMatchDao();
		this.playersmatchDao = DatabaseManager.getInstance().getHelper().getPlayersmatchDao();

		return mBinder;
	}

	public class MyBinder extends Binder {
		public LocalNetworkService getService() {

			if (BuildConfig.DEBUG) {
				Log.d(TAG, "getService ");
			}

			return LocalNetworkService.this;
		}
	}

	// Implements network call here
	public void callDummyNetworkCall() {

		if (BuildConfig.DEBUG) {
			Log.d(TAG, "callDummyNetworkCall");
		}

		Handler handler = new Handler();
		handler.postDelayed(new Runnable() {

			@Override
			public void run() {
				EventBus.getDefault().post(new NetworkDummyEvent());
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callDummyNetworkCall Called");
				}
			}
		}, 3000); // 3000ms delay
	}
	

		public void callLoadPlayer( ){

		Call<PlayerReply> callPlayer = apiServiceInterface.loadPlayer();

		callPlayer.enqueue(new Callback<PlayerReply>() {
			@Override
			public void onFailure(Call<PlayerReply> request, Throwable t) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callPlayer onFailure " + t);
				}
				EventBus.getDefault().post(new NetworkPlayerEvent());
			}

			@Override
			public void onResponse(Call<PlayerReply> request, Response<PlayerReply> response) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callPlayer onResponse code = " + response.code());
					Log.d(TAG, "callPlayer onResponse message = " + response.message());
					Log.d(TAG, "callPlayer onResponse errorBody = " + response.errorBody());
					Log.d(TAG, "callPlayer onResponse toString = " + response.toString());
					Log.d(TAG, "callPlayer onResponse raw = " + response.raw().toString());
					Log.d(TAG, "callPlayer onResponse isSuccess = " + Boolean.toString(response.isSuccessful()) );
					Log.d(TAG, "callPlayer onResponse body = " + response.body());
				}

				if(response.body() != null && response.body().getPlayerList() != null && response.body().getPlayerList().length > 0 ){
					LocalNetworkService.this.playerDao.clear();
					for (Player currentPlayer : response.body().getPlayerList()) {
						try {
							LocalNetworkService.this.playerDao.create(currentPlayer);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}

				NetworkPlayerEvent event = new NetworkPlayerEvent();
				event.hasSucced = true;
				EventBus.getDefault().post(event);
			}
		});
	}
		public void callLoadGame( ){

		Call<GameReply> callGame = apiServiceInterface.loadGame();

		callGame.enqueue(new Callback<GameReply>() {
			@Override
			public void onFailure(Call<GameReply> request, Throwable t) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callGame onFailure " + t);
				}
				EventBus.getDefault().post(new NetworkGameEvent());
			}

			@Override
			public void onResponse(Call<GameReply> request, Response<GameReply> response) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callGame onResponse code = " + response.code());
					Log.d(TAG, "callGame onResponse message = " + response.message());
					Log.d(TAG, "callGame onResponse errorBody = " + response.errorBody());
					Log.d(TAG, "callGame onResponse toString = " + response.toString());
					Log.d(TAG, "callGame onResponse raw = " + response.raw().toString());
					Log.d(TAG, "callGame onResponse isSuccess = " + Boolean.toString(response.isSuccessful()) );
					Log.d(TAG, "callGame onResponse body = " + response.body());
				}

				if(response.body() != null && response.body().getGameList() != null && response.body().getGameList().length > 0 ){
					LocalNetworkService.this.gameDao.clear();
					for (Game currentGame : response.body().getGameList()) {
						try {
							LocalNetworkService.this.gameDao.create(currentGame);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				NetworkGameEvent event = new NetworkGameEvent();
				event.hasSucced = true;
				EventBus.getDefault().post(event);
			}
		});
	}
		public void callLoadMatch( ){

		Call<MatchReply> callMatch = apiServiceInterface.loadMatch();

		callMatch.enqueue(new Callback<MatchReply>() {
			@Override
			public void onFailure(Call<MatchReply> request, Throwable t) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callMatch onFailure " + t);
				}
				EventBus.getDefault().post(new NetworkMatchEvent());
			}

			@Override
			public void onResponse(Call<MatchReply> request, Response<MatchReply> response) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callMatch onResponse code = " + response.code());
					Log.d(TAG, "callMatch onResponse message = " + response.message());
					Log.d(TAG, "callMatch onResponse errorBody = " + response.errorBody());
					Log.d(TAG, "callMatch onResponse toString = " + response.toString());
					Log.d(TAG, "callMatch onResponse raw = " + response.raw().toString());
					Log.d(TAG, "callMatch onResponse isSuccess = " + Boolean.toString(response.isSuccessful()) );
					Log.d(TAG, "callMatch onResponse body = " + response.body());
				}

				if(response.body() != null && response.body().getMatchList() != null && response.body().getMatchList().length > 0 ){
					LocalNetworkService.this.matchDao.clear();
					for (Match currentMatch : response.body().getMatchList()) {
						try {
							LocalNetworkService.this.matchDao.create(currentMatch);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				NetworkMatchEvent event = new NetworkMatchEvent();
				event.hasSucced = true;
				EventBus.getDefault().post(event);
			}
		});
	}
		public void callLoadPlayersmatch( ){

		Call<PlayersmatchReply> callPlayersmatch = apiServiceInterface.loadPlayersmatch();

		callPlayersmatch.enqueue(new Callback<PlayersmatchReply>() {
			@Override
			public void onFailure(Call<PlayersmatchReply> request, Throwable t) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callPlayersmatch onFailure " + t);
				}
				EventBus.getDefault().post(new NetworkPlayersmatchEvent());
			}

			@Override
			public void onResponse(Call<PlayersmatchReply> request, Response<PlayersmatchReply> response) {
				if (BuildConfig.DEBUG) {
					Log.d(TAG, "callPlayersmatch onResponse code = " + response.code());
					Log.d(TAG, "callPlayersmatch onResponse message = " + response.message());
					Log.d(TAG, "callPlayersmatch onResponse errorBody = " + response.errorBody());
					Log.d(TAG, "callPlayersmatch onResponse toString = " + response.toString());
					Log.d(TAG, "callPlayersmatch onResponse raw = " + response.raw().toString());
					Log.d(TAG, "callPlayersmatch onResponse isSuccess = " + Boolean.toString(response.isSuccessful()) );
					Log.d(TAG, "callPlayersmatch onResponse body = " + response.body());
				}

				if(response.body() != null && response.body().getPlayersmatchList() != null && response.body().getPlayersmatchList().length > 0 ){
					LocalNetworkService.this.playersmatchDao.clear();
					for (Playersmatch currentPlayersmatch : response.body().getPlayersmatchList()) {
						try {
							LocalNetworkService.this.playersmatchDao.create(currentPlayersmatch);
						} catch (SQLException e) {
							e.printStackTrace();
						}
					}
				}
				
				NetworkPlayersmatchEvent event = new NetworkPlayersmatchEvent();
				event.hasSucced = true;
				EventBus.getDefault().post(event);
			}
		});
	}
		
}
