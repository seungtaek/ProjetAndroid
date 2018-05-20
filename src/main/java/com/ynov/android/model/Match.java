package com.ynov.android.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.ynov.android.database.MatchDao;

@DatabaseTable(tableName = "match", daoClass = MatchDao.class)
public class Match {

													@DatabaseField( index=true, columnName="id", canBeNull=true, generatedId=true, allowGeneratedIdInsert=true ) // For Database
						
			private long _id;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int id_activity;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String description;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int score_team_1;
							@DatabaseField( canBeNull=true ) // For Database
						
			private int score_team_2;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String datetime;
		
	// Add the table name variable
	public static final String DB_TABLE_NAME	= "match";

			public static final String DB_FIELD_MATCH_ID		= "id";
				public static final String DB_FIELD_MATCH_ID_ACTIVITY		= "id_activity";
				public static final String DB_FIELD_MATCH_DESCRIPTION		= "description";
				public static final String DB_FIELD_MATCH_SCORE_TEAM_1		= "score_team_1";
				public static final String DB_FIELD_MATCH_SCORE_TEAM_2		= "score_team_2";
				public static final String DB_FIELD_MATCH_DATETIME		= "datetime";
		
	// Empty constructor
	public Match () {
		super();
	}

																								// Full parameters constructor (without ID)
public Match ( int id_activity, String description, int score_team_1, int score_team_2, String datetime){
	super();

							this.id_activity		= id_activity;
				this.description		= description;
				this.score_team_1		= score_team_1;
				this.score_team_2		= score_team_2;
				this.datetime		= datetime;
	}
			

																								// Full parameters constructor (without ID)
public Match ( long _id, int id_activity, String description, int score_team_1, int score_team_2, String datetime){
	super();

			this._id 		= _id;
				this.id_activity		= id_activity;
				this.description		= description;
				this.score_team_1		= score_team_1;
				this.score_team_2		= score_team_2;
				this.datetime		= datetime;
	}


		public long getId() {
		return _id;
	}

	public void setId(long _id) {
		this._id = _id;
	}

			public int getIdActivity(){
		return this.id_activity;
	}

	public void setIdActivity( int id_activity ){
		this.id_activity = id_activity;
	}

			public String getDescription(){
		return this.description;
	}

	public void setDescription( String description ){
		this.description = description;
	}

			public int getScoreTeam1(){
		return this.score_team_1;
	}

	public void setScoreTeam1( int score_team_1 ){
		this.score_team_1 = score_team_1;
	}

			public int getScoreTeam2(){
		return this.score_team_2;
	}

	public void setScoreTeam2( int score_team_2 ){
		this.score_team_2 = score_team_2;
	}

			public String getDatetime(){
		return this.datetime;
	}

	public void setDatetime( String datetime ){
		this.datetime = datetime;
	}

	
	@Override
	public String toString() {
		return "Match [ " +
																		"_id=" + _id + ", " + "id_activity=" + id_activity +  ", " + "description=" + description +  ", " + "score_team_1=" + score_team_1 +  ", " + "score_team_2=" + score_team_2 +  ", " + "datetime=" + datetime + 
"]";
	}
	

}