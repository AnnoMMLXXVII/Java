package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Course;

public class CourseDAO extends DAO<Course> {

	public CourseDAO() {
		table = DB_TABLES.courses;
		fieldCount = 4;
		col_id = DB_COLUMNS.CourseID;
		fields = new DB_COLUMNS[] { DB_COLUMNS.CoursePrefix, DB_COLUMNS.CourseNumber, DB_COLUMNS.CourseName };
	}

	@Override
	public boolean update(Course object) {
		return update(object, object.getId());
	}

	@Override
	public Course getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new Course(Integer.parseInt(rs.getString(DB_COLUMNS.CourseID.toString())),
				rs.getString(DB_COLUMNS.CoursePrefix.toString()),
				Integer.parseInt(rs.getString(DB_COLUMNS.CourseNumber.toString())),
				rs.getString(DB_COLUMNS.CourseName.toString()));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, Course z) throws SQLException {
		try {
			ps.setString(1, z.getId() + "");
			ps.setString(2, z.getPrefix());
			ps.setString(3, z.getNumber() + "");
			ps.setString(4, z.getName());
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}
	}

}
