package com.ynov.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.GameDao;
import com.ynov.android.model.Game;

public class DetailGameActivity extends Activity{

	private final static String TAG = "DetailGameActivity";

	// For the database
	private GameDao gameDao;
	private long gameId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_game);
		
		if (savedInstanceState != null) {
			gameId = savedInstanceState.getInt(Constants.INTENT_PARAMS_GAME_ID);
		} else {
			//get the Bundle out of the Intent...
			Bundle extras = getIntent().getExtras();
			gameId = (extras != null) ? extras
					.getLong(Constants.INTENT_PARAMS_GAME_ID) : -1;
		}

		gameDao = DatabaseManager.getInstance().getHelper().getGameDao();
		Game game = gameDao.findById(gameId);

		((TextView)findViewById(R.id.activity_detail_game_textView_id)).setText( Long.toString(game.getId()) );
		((TextView)findViewById(R.id.activity_detail_game_textView_name)).setText(game.getName() );
		((TextView)findViewById(R.id.activity_detail_game_textView_nbr_players_by_team)).setText( Integer.toString(game.getNbrPlayersByTeam()) );

	}
}