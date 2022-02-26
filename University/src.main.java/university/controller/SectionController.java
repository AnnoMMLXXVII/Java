package university.controller;

import java.util.List;

import university.dao.SectionDAO;
import university.pojo.Section;

public class SectionController extends Controller<Section> {

	public SectionController() {
		dao = new SectionDAO();
	}

	@Override
	public void remove(Section t) {
		dao.removeById(t.getId());
	}

	@Override
	public void remove(List<Section> t) {
		t.forEach(e -> dao.removeById(e.getId()));
	}

}
