package university;

import static university.shared.Common.generateRandomDate;
import static university.shared.Common.generateRandomGrade;
import static university.shared.Common.generateRandomID;
import static university.shared.Common.generateRandomString;
import static university.shared.Common.getApplicationLogger;
import static university.shared.Common.setApplicationLogger;

import static university.shared.Constants.LOG_FILE.application_log;

import java.util.ArrayList;
import java.util.List;

import university.controller.BuildingController;
import university.controller.CampusController;
import university.controller.ClassRoomController;
import university.controller.Controller;
import university.controller.CourseController;
import university.controller.FacultyController;
import university.controller.FacultyOfficeController;
import university.controller.SectionController;
import university.controller.StudentController;
import university.controller.ZipCodeController;
import university.logger.ApplicationLogger;
import university.logger.Logs;
import university.pojo.Building;
import university.pojo.Campus;
import university.pojo.ClassRoom;
import university.pojo.Course;
import university.pojo.Faculty;
import university.pojo.FacultyOffice;
import university.pojo.Section;
import university.pojo.Student;
import university.pojo.ZipCode;

public class UniversityMain {

	private Logs<ApplicationLogger> applicationLogger;
	private static UniversityMain instance;

	public static void main(String[] args) {
		UniversityMain.getInstance();
		testZipCodeController();
		testCampusController();
		testCourseController();
		testClassRoomController();
		testBuildingsController();
		testFacultyOfficeController();
		testSectionController();
		testFacultyController();
		testStudentController();
	}

	public static UniversityMain getInstance() {
		if (instance == null) {
			synchronized (UniversityMain.class) {
				if (instance == null) {
					return new UniversityMain();
				}
			}
		}
		return instance;
	}

	public UniversityMain() {
		initializeLoggers();
	}

	/**
	 * Creates Activity Logger and Application Looger
	 */
	private void initializeLoggers() {
		applicationLogger = new ApplicationLogger(application_log.toString());
		setApplicationLogger(applicationLogger);
		getApplicationLogger().logINFO("\n--------Program initialized---------\n");
	}

