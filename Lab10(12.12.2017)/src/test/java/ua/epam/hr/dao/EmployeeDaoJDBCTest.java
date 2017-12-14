package ua.epam.hr.dao;

import org.junit.Before;
import org.junit.Test;
import ua.epam.hr.entity.Employee;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeDaoJDBCTest {

    JDBC jdbc;
    Employee employee;
    EmployeeDaoJDBC employeeDaoJDBC;

    @Before
    public void init(){
        jdbc = new JDBC();
        employee = new Employee();
        employeeDaoJDBC = new EmployeeDaoJDBC();
        employeeDaoJDBC.setConnection(jdbc.getConnection());
    }

    @Test
    public void getConnection() {
        Connection connection = employeeDaoJDBC.getConnection();
    }

    @Test
    public void save() {
        Employee e1 = new Employee();
        e1.setDepartmentId(5);
        e1.setName("Employee 4");
        System.out.println(e1);
        employeeDaoJDBC.save(e1);
        System.out.println(e1);
    }

    @Test
    public void delete() {
        Employee employee = employeeDaoJDBC.delete(4);
        System.out.println(employee);
    }

    @Test
    public void getById() {
        Employee employee = employeeDaoJDBC.getById(9);
        System.out.println(employee);
    }

    @Test
    public void getByName() {
        Employee employee = employeeDaoJDBC.getByName("Employee 1");
        System.out.println(employee);
    }

    @Test
    public void getList() {
        List<Employee> list = employeeDaoJDBC.getList();
        System.out.println(list);
    }
}