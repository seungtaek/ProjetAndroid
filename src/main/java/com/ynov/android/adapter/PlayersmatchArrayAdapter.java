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
import com.ynov.android.model.Playersmatch;
	
public class PlayersmatchArrayAdapter extends ArrayAdapter<Playersmatch>{

	private final static String TAG = "PlayersmatchArrayAdapter";

	private LayoutInflater mInflater;
	private int mLayout;

	public PlayersmatchArrayAdapter(Context context, int layout, List<Playersmatch> itemList) {
		super(context, layout, itemList);

		mLayout = layout;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	private class ViewHolder {

						TextView tvId, tvIdPlayer, tvIdMatch, tvTeam;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder holder;

		final Playersmatch currentObject = getItem(position);

		//(1) : Recycle the layouts
		if (convertView == null) {
			//Init the item from the XML layout
			v = (LinearLayout) mInflater.inflate(mLayout, parent, false);

			//(2) : Get the items from the layout and put them into the holder
			holder = new ViewHolder();
			holder.tvId = (TextView)v.findViewById(R.id.item_playersmatch_textView_id);
			holder.tvIdPlayer = (TextView)v.findViewById(R.id.item_playersmatch_textView_id_player);
			holder.tvIdMatch = (TextView)v.findViewById(R.id.item_playersmatch_textView_id_match);
			holder.tvTeam = (TextView)v.findViewById(R.id.item_playersmatch_textView_team);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//(3) : Fill in the values
	
	holder.tvId.setText( Long.toString(currentObject.getId()) );
		
		holder.tvIdPlayer.setText( Integer.toString(currentObject.getIdPlayer()) );
		
		holder.tvIdMatch.setText( Integer.toString(currentObject.getIdMatch()) );
		
		holder.tvTeam.setText( Integer.toString(currentObject.getTeam()) );
	
		//Return the current view.
		return v;
	}
}