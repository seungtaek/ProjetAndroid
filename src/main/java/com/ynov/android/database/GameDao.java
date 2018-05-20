package com.ynov.android.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import com.ynov.android.model.Game;


public class GameDao extends BaseDaoImpl<Game, Long>{
	public GameDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Game.class);
	}
	
	public GameDao(ConnectionSource connectionSource, DatabaseTableConfig<Game> tableConfig) throws SQLException { 
		super(connectionSource, tableConfig);
	}

	public List<Game> findAll() {
		
		List<Game> games = new ArrayList<Game>();;
		try {
			games = queryForAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return games;
	}

	public Game findById(long id) {
		Game game = null;
		try {
			game = (Game) queryForId(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return game;
	}
	
	public void clear(){
		
		try {
			TableUtils.clearTable(getConnectionSource(), Game.class);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/*
	// Sample using COUNT
	@SuppressWarnings("unchecked")
	public long countForId(long id) {

		try {
			QueryBuilder<Game, Long> gameqb = queryBuilder();
			gameqb.where().eq(Game.DB_FIELD_TO_BE_DEFINED, id);
			
			long nbreOfItems = countOf( gameqb.setCountOf(true).prepare() );
			return nbreOfItems;

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	*/

	/*
	// Sample using COUNT (more complex)
	@SuppressWarnings("unchecked")
	public long countForIdMoreComplex(long id) {

		try {
			QueryBuilder<Game, Long> gameqb = queryBuilder();

			Where where = gameqb.where();

			where.eq(Game.DB_FIELD_TO_BE_DEFINED, id);
			where.and();
			where.eq(Game.DB_FIELD_TO_BE_DEFINED, id);
			
			// setCountOf(true) : Set the flag saying that we should only return the count of the results.
			// This query can then be used by countOf(PreparedQuery). To get the count-of directly, use countOf().
			long nbreOfItems = countOf( gameqb.setCountOf(true).prepare() );
			return nbreOfItems;

		}catch(SQLException e) {
			e.printStackTrace();
		}

		return 0;
	}
	*/

	/*
	// Sample using simple equal
	@SuppressWarnings("unchecked")
	public Game findByStringCriteria(String criteria) {
		Game game = null;
		try {
			List<Game> games = queryForEq(Game.DB_FIELD_TO_BE_DEFINED, criteria);
			if(games != null && games.size() == 1){
				return games.get(0);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return game;
	}
	*/

	/*
	// Sample using JOIN
	@SuppressWarnings("unchecked")
	public List<Game> findAllForId( long id ) {
		
		List<Game> games = null;
		try {

			GameSubTableDao gameSubTableDao;
			gameSubTableDao = DatabaseManager.getInstance().getHelper().getGameSubTableDao();
			
			QueryBuilder<Game, Long> gameqb = queryBuilder();
			QueryBuilder<GameSubTable, Long> gameSubTableqb = gameSubTableDao.queryBuilder();
			gameSubTableqb.where().eq(GameSubTable.DB_FIELD_TO_BE_DEFINED, parent);

			gameqb.orderBy(Game.DB_FIELD_TO_BE_DEFINED, true);
			
			games = gameqb.join(postsCategoriesqb).query();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return games;
	}
	*/
					
	/*
	// Sample using LIKE
	@SuppressWarnings("unchecked")
	public List<Game> findByValueInAnyFieldUsingLike(String searchString) {
		List<Game> gameList = null;
		try {

			QueryBuilder<String, String> queryBuilder = queryBuilder();
			queryBuilder.where()
			.like(Game.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%")
			.or().like(Game.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%");
			
			PreparedQuery<String> preparedQuery = queryBuilder.prepare();

			gameList = query(preparedQuery);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return gameList;
	}
	*/
}