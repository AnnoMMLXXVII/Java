package university.controller;

import java.util.List;

import university.dao.FacultyOfficeDAO;
import university.pojo.FacultyOffice;

public class FacultyOfficeController extends Controller<FacultyOffice> {

	public FacultyOfficeController() {
		dao = new FacultyOfficeDAO();
	}

	@Override
	public void remove(FacultyOffice t) {
		dao.removeById(t.getOfficeNumber());
	}

	@Override
	public void remove(List<FacultyOffice> t) {
		t.forEach(e -> dao.removeById(e.getOfficeNumber()));
	}

}
