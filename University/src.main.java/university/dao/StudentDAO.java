package university.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Student;

public class StudentDAO extends DAO<Student> {

	public StudentDAO() {
		table = DB_TABLES.students;
		fieldCount = 12;
		col_id = DB_COLUMNS.StudentID;
		composite_id = new DB_COLUMNS[] { DB_COLUMNS.StudentID, DB_COLUMNS.SectionID };
		fields = new DB_COLUMNS[] { DB_COLUMNS.FirstName, DB_COLUMNS.LastName, DB_COLUMNS.Address, DB_COLUMNS.Phone,
				DB_COLUMNS.Birthdate, DB_COLUMNS.ZipCode, DB_COLUMNS.FacultyID, DB_COLUMNS.Grade, DB_COLUMNS.StartDate,
				DB_COLUMNS.EndDate };
	}

	@Override
	public boolean update(Student object) {
		return update(object, object.getId());
	}

	@Override
	public Student getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new Student(Integer.parseInt(rs.getString(DB_COLUMNS.StudentID.name())),
				rs.getString(DB_COLUMNS.FirstName.name()), rs.getString(DB_COLUMNS.LastName.name()),
				rs.getString(DB_COLUMNS.Address.name()), rs.getString(DB_COLUMNS.Phone.name()),
				rs.getDate(DB_COLUMNS.Birthdate.name()), rs.getString(DB_COLUMNS.Grade.name()),
				rs.getDate(DB_COLUMNS.StartDate.name()), rs.getDate(DB_COLUMNS.EndDate.name()),
				rs.getString(DB_COLUMNS.ZipCode.name()), Integer.parseInt(rs.getString(DB_COLUMNS.FacultyID.name())),
				Integer.parseInt(rs.getString(DB_COLUMNS.SectionID.name())));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, Student z) throws SQLException {
		ps.setString(1, z.getId() + "");
		ps.setString(2, z.getSectionId()+"");
		ps.setString(3, z.getFirstName());
		ps.setString(4, z.getLastName());
		ps.setString(5, z.getAddress());
		ps.setString(6, z.getPhone());
		ps.setString(7, z.getBirthdate() + "");
		ps.setString(8, z.getGrade() + "");
		ps.setString(9, z.getStartDate() + "");
		ps.setString(10, z.getEndDate() + "");
		ps.setString(11, z.getZipCode() + "");
		ps.setString(12, z.getFacultyId() + "");
	}

}
