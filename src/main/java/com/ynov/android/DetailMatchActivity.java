package com.ynov.android;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.ynov.android.database.DatabaseManager;
import com.ynov.android.database.MatchDao;
import com.ynov.android.model.Match;

public class DetailMatchActivity extends Activity{

	private final static String TAG = "DetailMatchActivity";

	// For the database
	private MatchDao matchDao;
	private long matchId;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_detail_match);
		
		if (savedInstanceState != null) {
			matchId = savedInstanceState.getInt(Constants.INTENT_PARAMS_MATCH_ID);
		} else {
			//get the Bundle out of the Intent...
			Bundle extras = getIntent().getExtras();
			matchId = (extras != null) ? extras
					.getLong(Constants.INTENT_PARAMS_MATCH_ID) : -1;
		}

		matchDao = DatabaseManager.getInstance().getHelper().getMatchDao();
		Match match = matchDao.findById(matchId);

		((TextView)findViewById(R.id.activity_detail_match_textView_id)).setText( Long.toString(match.getId()) );
		((TextView)findViewById(R.id.activity_detail_match_textView_id_activity)).setText( Integer.toString(match.getIdActivity()) );
		((TextView)findViewById(R.id.activity_detail_match_textView_description)).setText(match.getDescription() );
		((TextView)findViewById(R.id.activity_detail_match_textView_score_team_1)).setText( Integer.toString(match.getScoreTeam1()) );
		((TextView)findViewById(R.id.activity_detail_match_textView_score_team_2)).setText( Integer.toString(match.getScoreTeam2()) );
		((TextView)findViewById(R.id.activity_detail_match_textView_datetime)).setText(match.getDatetime() );

	}
}