	private static void testZipCodeController() {
		Controller<ZipCode> z = new ZipCodeController();
		List<ZipCode> zips = new ArrayList<>();
		int i = 0;
		StringBuilder sb;
		while (i < 100) {
			sb = new StringBuilder();
			sb.append(generateRandomID(5));
			sb.append("-");
			sb.append(generateRandomID(4));
			zips.add(new ZipCode(sb.toString(), generateRandomString(10) + " City",
					generateRandomString(2).toUpperCase()));
			i++;
		}
		zips.forEach(e -> System.out.println(e.toString()));
		z.create(zips);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(zips);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testCampusController() {
		Controller<Campus> z = new CampusController();
		List<Campus> camps = new ArrayList<>();
		int i = 0;
		while (i < 100) {
			camps.add(new Campus(generateRandomID(), generateRandomString(0)));
			i++;
		}
		camps.forEach(e -> System.out.println(e.toString()));
		z.create(camps);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(camps);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testCourseController() {
		Controller<Course> z = new CourseController();
		List<Course> courses = new ArrayList<>();
		int i = 0;
		while (i < 100) {
			courses.add(new Course(generateRandomID(), generateRandomString(4), generateRandomID(4),
					generateRandomString(16)));
			i++;
		}
		courses.forEach(e -> System.out.println(e.toString()));
		z.create(courses);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testClassRoomController() {
		Controller<ClassRoom> z = new ClassRoomController();
		List<ClassRoom> courses = new ArrayList<>();
		int i = 0;
		while (i < 100) {
			courses.add(new ClassRoom(generateRandomID(4), generateRandomID(3)));
			i++;
		}
		courses.forEach(e -> System.out.println(e.toString()));
		z.create(courses);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testBuildingsController() {
		Controller<Building> z = new BuildingController();
		Controller<ClassRoom> y = new ClassRoomController();
		Controller<ZipCode> x = new ZipCodeController();
		Controller<Campus> w = new CampusController();
		List<ZipCode> zips = x.getAll();
		List<ClassRoom> rooms = y.getAll();
		List<Campus> camps = w.getAll();

		List<Building> builds = new ArrayList<>();
		int i = 0;
		while (i < 30) {
			builds.add(new Building(generateRandomID(), generateRandomString(16), generateRandomString(24),
					camps.get(i % 2).getId(), zips.get(i % 2).getZipcode(), rooms.get(i % 2).getRoomNumber()));
			i++;
		}
		builds.forEach(e -> System.out.println(e.toString()));
		z.create(builds);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testFacultyOfficeController() {
		Controller<FacultyOffice> z = new FacultyOfficeController();
		Controller<Building> x = new BuildingController();
		List<Building> builds = x.getAll();

		List<FacultyOffice> offices = new ArrayList<>();
		int i = 0;
		while (i < 25) {
			offices.add(new FacultyOffice(generateRandomID(), builds.get(i % 2).getId()));
			i++;
		}
		offices.forEach(e -> System.out.println(e.toString()));
		z.create(offices);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testSectionController() {
		Controller<Section> z = new SectionController();
		Controller<Course> x = new CourseController();
		Controller<ClassRoom> y = new ClassRoomController();
		List<Course> courses = x.getAll();
		List<ClassRoom> rooms = y.getAll();

		List<Section> sections = new ArrayList<>();
		int i = 0;
		while (i < 35) {
			sections.add(new Section(generateRandomID(4), generateRandomID(8), generateRandomID(4), generateRandomID(4),
					courses.get(i % 2).getId(), rooms.get(i % 2).getRoomNumber()));
			i++;
		}
		sections.forEach(e -> System.out.println(e.toString()));
		z.create(sections);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testFacultyController() {
		Controller<Faculty> z = new FacultyController();
		Controller<FacultyOffice> y = new FacultyOfficeController();
		Controller<ZipCode> x = new ZipCodeController();
		Controller<Section> w = new SectionController();
		List<FacultyOffice> offices = y.getAll();
		List<ZipCode> zips = x.getAll();
		List<Section> sections = w.getAll();

		List<Faculty> faculties = new ArrayList<>();
		int i = 0;
		while (i < 24) {
			faculties.add(new Faculty(generateRandomID(), generateRandomString(12), generateRandomString(30),
					generateRandomString(28), generateRandomString(8), generateRandomID(5), generateRandomString(12),
					zips.get(i % 2).getZipcode(), offices.get(i % 2).getOfficeNumber(), sections.get(i % 2).getId()));
			i++;
		}
		faculties.forEach(e -> System.out.println(e.toString()));
		z.create(faculties);
		z.getAll().forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

	private static void testStudentController() {
		Controller<Student> z = new StudentController();
		Controller<Faculty> y = new FacultyController();
		Controller<ZipCode> x = new ZipCodeController();
		Controller<Section> w = new SectionController();
		List<Faculty> faculties = y.getAll();
		List<ZipCode> zips = x.getAll();
		List<Section> sections = w.getAll();
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");
		sections.forEach(e -> System.err.println(e.getId()));
		System.out.println("-------------------------------------------------");
		System.out.println("-------------------------------------------------");

		List<Student> students = new ArrayList<>();
		int i = 0;
		while (i < 20) {
			students.add(new Student(generateRandomID(), generateRandomString(12), generateRandomString(30),
					generateRandomString(28), generateRandomString(8), generateRandomDate(), generateRandomGrade(),
					generateRandomDate(), generateRandomDate(), zips.get(i).getZipcode(), faculties.get(i).getId(),
					sections.get(i%2).getId()));
			i++;
		}
		z.getAll().forEach(e -> System.out.println(e.toString().replaceAll("[", "(").replaceAll("]", ")")));
		z.create(students);
		students.forEach(e -> System.out.println(e.toString()));
//		z.remove(courses);
//		z.getAll().forEach(e -> System.out.println(e.toString()));
	}

}
