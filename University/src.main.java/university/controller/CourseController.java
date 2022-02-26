package university.controller;

import java.util.List;

import university.dao.CourseDAO;
import university.pojo.Course;

public class CourseController extends Controller<Course> {

	public CourseController() {
		dao = new CourseDAO();
	}

	@Override
	public void remove(Course t) {
		dao.removeById(t.getId());
	}

	@Override
	public void remove(List<Course> t) {
		t.forEach(e -> dao.removeById(e.getId()));
	}

}
