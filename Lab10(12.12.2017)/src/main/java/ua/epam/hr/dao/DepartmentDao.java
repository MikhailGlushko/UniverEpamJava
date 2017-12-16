package ua.epam.hr.dao;

import ua.epam.hr.entity.Department;

import java.sql.Connection;
import java.util.List;

public interface DepartmentDao {
    void add(Department department);
    void save(Department department);
    Department delete(int id);
    void deleteAll();
    Department getById(int id);
    Department getByName(String name);
    List<Department> getList();
    void setConnection(Connection connection);
    Connection getConnection();
}
