package university.dao;

import static university.jdbc.DatabaseQueries.createDeleteQueryByCondition;
import static university.jdbc.DatabaseQueries.createInsertQuery;
import static university.jdbc.DatabaseQueries.createUpdateQuery;
import static university.jdbc.DatabaseQueries.queryAll;
import static university.jdbc.DatabaseQueries.queryAllByCondition;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.jdbc.JDBCManager;

public abstract class DAO<T> implements DataAccessObject<T> {

	private T t;
	private List<T> ts;
	protected ResultSet rs;
	protected boolean isAddAction = false;
	protected PreparedStatement ps;
	protected DB_TABLES table;
	protected DB_COLUMNS col_id;
	protected DB_COLUMNS[] composite_id;
	protected int fieldCount = 0;
	protected DB_COLUMNS[] fields;

	@Override
	public List<T> getAll() {
		JDBCManager.openConnection();
		ts = new ArrayList<>();
		try {
			JDBCManager.makePreparedStatement(queryAll(table.name()), JDBCManager.getConnection());
			rs = JDBCManager.getPreparedStatement().executeQuery();
			while (rs.next()) {
				ts.add(getAllColumnsUsingResultSet(rs));
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} catch (NullPointerException e) {
			getApplicationLogger().logERROR("NULL EXCEPTION" + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return ts;
	}

	@Override
	public T getById(int id) {
		JDBCManager.openConnection();
		try {
			JDBCManager.makePreparedStatement(
					queryAllByCondition(table.name(), this.col_id.name(), String.format("%s", id)),
					JDBCManager.getConnection());
			rs = JDBCManager.getPreparedStatement().executeQuery();
			while (rs.next()) {
				t = getAllColumnsUsingResultSet(rs);
				if (t == null) {
					getApplicationLogger().logERROR("NULL EXCEPTION Using ID " + id);
					throw new NullPointerException("Could not retrieve Appointment By ID : " + id);
				}
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return t;
	}

	@Override
	public boolean create(T object) {
		JDBCManager.openConnection();
		try {
			JDBCManager.makePreparedStatement(createInsertQuery(this.table.name(), this.fieldCount),
					JDBCManager.getConnection());
			ps = JDBCManager.getPreparedStatement();
			isAddAction = true;
			executeModificationQuery(ps, object);
			ps.execute();
			if (ps.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return false;
	}

	@Override
	public abstract boolean update(T object);

	@Override
	public boolean removeById(int id) {
		JDBCManager.openConnection();
		try {
			JDBCManager.makePreparedStatement(
					createDeleteQueryByCondition(this.table.name(), this.col_id.name(), id + ""),
					JDBCManager.getConnection());
			ps = JDBCManager.getPreparedStatement();
			ps.execute();
			if (ps.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return false;
	}

	@Override
	public boolean removeById(String id) {
		JDBCManager.openConnection();
		try {
			JDBCManager.makePreparedStatement(
					createDeleteQueryByCondition(this.table.name(), this.col_id.name(), id + ""),
					JDBCManager.getConnection());
			ps = JDBCManager.getPreparedStatement();
			ps.execute();
			if (ps.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return false;
	}

	public boolean removeByCondition(DB_COLUMNS column, String value) {
		JDBCManager.openConnection();
		try {
			JDBCManager.makePreparedStatement(
					createDeleteQueryByCondition(this.table.name(), column.name(), value.toString() + ""),
					JDBCManager.getConnection());
			ps = JDBCManager.getPreparedStatement();
			ps.execute();
			if (ps.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return false;
	}

	@Override
	public abstract T getAllColumnsUsingResultSet(ResultSet rs) throws SQLException;

	@Override
	public abstract void executeModificationQuery(PreparedStatement ps, T z) throws SQLException;

	protected boolean update(T t, int object_id) {
		JDBCManager.openConnection();
		try {
			isAddAction = false;
			JDBCManager.makePreparedStatement(createUpdateQuery(table.name(), col_id.name(), object_id + "", fields),
					JDBCManager.getConnection());
			ps = JDBCManager.getPreparedStatement();
			executeModificationQuery(ps, t);
			ps.execute();
			if (ps.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return false;
	}

	protected boolean update(T t, String object_id) {
		JDBCManager.openConnection();
		try {
			isAddAction = false;
			JDBCManager.makePreparedStatement(createUpdateQuery(table.name(), col_id.name(), object_id + "", fields),
					JDBCManager.getConnection());
			ps = JDBCManager.getPreparedStatement();
			executeModificationQuery(ps, t);
			ps.execute();
			if (ps.getUpdateCount() > 0) {
				return true;
			}
		} catch (SQLException e) {
			getApplicationLogger().logERROR("SQL EXCEPTION : " + e.getMessage());
		} finally {
			JDBCManager.closeConnection();
		}
		return false;
	}

}
