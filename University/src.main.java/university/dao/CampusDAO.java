package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Campus;

public class CampusDAO extends DAO<Campus> {

	public CampusDAO() {
		table = DB_TABLES.campuses;
		fieldCount = 2;
		col_id = DB_COLUMNS.CampusID;
		fields = new DB_COLUMNS[] { DB_COLUMNS.CampusName };
	}

	@Override
	public boolean update(Campus object) {
		return update(object, object.getId());
	}

	@Override
	public Campus getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new Campus(Integer.parseInt(rs.getString(DB_COLUMNS.CampusID.toString())),
				rs.getString(DB_COLUMNS.CampusName.toString()));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, Campus z) throws SQLException {
		try {
			ps.setString(1, z.getId() + "");
			ps.setString(2, z.getName());
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}
	}

}
