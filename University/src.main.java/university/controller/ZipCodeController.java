package university.controller;

import java.util.List;

import university.dao.ZipCodeDAO;
import university.pojo.ZipCode;

public class ZipCodeController extends Controller<ZipCode> {

	public ZipCodeController() {
		dao = new ZipCodeDAO();
	}

	@Override
	public void remove(ZipCode zipCode) {
		dao.removeById(zipCode.getZipcode());
	}

	@Override
	public void remove(List<ZipCode> zipCodes) {
		zipCodes.forEach(e -> dao.removeById(e.getZipcode()));
	}

}
