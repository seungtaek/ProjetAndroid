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
import com.ynov.android.model.Game;
	
public class GameArrayAdapter extends ArrayAdapter<Game>{

	private final static String TAG = "GameArrayAdapter";

	private LayoutInflater mInflater;
	private int mLayout;

	public GameArrayAdapter(Context context, int layout, List<Game> itemList) {
		super(context, layout, itemList);

		mLayout = layout;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	private class ViewHolder {

					TextView tvId, tvName, tvNbrPlayersByTeam;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder holder;

		final Game currentObject = getItem(position);

		//(1) : Recycle the layouts
		if (convertView == null) {
			//Init the item from the XML layout
			v = (LinearLayout) mInflater.inflate(mLayout, parent, false);

			//(2) : Get the items from the layout and put them into the holder
			holder = new ViewHolder();
			holder.tvId = (TextView)v.findViewById(R.id.item_game_textView_id);
			holder.tvName = (TextView)v.findViewById(R.id.item_game_textView_name);
			holder.tvNbrPlayersByTeam = (TextView)v.findViewById(R.id.item_game_textView_nbr_players_by_team);
			v.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		//(3) : Fill in the values
	
	holder.tvId.setText( Long.toString(currentObject.getId()) );
		
		if(currentObject.getName() != null && currentObject.getName().trim().length()>0 ){
			holder.tvName.setText( currentObject.getName().trim() );
		}else{
			holder.tvName.setText("");
		}
		
		holder.tvNbrPlayersByTeam.setText( Integer.toString(currentObject.getNbrPlayersByTeam()) );
	
		//Return the current view.
		return v;
	}
}