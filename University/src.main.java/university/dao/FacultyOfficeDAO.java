package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Building;
import university.pojo.FacultyOffice;

public class FacultyOfficeDAO extends DAO<FacultyOffice> {

	public FacultyOfficeDAO() {
		table = DB_TABLES.facultyoffices;
		fieldCount = 2;
		col_id = DB_COLUMNS.OfficeNumber;
		fields = new DB_COLUMNS[] { DB_COLUMNS.BuildingID };
	}

	@Override
	public boolean update(FacultyOffice object) {
		return update(object, object.getOfficeNumber());
	}

	@Override
	public FacultyOffice getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new FacultyOffice(Integer.parseInt(rs.getString(DB_COLUMNS.OfficeNumber.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.BuildingID.toString())));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, FacultyOffice z) throws SQLException {
		try {
			ps.setString(1, z.getOfficeNumber() + "");
			ps.setString(2, z.getBuildingId() + "");
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}

	}

}
