package com.ynov.android.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.ynov.android.database.PlayersmatchDao;

@DatabaseTable(tableName = "playersmatch", daoClass = PlayersmatchDao.class)
public class Playersmatch {

													@DatabaseField( index=true, columnName="id", canBeNull=true, generatedId=true, allowGeneratedIdInsert=true ) // For Database
						
			private long _id;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int id_player;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int id_match;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int team;
		
	// Add the table name variable
	public static final String DB_TABLE_NAME	= "playersmatch";

			public static final String DB_FIELD_PLAYERSMATCH_ID		= "id";
				public static final String DB_FIELD_PLAYERSMATCH_ID_PLAYER		= "id_player";
				public static final String DB_FIELD_PLAYERSMATCH_ID_MATCH		= "id_match";
				public static final String DB_FIELD_PLAYERSMATCH_TEAM		= "team";
		
	// Empty constructor
	public Playersmatch () {
		super();
	}

																// Full parameters constructor (without ID)
public Playersmatch ( int id_player, int id_match, int team){
	super();

							this.id_player		= id_player;
				this.id_match		= id_match;
				this.team		= team;
	}
			

																// Full parameters constructor (without ID)
public Playersmatch ( long _id, int id_player, int id_match, int team){
	super();

			this._id 		= _id;
				this.id_player		= id_player;
				this.id_match		= id_match;
				this.team		= team;
	}


		public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

			public int getIdPlayer(){
		return this.id_player;
	}

	public void setIdPlayer( int id_player ){
		this.id_player = id_player;
	}

			public int getIdMatch(){
		return this.id_match;
	}

	public void setIdMatch( int id_match ){
		this.id_match = id_match;
	}

			public int getTeam(){
		return this.team;
	}

	public void setTeam( int team ){
		this.team = team;
	}

	
	@Override
	public String toString() {
		return "Playersmatch [ " +
												"_id=" + _id + ", " + "id_player=" + id_player +  ", " + "id_match=" + id_match +  ", " + "team=" + team + 
"]";
	}
	

}