package ua.epam.hr.dao;

import org.junit.Before;
import org.junit.Test;
import ua.epam.hr.entity.Department;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDaoJDBCTest {

    JDBC jdbc;
    Department department;
    DepartmentDaoJDBC departmentDaoJDBC;

    @Before
    public void init(){
        jdbc = new JDBC();
        department = new Department();
        departmentDaoJDBC = new DepartmentDaoJDBC();
        departmentDaoJDBC.setConnection(jdbc.getConnection());
    }

    @Test
    public void getConnection() {
        Connection connection = departmentDaoJDBC.getConnection();
    }

    @Test
    public void setConnection() {
    }

    @Test
    public void save() {
        Department d1 = new Department();
        d1.setName("Отдел 6");
        System.out.println(d1);
        departmentDaoJDBC.save(d1);
        System.out.println(d1);
    }

    @Test
    public void delete() {
        Department delete = departmentDaoJDBC.delete(2);
        System.out.println(delete);
    }

    @Test
    public void getById() {
        Department department = departmentDaoJDBC.getById(4);
        System.out.println(department);
    }

    @Test
    public void getByname() {
        Department department = departmentDaoJDBC.getByname("Отдел 2");
        System.out.println(department);
    }

    @Test
    public void getList() {
        List<Department> list = departmentDaoJDBC.getList();
        System.out.println(list);
    }
}