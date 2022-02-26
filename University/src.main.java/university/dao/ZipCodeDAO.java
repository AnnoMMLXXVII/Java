package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.ZipCode;

public class ZipCodeDAO extends DAO<ZipCode> {

	public ZipCodeDAO() {
		table = DB_TABLES.zipcodes;
		fieldCount = 3;
		col_id = DB_COLUMNS.ZipCode;
		fields = new DB_COLUMNS[] { DB_COLUMNS.City, DB_COLUMNS.StateAbbr };
	}

	@Override
	public boolean update(ZipCode object) {
		return update(object, object.getZipcode());
	}

	@Override
	public ZipCode getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new ZipCode(rs.getString(DB_COLUMNS.ZipCode.toString()), rs.getString(DB_COLUMNS.City.toString()),
				rs.getString(DB_COLUMNS.StateAbbr.toString()));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, ZipCode z) throws SQLException {
		try {
			ps.setString(1, z.getZipcode() + "");
			ps.setString(2, z.getCity());
			ps.setString(3, z.getStateAbbr());
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}

	}

}
