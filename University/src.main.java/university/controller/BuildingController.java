package university.controller;

import java.util.List;

import university.dao.BuildingDAO;
import university.pojo.Building;

public class BuildingController extends Controller<Building> {

	public BuildingController() {
		dao = new BuildingDAO();
	}

	@Override
	public void remove(Building t) {
		dao.removeById(t.getId());

	}

	@Override
	public void remove(List<Building> t) {
		t.forEach(e -> dao.removeById(e.getId()));
	}

}
