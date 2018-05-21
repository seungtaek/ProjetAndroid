package com.ynov.android;

import android.app.Activity;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.PlayerDao;
import com.ynov.android.model.Player;

import java.io.InputStream;
import java.net.URL;

public class DetailPlayerActivity extends Activity{

	private final static String TAG = "DetailPlayerActivity";

	// For the database
	private PlayerDao playerDao;
	private long playerId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_player);
		
		if (savedInstanceState != null) {
			playerId = savedInstanceState.getInt(Constants.INTENT_PARAMS_PLAYER_ID);
		} else {
			//get the Bundle out of the Intent...
			Bundle extras = getIntent().getExtras();
			playerId = (extras != null) ? extras
					.getLong(Constants.INTENT_PARAMS_PLAYER_ID) : -1;
		}

		playerDao = DatabaseManager.getInstance().getHelper().getPlayerDao();
		Player player = playerDao.findById(playerId);

		((TextView)findViewById(R.id.activity_detail_player_textView_id)).setText( Long.toString(player.getId()) );
		((TextView)findViewById(R.id.activity_detail_player_textView_name)).setText(player.getName() );
		((TextView)findViewById(R.id.activity_detail_player_textView_firstname)).setText(player.getFirstname() );
		((TextView)findViewById(R.id.activity_detail_player_textView_comment)).setText(player.getComment() );


		Drawable imageUser = LoadImageFromWebOperations(player.getPicture());

		((ImageView)findViewById(R.id.activity_detail_player_textView_picture)).setImageDrawable(imageUser);


		NotificationCompat.Builder mBuilder =
				new NotificationCompat.Builder(this)
						.setSmallIcon(R.mipmap.ic_launcher)
						.setContentTitle("Hello user")
						.setContentText("vous avez regardé "+player.getFirstname());
		NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
		notificationManager.notify(1, mBuilder.build());



	}
	public static Drawable LoadImageFromWebOperations(String url) {
		try {
			url = url.replace("m/i", "m/ynovapi/i");
			Log.d(TAG, "new url: "+url);
			InputStream is = (InputStream) new URL(url).getContent();
			Drawable d = Drawable.createFromStream(is, "src name");
			Log.d(TAG, "LoadImageFromWebOperations: worked");
			return d;
		} catch (Exception e) {
			Log.d(TAG, e.toString());
			return null;
		}
	}
}