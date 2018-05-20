package com.ynov.android.database;

import java.sql.SQLException;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;

// Import the ressources for the config file
import com.ynov.android.R;
// Imports for the local model
import com.ynov.android.model.Player;
import com.ynov.android.model.Game;
import com.ynov.android.model.Match;
import com.ynov.android.model.Playersmatch;


public class DatabaseHelper extends OrmLiteSqliteOpenHelper {

	private static final String DATABASE_NAME = "database";
	private static final int DATABASE_VERSION = 1;

	// The DAO objects
		private PlayerDao playerDao = null;
		private GameDao gameDao = null;
		private MatchDao matchDao = null;
		private PlayersmatchDao playersmatchDao = null;
	
	public DatabaseHelper(Context context) {
				// Without optimisation file
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
			}

	@Override
	public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
		try {

		// Create the table(s)
				TableUtils.createTable(connectionSource, Player.class);
				TableUtils.createTable(connectionSource, Game.class);
				TableUtils.createTable(connectionSource, Match.class);
				TableUtils.createTable(connectionSource, Playersmatch.class);
		

		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't create database", e);
			throw new RuntimeException(e);
		}
	}

	@Override
	public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {
		try {
			// Drop the table(s)
						TableUtils.dropTable(connectionSource, Player.class, true);
						TableUtils.dropTable(connectionSource, Game.class, true);
						TableUtils.dropTable(connectionSource, Match.class, true);
						TableUtils.dropTable(connectionSource, Playersmatch.class, true);
						
			// After we drop the old databases, we create the new ones
			onCreate(database, connectionSource);
			
			// Other example smarter
			// if (oldVersion < 2) {
			//   // we added the age column in version 2
			//   dao.executeRaw("ALTER TABLE `account` ADD COLUMN age INTEGER;");
			// }
			// if (oldVersion < 3) {
			//   // we added the weight column in version 3
			//   dao.executeRaw("ALTER TABLE `account` ADD COLUMN weight INTEGER;");
			// }
			
		} catch (SQLException e) {
			Log.e(DatabaseHelper.class.getName(), "Can't drop databases", e);
			throw new RuntimeException(e);
		}
	}

	
	@SuppressWarnings("unchecked")
	public PlayerDao getPlayerDao() {
		if(null == playerDao) {
			try {
				playerDao = DaoManager.createDao(getConnectionSource(), Player.class);
			}catch(java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return playerDao;
	}
	
	@SuppressWarnings("unchecked")
	public GameDao getGameDao() {
		if(null == gameDao) {
			try {
				gameDao = DaoManager.createDao(getConnectionSource(), Game.class);
			}catch(java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return gameDao;
	}
	
	@SuppressWarnings("unchecked")
	public MatchDao getMatchDao() {
		if(null == matchDao) {
			try {
				matchDao = DaoManager.createDao(getConnectionSource(), Match.class);
			}catch(java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return matchDao;
	}
	
	@SuppressWarnings("unchecked")
	public PlayersmatchDao getPlayersmatchDao() {
		if(null == playersmatchDao) {
			try {
				playersmatchDao = DaoManager.createDao(getConnectionSource(), Playersmatch.class);
			}catch(java.sql.SQLException e) {
				e.printStackTrace();
			}
		}
		return playersmatchDao;
	}
	
}