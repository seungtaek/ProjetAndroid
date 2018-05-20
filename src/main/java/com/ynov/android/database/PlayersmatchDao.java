package com.ynov.android.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import com.ynov.android.model.Playersmatch;


public class PlayersmatchDao extends BaseDaoImpl<Playersmatch, Long>{
	public PlayersmatchDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Playersmatch.class);
	}
	
	public PlayersmatchDao(ConnectionSource connectionSource, DatabaseTableConfig<Playersmatch> tableConfig) throws SQLException { 
		super(connectionSource, tableConfig);
	}

	public List<Playersmatch> findAll() {
		
		List<Playersmatch> playersmatchs = new ArrayList<Playersmatch>();;
		try {
			playersmatchs = queryForAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return playersmatchs;
	}

	public Playersmatch findById(long id) {
		Playersmatch playersmatch = null;
		try {
			playersmatch = (Playersmatch) queryForId(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return playersmatch;
	}
	
	public void clear(){
		
		try {
			TableUtils.clearTable(getConnectionSource(), Playersmatch.class);
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
			QueryBuilder<Playersmatch, Long> playersmatchqb = queryBuilder();
			playersmatchqb.where().eq(Playersmatch.DB_FIELD_TO_BE_DEFINED, id);
			
			long nbreOfItems = countOf( playersmatchqb.setCountOf(true).prepare() );
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
			QueryBuilder<Playersmatch, Long> playersmatchqb = queryBuilder();

			Where where = playersmatchqb.where();

			where.eq(Playersmatch.DB_FIELD_TO_BE_DEFINED, id);
			where.and();
			where.eq(Playersmatch.DB_FIELD_TO_BE_DEFINED, id);
			
			// setCountOf(true) : Set the flag saying that we should only return the count of the results.
			// This query can then be used by countOf(PreparedQuery). To get the count-of directly, use countOf().
			long nbreOfItems = countOf( playersmatchqb.setCountOf(true).prepare() );
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
	public Playersmatch findByStringCriteria(String criteria) {
		Playersmatch playersmatch = null;
		try {
			List<Playersmatch> playersmatchs = queryForEq(Playersmatch.DB_FIELD_TO_BE_DEFINED, criteria);
			if(playersmatchs != null && playersmatchs.size() == 1){
				return playersmatchs.get(0);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return playersmatch;
	}
	*/

	/*
	// Sample using JOIN
	@SuppressWarnings("unchecked")
	public List<Playersmatch> findAllForId( long id ) {
		
		List<Playersmatch> playersmatchs = null;
		try {

			PlayersmatchSubTableDao playersmatchSubTableDao;
			playersmatchSubTableDao = DatabaseManager.getInstance().getHelper().getPlayersmatchSubTableDao();
			
			QueryBuilder<Playersmatch, Long> playersmatchqb = queryBuilder();
			QueryBuilder<PlayersmatchSubTable, Long> playersmatchSubTableqb = playersmatchSubTableDao.queryBuilder();
			playersmatchSubTableqb.where().eq(PlayersmatchSubTable.DB_FIELD_TO_BE_DEFINED, parent);

			playersmatchqb.orderBy(Playersmatch.DB_FIELD_TO_BE_DEFINED, true);
			
			playersmatchs = playersmatchqb.join(postsCategoriesqb).query();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return playersmatchs;
	}
	*/
					
	/*
	// Sample using LIKE
	@SuppressWarnings("unchecked")
	public List<Playersmatch> findByValueInAnyFieldUsingLike(String searchString) {
		List<Playersmatch> playersmatchList = null;
		try {

			QueryBuilder<String, String> queryBuilder = queryBuilder();
			queryBuilder.where()
			.like(Playersmatch.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%")
			.or().like(Playersmatch.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%");
			
			PreparedQuery<String> preparedQuery = queryBuilder.prepare();

			playersmatchList = query(preparedQuery);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return playersmatchList;
	}
	*/
}