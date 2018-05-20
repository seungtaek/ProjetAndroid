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
import com.ynov.android.adapter.PlayersmatchArrayAdapter;
import com.ynov.android.database.PlayersmatchDao;
import com.ynov.android.model.Playersmatch;

public class ListPlayersmatchActivity extends Activity{

	private final static String TAG = "ListPlayersmatchActivity";

	// For the database
	private PlayersmatchDao playersmatchDao;

	// To display the list
	private ListView lv;
	
	// To display a message when the list is empty
	private LinearLayout llEmpty;

	// Adapter to display the items of the lsit
	ArrayAdapter<Playersmatch> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_playersmatch);

		// For action bar
		//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Seems to be automatic, but just in case
		//getSupportActionBar().setLogo(R.drawable.ic_launcher);


		// Database
		DatabaseManager.init(this);
		this.playersmatchDao = DatabaseManager.getInstance().getHelper().getPlayersmatchDao();


		lv = (ListView) findViewById(R.id.activity_list_playersmatch_listView_playersmatch);
		assert lv != null : "ListView activity_list_playersmatch_listView_playersmatch is null";

		llEmpty = (LinearLayout) findViewById(R.id.activity_list_playersmatch_linearLayout_empty);
		assert llEmpty != null : "LinearLayout activity_list_playersmatch_linearLayout_empty is null";

		loadAndDisplayPlayersmatch();
	}

	private void loadAndDisplayPlayersmatch(){
		
		List<Playersmatch> playersmatchList = null;
		
		playersmatchList = playersmatchDao.findAll();
		
		

		if(playersmatchList != null && playersmatchList.size() > 0){

			// Hide the empty layout
			llEmpty.setVisibility(View.GONE);

			// This is the array adapter, it takes the context of the activity as a first
			// parameter, the type of list view as a second parameter and your array as a third parameter
			arrayAdapter = new PlayersmatchArrayAdapter(this,R.layout.item_playersmatch, playersmatchList);
			lv.setAdapter(arrayAdapter);
			
			// TODO: if you need to manage a click on the item, keep the following code
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

					Intent intent = new Intent( ListPlayersmatchActivity.this, DetailPlayersmatchActivity.class);
					Bundle b = new Bundle();
					b.putLong(Constants.INTENT_PARAMS_PLAYERSMATCH_ID, id );
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