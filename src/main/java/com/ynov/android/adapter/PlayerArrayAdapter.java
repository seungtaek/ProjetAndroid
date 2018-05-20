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
import com.ynov.android.model.Player;
	
public class PlayerArrayAdapter extends ArrayAdapter<Player>{

	private final static String TAG = "PlayerArrayAdapter";

	private LayoutInflater mInflater;
	private int mLayout;

	public PlayerArrayAdapter(Context context, int layout, List<Player> itemList) {
		super(context, layout, itemList);

		mLayout = layout;
		mInflater = LayoutInflater.from(context);
	}

	@Override
	public long getItemId(int position) {
		return getItem(position).getId();
	}

	private class ViewHolder {

							TextView tvId, tvName, tvFirstname, tvComment, tvPicture;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		View v = convertView;
		final ViewHolder holder;

		final Player currentObject = getItem(position);

		//(1) : Recycle the layouts
		if (convertView == null) {
			//Init the item from the XML layout
			v = (LinearLayout) mInflater.inflate(mLayout, parent, false);

			//(2) : Get the items from the layout and put them into the holder
			holder = new ViewHolder();
			holder.tvId = (TextView)v.findViewById(R.id.item_player_textView_id);
			holder.tvName = (TextView)v.findViewById(R.id.item_player_textView_name);
			holder.tvFirstname = (TextView)v.findViewById(R.id.item_player_textView_firstname);
			holder.tvComment = (TextView)v.findViewById(R.id.item_player_textView_comment);
			holder.tvPicture = (TextView)v.findViewById(R.id.item_player_textView_picture);
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
		
		if(currentObject.getFirstname() != null && currentObject.getFirstname().trim().length()>0 ){
			holder.tvFirstname.setText( currentObject.getFirstname().trim() );
		}else{
			holder.tvFirstname.setText("");
		}
		
		if(currentObject.getComment() != null && currentObject.getComment().trim().length()>0 ){
			holder.tvComment.setText( currentObject.getComment().trim() );
		}else{
			holder.tvComment.setText("");
		}
		
		if(currentObject.getPicture() != null && currentObject.getPicture().trim().length()>0 ){
			holder.tvPicture.setText( currentObject.getPicture().trim() );
		}else{
			holder.tvPicture.setText("");
		}
	
		//Return the current view.
		return v;
	}
}