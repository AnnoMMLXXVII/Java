package dao;

import controller.CustomerController;
import shared.DataAccessObject;

import java.util.List;
import java.util.Optional;

public class CustomerDAO implements DataAccessObject<CustomerController> {

    @Override
    public Optional<List<CustomerController>> getAll() {
        return Optional.empty();
    }

    @Override
    public Optional<CustomerController> getById(int id) {
        return Optional.empty();
    }

    @Override
    public boolean create(CustomerController object) {
        return false;
    }

    @Override
    public boolean update(CustomerController object) {
        return false;
    }

    @Override
    public boolean remove(CustomerController object) {
        return false;
    }

    @Override
    public boolean removeById(int id) {
        return false;
    }
}
