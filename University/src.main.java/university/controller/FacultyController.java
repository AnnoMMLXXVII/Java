package university.controller;

import java.util.List;

import university.dao.FacultyDAO;
import university.pojo.Faculty;

public class FacultyController extends Controller<Faculty> {

	public FacultyController() {
		dao = new FacultyDAO();
	}

	@Override
	public void remove(Faculty t) {
		dao.removeById(t.getId());
	}

	@Override
	public void remove(List<Faculty> t) {
		t.forEach(e -> dao.removeById(e.getId()));
	}

}
