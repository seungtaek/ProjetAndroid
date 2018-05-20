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
import com.ynov.android.adapter.MatchArrayAdapter;
import com.ynov.android.database.MatchDao;
import com.ynov.android.model.Match;

public class ListMatchActivity extends Activity{

	private final static String TAG = "ListMatchActivity";

	// For the database
	private MatchDao matchDao;

	// To display the list
	private ListView lv;
	
	// To display a message when the list is empty
	private LinearLayout llEmpty;

	// Adapter to display the items of the lsit
	ArrayAdapter<Match> arrayAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_list_match);

		// For action bar
		//		getSupportActionBar().setDisplayHomeAsUpEnabled(true); // Seems to be automatic, but just in case
		//getSupportActionBar().setLogo(R.drawable.ic_launcher);


		// Database
		DatabaseManager.init(this);
		this.matchDao = DatabaseManager.getInstance().getHelper().getMatchDao();


		lv = (ListView) findViewById(R.id.activity_list_match_listView_match);
		assert lv != null : "ListView activity_list_match_listView_match is null";

		llEmpty = (LinearLayout) findViewById(R.id.activity_list_match_linearLayout_empty);
		assert llEmpty != null : "LinearLayout activity_list_match_linearLayout_empty is null";

		loadAndDisplayMatch();
	}

	private void loadAndDisplayMatch(){
		
		List<Match> matchList = null;
		
		matchList = matchDao.findAll();
		
		

		if(matchList != null && matchList.size() > 0){

			// Hide the empty layout
			llEmpty.setVisibility(View.GONE);

			// This is the array adapter, it takes the context of the activity as a first
			// parameter, the type of list view as a second parameter and your array as a third parameter
			arrayAdapter = new MatchArrayAdapter(this,R.layout.item_match, matchList);
			lv.setAdapter(arrayAdapter);
			
			// TODO: if you need to manage a click on the item, keep the following code
			lv.setOnItemClickListener(new OnItemClickListener() {

				@Override
				public void onItemClick (AdapterView<?> parent, View view, int position, long id) {

					Intent intent = new Intent( ListMatchActivity.this, DetailMatchActivity.class);
					Bundle b = new Bundle();
					b.putLong(Constants.INTENT_PARAMS_MATCH_ID, id );
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