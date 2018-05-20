package com.ynov.android.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.ynov.android.R;
import com.ynov.android.model.Match;
	
public class MatchArrayAdapter extends ArrayAdapter<Match>{

	private final static String TAG = "MatchArrayAdapter";

	private LayoutInflater mInflater;
	private int mLayout;

	public MatchArrayAdapter(Context context, int layout, List<Match> itemList) {
		super(context, layout, itemList);

		mLayout = layout;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	private class ViewHolder {

								TextView tvId, tvIdActivity, tvDescription, tvScoreTeam1, tvScoreTeam2, tvDatetime;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder holder;

		final Match currentObject = getItem(position);

		//(1) : Recycle the layouts
		if (convertView == null) {
			//Init the item from the XML layout
			v = (LinearLayout) mInflater.inflate(mLayout, parent, false);

			//(2) : Get the items from the layout and put them into the holder
			holder = new ViewHolder();
			holder.tvId = (TextView)v.findViewById(R.id.item_match_textView_id);
			holder.tvIdActivity = (TextView)v.findViewById(R.id.item_match_textView_id_activity);
			holder.tvDescription = (TextView)v.findViewById(R.id.item_match_textView_description);
			holder.tvScoreTeam1 = (TextView)v.findViewById(R.id.item_match_textView_score_team_1);
			holder.tvScoreTeam2 = (TextView)v.findViewById(R.id.item_match_textView_score_team_2);
			holder.tvDatetime = (TextView)v.findViewById(R.id.item_match_textView_datetime);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//(3) : Fill in the values
	
	holder.tvId.setText( Long.toString(currentObject.getId()) );
		
		holder.tvIdActivity.setText( Integer.toString(currentObject.getIdActivity()) );
		
		if(currentObject.getDescription() != null && currentObject.getDescription().trim().length()>0 ){
			holder.tvDescription.setText( currentObject.getDescription().trim() );
		}else{
			holder.tvDescription.setText("");
		}
		
		holder.tvScoreTeam1.setText( Integer.toString(currentObject.getScoreTeam1()) );
		
		holder.tvScoreTeam2.setText( Integer.toString(currentObject.getScoreTeam2()) );
		
		if(currentObject.getDatetime() != null && currentObject.getDatetime().trim().length()>0 ){
			holder.tvDatetime.setText( currentObject.getDatetime().trim() );
		}else{
			holder.tvDatetime.setText("");
		}
	
		//Return the current view.
		return v;
	}
}