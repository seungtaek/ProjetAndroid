package com.ynov.android;

import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import com.ynov.android.R;
import com.ynov.android.database.DatabaseManager;
import com.ynov.android.adapter.GameArrayAdapter;
import com.ynov.android.database.GameDao;
import com.ynov.android.model.Game;

public class ListGameActivity extends Activity{

	private final static String TAG = "ListGameActivity";

	// For the database
	private GameDao gameDao;

	// To display the list
	private ListView lv;
	
	// To display a message when the list is empty
	private LinearLayout llEmpty;

	// Adapter to display the items of the lsit
	ArrayAdapter<Game> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_game);

		// For action bar
		//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Seems to be automatic, but just in case
		//getSupportActionBar().setLogo(R.drawable.ic_launcher);


		// Database
		DatabaseManager.init(this);
		this.gameDao = DatabaseManager.getInstance().getHelper().getGameDao();


		lv = (ListView) findViewById(R.id.activity_list_game_listView_game);
		assert lv != null : "ListView activity_list_game_listView_game is null";

		llEmpty = (LinearLayout) findViewById(R.id.activity_list_game_linearLayout_empty);
		assert llEmpty != null : "LinearLayout activity_list_game_linearLayout_empty is null";

		loadAndDisplayGame();
	}

	private void loadAndDisplayGame(){
		
		List<Game> gameList = null;
		
		gameList = gameDao.findAll();
		
		

		if(gameList != null && gameList.size() > 0){

			// Hide the empty layout
			llEmpty.setVisibility(View.GONE);

			// This is the array adapter, it takes the context of the activity as a first
			// parameter, the type of list view as a second parameter and your array as a third parameter
			arrayAdapter = new GameArrayAdapter(this,R.layout.item_game, gameList);
			lv.setAdapter(arrayAdapter);
			
			// TODO: if you need to manage a click on the item, keep the following code
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

					Intent intent = new Intent( ListGameActivity.this, DetailGameActivity.class);
					Bundle b = new Bundle();
					b.putLong(Constants.INTENT_PARAMS_GAME_ID, id );
					intent.putExtras(b);
					startActivity(intent);

				}
			});


		}else{
			// Show the empty layout
			llEmpty.setVisibility(View.VISIBLE);
		}

	}
}