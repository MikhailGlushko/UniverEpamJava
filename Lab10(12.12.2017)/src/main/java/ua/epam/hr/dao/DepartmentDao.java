package ua.epam.hr.dao;

import ua.epam.hr.entity.Department;

import java.util.List;

public interface DepartmentDao {
    void save(Department department);
    Department delete(int id);
    Department getById(int id);
    Department getByname(String name);
    List<Department> getList();
}
