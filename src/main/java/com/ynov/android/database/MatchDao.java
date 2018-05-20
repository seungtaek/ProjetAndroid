package com.ynov.android.database;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.j256.ormlite.dao.BaseDaoImpl;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.DatabaseTableConfig;
import com.j256.ormlite.table.TableUtils;

import com.ynov.android.model.Match;


public class MatchDao extends BaseDaoImpl<Match, Long>{
	public MatchDao(ConnectionSource connectionSource) throws SQLException {
		super(connectionSource, Match.class);
	}
	
	public MatchDao(ConnectionSource connectionSource, DatabaseTableConfig<Match> tableConfig) throws SQLException { 
		super(connectionSource, tableConfig);
	}

	public List<Match> findAll() {
		
		List<Match> matchs = new ArrayList<Match>();;
		try {
			matchs = queryForAll();
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return matchs;
	}

	public Match findById(long id) {
		Match match = null;
		try {
			match = (Match) queryForId(id);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return match;
	}
	
	public void clear(){
		
		try {
			TableUtils.clearTable(getConnectionSource(), Match.class);
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
			QueryBuilder<Match, Long> matchqb = queryBuilder();
			matchqb.where().eq(Match.DB_FIELD_TO_BE_DEFINED, id);
			
			long nbreOfItems = countOf( matchqb.setCountOf(true).prepare() );
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
			QueryBuilder<Match, Long> matchqb = queryBuilder();

			Where where = matchqb.where();

			where.eq(Match.DB_FIELD_TO_BE_DEFINED, id);
			where.and();
			where.eq(Match.DB_FIELD_TO_BE_DEFINED, id);
			
			// setCountOf(true) : Set the flag saying that we should only return the count of the results.
			// This query can then be used by countOf(PreparedQuery). To get the count-of directly, use countOf().
			long nbreOfItems = countOf( matchqb.setCountOf(true).prepare() );
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
	public Match findByStringCriteria(String criteria) {
		Match match = null;
		try {
			List<Match> matchs = queryForEq(Match.DB_FIELD_TO_BE_DEFINED, criteria);
			if(matchs != null && matchs.size() == 1){
				return matchs.get(0);
			}
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return match;
	}
	*/

	/*
	// Sample using JOIN
	@SuppressWarnings("unchecked")
	public List<Match> findAllForId( long id ) {
		
		List<Match> matchs = null;
		try {

			MatchSubTableDao matchSubTableDao;
			matchSubTableDao = DatabaseManager.getInstance().getHelper().getMatchSubTableDao();
			
			QueryBuilder<Match, Long> matchqb = queryBuilder();
			QueryBuilder<MatchSubTable, Long> matchSubTableqb = matchSubTableDao.queryBuilder();
			matchSubTableqb.where().eq(MatchSubTable.DB_FIELD_TO_BE_DEFINED, parent);

			matchqb.orderBy(Match.DB_FIELD_TO_BE_DEFINED, true);
			
			matchs = matchqb.join(postsCategoriesqb).query();
			
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return matchs;
	}
	*/
					
	/*
	// Sample using LIKE
	@SuppressWarnings("unchecked")
	public List<Match> findByValueInAnyFieldUsingLike(String searchString) {
		List<Match> matchList = null;
		try {

			QueryBuilder<String, String> queryBuilder = queryBuilder();
			queryBuilder.where()
			.like(Match.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%")
			.or().like(Match.DB_FIELD_TO_BE_DEFINED, "%" + searchString + "%");
			
			PreparedQuery<String> preparedQuery = queryBuilder.prepare();

			matchList = query(preparedQuery);
		}catch(SQLException e) {
			e.printStackTrace();
		}
		return matchList;
	}
	*/
}