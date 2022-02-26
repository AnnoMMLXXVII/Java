package university.jdbc;

public class DatabaseCommon {
	public static enum DB_TABLES {
		buildings, campuses, classrooms, courses, faculty, facultyoffices, sections, students, zipcodes
	}

	public static enum DB_COLUMNS {
		ZipCode, City, StateAbbr, CampusID, CampusName, CoursePrefix, CourseNumber, CourseName, RoomNumber, Occupancy,
		BuildingID, Address, BuildingName, OfficeNumber, SectionID, SectionNumber, Term, Year, CourseID, FacultyID,
		FirstName, LastName, Position, Salary, Specialty, StudentID, Phone, Birthdate, Grade, StartDate, EndDate,

	}
}
