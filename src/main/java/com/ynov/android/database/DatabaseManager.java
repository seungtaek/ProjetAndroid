package com.ynov.android.database;
		
import android.content.Context;

public class DatabaseManager {

	static private DatabaseManager instance;

	static public void init(Context context) {
		if(null==instance) {
			instance = new DatabaseManager(context);
		}
	}

	static public DatabaseManager getInstance() {
		return instance;
	}

	private DatabaseHelper helper;

	private DatabaseManager(Context context) {
		helper = new DatabaseHelper(context);
	}

	public DatabaseHelper getHelper() {
		return helper;
	}
}