package com.ynov.android.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import com.ynov.android.model.Player;


public class PlayerDao extends BaseDaoImpl<Player, Long>{
	public PlayerDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Player.class);
	}
	
	public PlayerDao(ConnectionSource connectionSource, DatabaseTableConfig<Player> tableConfig) throws SQLException { 
		super(connectionSource, tableConfig);
	}

	public List<Player> findAll() {
		
		List<Player> players = new ArrayList<Player>();;
		try {
			players = queryForAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return players;
	}

	public Player findById(long id) {
		Player player = null;
		try {
			player = (Player) queryForId(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return player;
	}
	
	public void clear(){
		
		try {
			TableUtils.clearTable(getConnectionSource(), Player.class);
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
			QueryBuilder<Player, Long> playerqb = queryBuilder();
			playerqb.where().eq(Player.DB_FIELD_TO_BE_DEFINED, id);
			
			long nbreOfItems = countOf( playerqb.setCountOf(true).prepare() );
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
			QueryBuilder<Player, Long> playerqb = queryBuilder();

			Where where = playerqb.where();

			where.eq(Player.DB_FIELD_TO_BE_DEFINED, id);
			where.and();
			where.eq(Player.DB_FIELD_TO_BE_DEFINED, id);
			
			// setCountOf(true) : Set the flag saying that we should only return the count of the results.
			// This query can then be used by countOf(PreparedQuery). To get the count-of directly, use countOf().
			long nbreOfItems = countOf( playerqb.setCountOf(true).prepare() );
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
	public Player findByStringCriteria(String criteria) {
		Player player = null;
		try {
			List<Player> players = queryForEq(Player.DB_FIELD_TO_BE_DEFINED, criteria);
			if(players != null && players.size() == 1){
				return players.get(0);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return player;
	}
	*/

	/*
	// Sample using JOIN
	@SuppressWarnings("unchecked")
	public List<Player> findAllForId( long id ) {
		
		List<Player> players = null;
		try {

			PlayerSubTableDao playerSubTableDao;
			playerSubTableDao = DatabaseManager.getInstance().getHelper().getPlayerSubTableDao();
			
			QueryBuilder<Player, Long> playerqb = queryBuilder();
			QueryBuilder<PlayerSubTable, Long> playerSubTableqb = playerSubTableDao.queryBuilder();
			playerSubTableqb.where().eq(PlayerSubTable.DB_FIELD_TO_BE_DEFINED, parent);

			playerqb.orderBy(Player.DB_FIELD_TO_BE_DEFINED, true);
			
			players = playerqb.join(postsCategoriesqb).query();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return players;
	}
	*/
					
	/*
	// Sample using LIKE
	@SuppressWarnings("unchecked")
	public List<Player> findByValueInAnyFieldUsingLike(String searchString) {
		List<Player> playerList = null;
		try {

			QueryBuilder<String, String> queryBuilder = queryBuilder();
			queryBuilder.where()
			.like(Player.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%")
			.or().like(Player.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%");
			
			PreparedQuery<String> preparedQuery = queryBuilder.prepare();

			playerList = query(preparedQuery);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return playerList;
	}
	*/
}