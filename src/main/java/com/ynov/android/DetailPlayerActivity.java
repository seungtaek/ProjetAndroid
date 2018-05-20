package com.ynov.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.PlayerDao;
import com.ynov.android.model.Player;

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
		((TextView)findViewById(R.id.activity_detail_player_textView_picture)).setText(player.getPicture() );

	}
}