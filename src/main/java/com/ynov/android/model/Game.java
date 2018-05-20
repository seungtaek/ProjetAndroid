package com.ynov.android.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.ynov.android.database.GameDao;

@DatabaseTable(tableName = "game", daoClass = GameDao.class)
public class Game {

													@DatabaseField( index=true, columnName="id", canBeNull=true, generatedId=true, allowGeneratedIdInsert=true ) // For Database
						
			private long _id;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String name;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int nbr_players_by_team;
		
	// Add the table name variable
	public static final String DB_TABLE_NAME	= "game";

			public static final String DB_FIELD_GAME_ID		= "id";
				public static final String DB_FIELD_GAME_NAME		= "name";
				public static final String DB_FIELD_GAME_NBR_PLAYERS_BY_TEAM		= "nbr_players_by_team";
		
	// Empty constructor
	public Game () {
		super();
	}

												// Full parameters constructor (without ID)
public Game ( String name, int nbr_players_by_team){
	super();

							this.name		= name;
				this.nbr_players_by_team		= nbr_players_by_team;
	}
			

												// Full parameters constructor (without ID)
public Game ( long _id, String name, int nbr_players_by_team){
	super();

			this._id 		= _id;
				this.name		= name;
				this.nbr_players_by_team		= nbr_players_by_team;
	}


		public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

			public String getName(){
		return this.name;
	}

	public void setName( String name ){
		this.name = name;
	}

			public int getNbrPlayersByTeam(){
		return this.nbr_players_by_team;
	}

	public void setNbrPlayersByTeam( int nbr_players_by_team ){
		this.nbr_players_by_team = nbr_players_by_team;
	}

	
	@Override
	public String toString() {
		return "Game [ " +
									"_id=" + _id + ", " + "name=" + name +  ", " + "nbr_players_by_team=" + nbr_players_by_team + 
"]";
	}
	

}