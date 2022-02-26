package university.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Faculty;

public class FacultyDAO extends DAO<Faculty> {

	public FacultyDAO() {
		table = DB_TABLES.faculty;
		fieldCount = 10;
		col_id = DB_COLUMNS.FacultyID;
		fields = new DB_COLUMNS[] { DB_COLUMNS.FirstName, DB_COLUMNS.LastName, DB_COLUMNS.Address,
				DB_COLUMNS.Position, DB_COLUMNS.Salary, DB_COLUMNS.Specialty, DB_COLUMNS.ZipCode,
				DB_COLUMNS.OfficeNumber, DB_COLUMNS.SectionID };
	}

	@Override
	public boolean update(Faculty object) {
		return update(object, object.getId());
	}

	@Override
	public Faculty getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new Faculty(Integer.parseInt(rs.getString(DB_COLUMNS.FacultyID.name())),
				rs.getString(DB_COLUMNS.FirstName.name()), rs.getString(DB_COLUMNS.LastName.name()),
				rs.getString(DB_COLUMNS.Address.name()), rs.getString(DB_COLUMNS.Position.name()),
				Integer.parseInt(rs.getString(DB_COLUMNS.Salary.name())), rs.getString(DB_COLUMNS.Specialty.name()),
				rs.getString(DB_COLUMNS.ZipCode.name()),
				Integer.parseInt(rs.getString(DB_COLUMNS.OfficeNumber.name())),
				Integer.parseInt(rs.getString(DB_COLUMNS.SectionID.name())));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, Faculty z) throws SQLException {
		ps.setString(1, z.getId() + "");
		ps.setString(2, z.getFirstName());
		ps.setString(3, z.getLastName());
		ps.setString(4, z.getAddress());
		ps.setString(5, z.getRank());
		ps.setString(6, z.getSalary() + "");
		ps.setString(7, z.getSpecialty() + "");
		ps.setString(8, z.getZipCode() + "");
		ps.setString(9, z.getOfficeNumber() + "");
		ps.setString(10, z.getSectionId() + "");

	}

}
