package ua.epam.hr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.epam.hr.entity.Department;
import ua.epam.hr.entity.Employee;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class EmployeeDaoJDBCConnectionsTest {

    private JDBCConnections jdbcConnections;
    private Employee employee;
    private EmployeeDao employeeDaoJDBC;
    private Department department;
    private DepartmentDao departmentDaoJDBC;
    private int departmentId;

    @Before
    public void init(){
        jdbcConnections = new JDBCConnections();
        employee = new Employee();
        employeeDaoJDBC = new EmployeeDaoJDBC();
        employeeDaoJDBC.setConnection(jdbcConnections.getConnection());

        department = new Department("Тестовий відділ");
        departmentDaoJDBC = new DepartmentDaoJDBC();
        departmentDaoJDBC.setConnection(jdbcConnections.getConnection());
        departmentDaoJDBC.add(department);
        departmentId = department.getId();
    }

    @After
    public void clear(){
        employeeDaoJDBC.deleteAll();
        departmentDaoJDBC.deleteAll();
    }

    @Test
    public void getConnection() {
        Connection connection = employeeDaoJDBC.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void addEmployeeWhenExistOneWithSameName() {
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 4");
        employeeDaoJDBC.add(e1);
        employeeDaoJDBC.add(e1);
    }

    @Test
    public void addNewEmployee() {
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 00");
        employeeDaoJDBC.add(e1);
    }

    @Test
    public void saveEmployeeWithWrongDepartmentId() {
        Employee e1 = new Employee();
        e1.setDepartmentId(1);
        e1.setName("Employee 00");
        employeeDaoJDBC.add(e1);
    }

    @Test
    public void deleteNoExistEmployee() {
        Employee employee = employeeDaoJDBC.delete(4);
        assertNull(employee);
    }

    @Test
    public void deleteExistEmployee() {
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 0");
        employeeDaoJDBC.add(e1);
        Employee employee = employeeDaoJDBC.delete(e1.getId());
        assertNotNull(employee);
    }


    @Test
    public void getExistEmployeeById() {
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 0");
        employeeDaoJDBC.add(e1);

        Employee employee = employeeDaoJDBC.getById(e1.getId());
        assertNotNull(employee);
    }

    @Test
    public void getNoExistEmployeeById() {
        Employee employee = employeeDaoJDBC.getById(1);
        assertNull(employee);
    }

    @Test
    public void getExistEmployeeByName() {
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 2");
        employeeDaoJDBC.add(e1);

        Employee employee = employeeDaoJDBC.getByName("Employee 2");
        assertNotNull(employee);
    }

    @Test
    public void getNoExistEmployeeByName() {
        Employee employee = employeeDaoJDBC.getByName("Employee 1");
        assertNull(employee);
    }


    @Test
    public void getEmployeeList() {
        List<Employee> list = employeeDaoJDBC.getList();
       assertNotNull(list);
    }

    @Test
    public void deleteAllEmployee(){
        employeeDaoJDBC.deleteAll();
        List<Employee> listAfter = employeeDaoJDBC.getList();
        assertEquals(0,listAfter.size());
    }

    @Test
    public void saveExistEmployee(){
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 4");
        employeeDaoJDBC.add(e1);
        e1.setName("Employee 44");
        employeeDaoJDBC.save(e1);
        Employee tmp = employeeDaoJDBC.getByName("Employee 44");
        assertNotNull(tmp);
    }

    @Test
    public void saveNoExistEmployee(){
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 4");
        employeeDaoJDBC.add(e1);
        e1.setName("Employee 44");
        e1.setId(1);
        employeeDaoJDBC.save(e1);
        Employee tmp = employeeDaoJDBC.getByName("Employee 44");
        assertNull(tmp);
    }

    @Test
    public void saveEmployeeWithWrongDepartmentID(){
        Employee e1 = new Employee();
        e1.setDepartmentId(departmentId);
        e1.setName("Employee 4");
        employeeDaoJDBC.add(e1);
        e1.setName("Employee 44");
        e1.setDepartmentId(1);
        employeeDaoJDBC.save(e1);
        Employee tmp = employeeDaoJDBC.getByName("Employee 44");
        assertNull(tmp);
    }
}