package net.guides.springboot2.jdbc.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import net.guides.springboot2.jdbc.model.Employee;

@Repository
public class EmployeeJDBCRepository {
    @Autowired
    JdbcTemplate jdbcTemplate;

    class EmployeeRowMapper implements RowMapper<Employee> {

        public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
            Employee emp = new Employee();
            emp.setId(rs.getLong("id"));
            emp.setFirstName(rs.getString("first_name"));
            emp.setLastName(rs.getString("last_name"));
            emp.setEmailID(rs.getString("email_address"));
            return emp;
        } // endae mapRow
    } // endae CLASS EMPLOYEEROWMAPPER

    public List<Employee> findAll() {
        return jdbcTemplate.query("select * from employees", new EmployeeRowMapper());
    }

    public Optional<Employee> findById(long id) {
        return Optional.of(jdbcTemplate.queryForObject("select * from employees where id=?", new Object[]{
                        id
                }, new BeanPropertyRowMapper<Employee>(Employee.class)
        ));
    } // endae findById

    public int deleteById(long id) {
        return jdbcTemplate.update("delete from employees where id=?", new Object[]{
                id
        });
    }

    public int insert(Employee employee) {
        return jdbcTemplate.update("insert into employees (id, first_name, last_name, email_address) " + "values(?, ?, ?, ?)",
                new Object[]{
                        employee.getId(), employee.getFirstName(), employee.getLastName(), employee.getEmailID()
                });
    }

    public int update(Employee employee) {
        return jdbcTemplate.update("update employees " + " set first_name = ?, last_name = ?, email_address = ? " + " where id = ?",
                new Object[]{
                        employee.getFirstName(), employee.getLastName(), employee.getEmailID(), employee.getId()
                });
    }

}
