package university.controller;

import java.util.List;

import university.dao.ClassRoomDAO;
import university.pojo.ClassRoom;

public class ClassRoomController extends Controller<ClassRoom> {

	public ClassRoomController() {
		dao = new ClassRoomDAO();
	}

	@Override
	public void remove(ClassRoom t) {
		dao.removeById(t.getRoomNumber());
	}

	@Override
	public void remove(List<ClassRoom> t) {
		t.forEach(e -> dao.removeById(e.getRoomNumber()));
	}

}
