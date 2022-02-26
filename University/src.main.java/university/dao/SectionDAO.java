package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Section;

public class SectionDAO extends DAO<Section> {

	public SectionDAO() {
		table = DB_TABLES.sections;
		fieldCount = 6;
		col_id = DB_COLUMNS.SectionID;
		fields = new DB_COLUMNS[] { DB_COLUMNS.SectionNumber, DB_COLUMNS.Term, DB_COLUMNS.Year, DB_COLUMNS.CourseID,
				DB_COLUMNS.RoomNumber };
	}

	@Override
	public boolean update(Section object) {
		return update(object, object.getId());
	}

	@Override
	public Section getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new Section(Integer.parseInt(rs.getString(DB_COLUMNS.SectionID.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.SectionNumber.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.Term.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.Year.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.CourseID.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.RoomNumber.toString())));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, Section z) throws SQLException {
		try {
			ps.setString(1, z.getId() + "");
			ps.setString(2, z.getNumber() + "");
			ps.setString(3, z.getTerm() + "");
			ps.setString(4, z.getYear() + "");
			ps.setString(5, z.getCourseId() + "");
			ps.setString(6, z.getRoomNumber() + "");
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}
	}

}
