package ua.epam.hr.dao;

import ua.epam.hr.entity.Employee;

import java.sql.Connection;
import java.util.List;

public interface EmployeeDao {
    void add(Employee employee);
    void save(Employee employee);
    Employee delete(int id);
    void deleteAll();
    Employee getById(int id);
    Employee getByName(String name);
    List<Employee> getList();
    List<Employee> getList(int departmentId);
    void setConnection(Connection connection);
    Connection getConnection();
}
