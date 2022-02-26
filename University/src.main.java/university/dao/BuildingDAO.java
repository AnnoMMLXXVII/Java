package university.dao;

import static university.shared.Common.getApplicationLogger;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import university.jdbc.DatabaseCommon.DB_COLUMNS;
import university.jdbc.DatabaseCommon.DB_TABLES;
import university.pojo.Building;

public class BuildingDAO extends DAO<Building> {

	public BuildingDAO() {
		table = DB_TABLES.buildings;
		fieldCount = 6;
		col_id = DB_COLUMNS.BuildingID;
		fields = new DB_COLUMNS[] { DB_COLUMNS.Address, DB_COLUMNS.BuildingName, DB_COLUMNS.CampusID,
				DB_COLUMNS.ZipCode, DB_COLUMNS.RoomNumber };
	}
	
	@Override
	public boolean update(Building object) {
		return update(object, object.getId());
	}

	@Override
	public Building getAllColumnsUsingResultSet(ResultSet rs) throws SQLException {
		return new Building(Integer.parseInt(rs.getString(DB_COLUMNS.BuildingID.toString())),
				rs.getString(DB_COLUMNS.Address.toString()), rs.getString(DB_COLUMNS.BuildingName.toString()),
				Integer.parseInt(rs.getString(DB_COLUMNS.CampusID.toString())),
				rs.getString(DB_COLUMNS.ZipCode.toString()),
				Integer.parseInt(rs.getString(DB_COLUMNS.RoomNumber.toString())));
	}

	@Override
	public void executeModificationQuery(PreparedStatement ps, Building z) throws SQLException {
		try {
			ps.setString(1, z.getId() + "");
			ps.setString(2, z.getAddress());
			ps.setString(3, z.getName());
			ps.setString(4, z.getCampusId() + "");
			ps.setString(5, z.getZipCode() + "");
			ps.setString(6, z.getRoomNumber() + "");
		} catch (SQLException ex) {
			getApplicationLogger().logERROR("Unable to Perform Modifcation: " + ex.getMessage());
		}
	}

}
