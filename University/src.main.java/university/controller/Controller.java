package university.controller;

import static university.shared.Common.getApplicationLogger;

import java.sql.SQLException;
import java.util.List;

import university.dao.DataAccessObject;
import university.jdbc.DatabaseCommon.DB_COLUMNS;

public abstract class Controller<T> {

	public DataAccessObject<T> dao;

	public List<T> getAll() {
		return dao.getAll();
	}

	public void create(T t) {
		if (!dao.create(t)) {
			getApplicationLogger().logWARN("Unable to Insert new Zip Code");
		}
	}

	public void create(List<T> t) {
		t.forEach(e -> {
			dao.create(e);
		});
	}

	public void remove(int t) {
		dao.removeById(t);
	}

	public abstract void remove(T t);

	public abstract void remove(List<T> t);

	public void remove(DB_COLUMNS k, String v) {
		try {
			dao.removeByCondition(k, v);
		} catch (SQLException e) {
			getApplicationLogger().logERROR("Unable to Remove By Condition: " + e.getMessage());
		}
	}

	public void remove(List<T> t, DB_COLUMNS k, String v) {
		t.forEach(e -> {
			try {
				dao.removeByCondition(k, v);
			} catch (SQLException ex) {
				getApplicationLogger().logERROR("Unable to Remove By Condition: " + ex.getMessage());
			}
		});
	}

	public void update(T t) {
		dao.update(t);
	}

	public void update(int t) {
		T object = dao.getById(t);
		dao.update(object);
	}

}
