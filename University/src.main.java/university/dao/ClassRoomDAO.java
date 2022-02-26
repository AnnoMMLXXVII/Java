package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.ClassRoom;

public class ClassRoomDAO extends DAO<ClassRoom> {

	public ClassRoomDAO() {
		table = DB_TABLES.classrooms;
		fieldCount = 2;
		col_id = DB_COLUMNS.RoomNumber;
		fields = new DB_COLUMNS[] { DB_COLUMNS.RoomNumber, DB_COLUMNS.Occupancy };
	}

	@Override
	public boolean update(ClassRoom object) {
		return update(object, object.getRoomNumber());
	}

	@Override
	public ClassRoom getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new ClassRoom(Integer.parseInt(rs.getString(DB_COLUMNS.RoomNumber.toString())),
				Integer.parseInt(rs.getString(DB_COLUMNS.Occupancy.toString())));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, ClassRoom z) throws SQLException {
		try {
			ps.setString(1, z.getRoomNumber() + "");
			ps.setString(2, z.getOccupancy() + "");
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}
	}

}
