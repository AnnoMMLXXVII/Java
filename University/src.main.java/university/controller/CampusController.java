package university.controller;

import java.util.List;

import university.dao.CampusDAO;
import university.pojo.Campus;

public class CampusController extends Controller<Campus> {

	public CampusController() {
		dao = new CampusDAO();
	}

	@Override
	public void remove(Campus campus) {
		dao.removeById(campus.getId());
	}

	@Override
	public void remove(List<Campus> campuss) {
		campuss.forEach(e -> dao.removeById(e.getId()));
	}

}
