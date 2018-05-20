package com.ynov.android.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

import com.ynov.android.database.PlayerDao;

@DatabaseTable(tableName = "player", daoClass = PlayerDao.class)
public class Player {

													@DatabaseField( index=true, columnName="id", canBeNull=true, generatedId=true, allowGeneratedIdInsert=true ) // For Database
						
			private long _id;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String name;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String firstname;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String comment;
							@DatabaseField( canBeNull=true ) // For Database
						
			private String picture;
		
	// Add the table name variable
	public static final String DB_TABLE_NAME	= "player";

			public static final String DB_FIELD_PLAYER_ID		= "id";
				public static final String DB_FIELD_PLAYER_NAME		= "name";
				public static final String DB_FIELD_PLAYER_FIRSTNAME		= "firstname";
				public static final String DB_FIELD_PLAYER_COMMENT		= "comment";
				public static final String DB_FIELD_PLAYER_PICTURE		= "picture";
		
	// Empty constructor
	public Player () {
		super();
	}

																				// Full parameters constructor (without ID)
public Player ( String name, String firstname, String comment, String picture){
	super();

							this.name		= name;
				this.firstname		= firstname;
				this.comment		= comment;
				this.picture		= picture;
	}
			

																				// Full parameters constructor (without ID)
public Player ( long _id, String name, String firstname, String comment, String picture){
	super();

			this._id 		= _id;
				this.name		= name;
				this.firstname		= firstname;
				this.comment		= comment;
				this.picture		= picture;
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

			public String getFirstname(){
		return this.firstname;
	}

	public void setFirstname( String firstname ){
		this.firstname = firstname;
	}

			public String getComment(){
		return this.comment;
	}

	public void setComment( String comment ){
		this.comment = comment;
	}

			public String getPicture(){
		return this.picture;
	}

	public void setPicture( String picture ){
		this.picture = picture;
	}

	
	@Override
	public String toString() {
		return "Player [ " +
															"_id=" + _id + ", " + "name=" + name +  ", " + "firstname=" + firstname +  ", " + "comment=" + comment +  ", " + "picture=" + picture + 
"]";
	}
	

}