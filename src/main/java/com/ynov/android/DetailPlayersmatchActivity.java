package com.ynov.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.PlayersmatchDao;
import com.ynov.android.model.Playersmatch;

public class DetailPlayersmatchActivity extends Activity{

	private final static String TAG = "DetailPlayersmatchActivity";

	// For the database
	private PlayersmatchDao playersmatchDao;
	private long playersmatchId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_playersmatch);
		
		if (savedInstanceState != null) {
			playersmatchId = savedInstanceState.getInt(Constants.INTENT_PARAMS_PLAYERSMATCH_ID);
		} else {
			//get the Bundle out of the Intent...
			Bundle extras = getIntent().getExtras();
			playersmatchId = (extras != null) ? extras
					.getLong(Constants.INTENT_PARAMS_PLAYERSMATCH_ID) : -1;
		}

		playersmatchDao = DatabaseManager.getInstance().getHelper().getPlayersmatchDao();
		Playersmatch playersmatch = playersmatchDao.findById(playersmatchId);

		((TextView)findViewById(R.id.activity_detail_playersmatch_textView_id)).setText( Long.toString(playersmatch.getId()) );
		((TextView)findViewById(R.id.activity_detail_playersmatch_textView_id_player)).setText( Integer.toString(playersmatch.getIdPlayer()) );
		((TextView)findViewById(R.id.activity_detail_playersmatch_textView_id_match)).setText( Integer.toString(playersmatch.getIdMatch()) );
		((TextView)findViewById(R.id.activity_detail_playersmatch_textView_team)).setText( Integer.toString(playersmatch.getTeam()) );

	}
}