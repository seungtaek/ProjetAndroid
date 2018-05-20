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
import com.ynov.android.adapter.PlayerArrayAdapter;
import com.ynov.android.database.PlayerDao;
import com.ynov.android.model.Player;

public class ListPlayerActivity extends Activity{

	private final static String TAG = "ListPlayerActivity";

	// For the database
	private PlayerDao playerDao;

	// To display the list
	private ListView lv;
	
	// To display a message when the list is empty
	private LinearLayout llEmpty;

	// Adapter to display the items of the lsit
	ArrayAdapter<Player> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_player);

		// For action bar
		//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Seems to be automatic, but just in case
		//getSupportActionBar().setLogo(R.drawable.ic_launcher);


		// Database
		DatabaseManager.init(this);
		this.playerDao = DatabaseManager.getInstance().getHelper().getPlayerDao();


		lv = (ListView) findViewById(R.id.activity_list_player_listView_player);
		assert lv != null : "ListView activity_list_player_listView_player is null";

		llEmpty = (LinearLayout) findViewById(R.id.activity_list_player_linearLayout_empty);
		assert llEmpty != null : "LinearLayout activity_list_player_linearLayout_empty is null";

		loadAndDisplayPlayer();
	}

	private void loadAndDisplayPlayer(){
		
		List<Player> playerList = null;
		
		playerList = playerDao.findAll();
		
		

		if(playerList != null && playerList.size() > 0){

			// Hide the empty layout
			llEmpty.setVisibility(View.GONE);

			// This is the array adapter, it takes the context of the activity as a first
			// parameter, the type of list view as a second parameter and your array as a third parameter
			arrayAdapter = new PlayerArrayAdapter(this,R.layout.item_player, playerList);
			lv.setAdapter(arrayAdapter);
			
			// TODO: if you need to manage a click on the item, keep the following code
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

					Intent intent = new Intent( ListPlayerActivity.this, DetailPlayerActivity.class);
					Bundle b = new Bundle();
					b.putLong(Constants.INTENT_PARAMS_PLAYER_ID, id );
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