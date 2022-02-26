package university.controller;

import java.util.List;

import university.dao.FacultyDAO;
import university.dao.StudentDAO;
import university.pojo.Faculty;
import university.pojo.Student;

public class StudentController extends Controller<Student> {

	public StudentController() {
		dao = new StudentDAO();
	}

	@Override
	public void remove(Student t) {
		dao.removeById(t.getId());
	}

	@Override
	public void remove(List<Student> t) {
		t.forEach(e -> dao.removeById(e.getId()));
	}

}
