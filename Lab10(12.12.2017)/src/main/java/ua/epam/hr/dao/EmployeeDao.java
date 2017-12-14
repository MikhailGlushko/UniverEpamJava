package ua.epam.hr.dao;

import ua.epam.hr.entity.Employee;

import java.util.List;

public interface EmployeeDao {
    void save(Employee employee);
    Employee delete(int id);
    Employee getById(int id);
    Employee getByName(String name);
    List<Employee> getList();
    List<Employee> getList(int departmentId);
}
