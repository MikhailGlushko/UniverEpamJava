package ua.epam.hr.dao;

import org.junit.After;
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

    @After
    public void clear(){
        departmentDaoJDBC.deleteAll();
    }


    @Test
    public void getConnection() {
        Connection connection = departmentDaoJDBC.getConnection();
        assertNotNull(connection);
    }

    @Test
    public void addOld() {
        Department d1 = new Department();
        d1.setName("Отдел 6");
        departmentDaoJDBC.add(d1);
        departmentDaoJDBC.add(d1);
    }

    @Test
    public void addNew() {
        Department d1 = new Department();
        d1.setName("Отдел "+(int)(Math.random()*10+10));
        departmentDaoJDBC.add(d1);
        assertNotEquals(0,d1.getId());
    }

    @Test
    public void deleteNoExist() {
        Department delete = departmentDaoJDBC.delete(2);
        assertNull(delete);
    }

    @Test
    public void deleteExist() {
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        Department delete = departmentDaoJDBC.delete(d1.getId());
        assertNotNull(delete);
    }


    @Test
    public void getByIdExist() {
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        Department department = departmentDaoJDBC.getById(d1.getId());
        assertNotNull(department);
    }

    @Test
    public void getByIdNoExist() {
        Department department = departmentDaoJDBC.getById(1);
        assertNull(department);
    }


    @Test
    public void getBynameExist() {
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        Department department = departmentDaoJDBC.getByname("Отдел 00");
        assertNotNull(department);
    }

    @Test
    public void getBynameNoExist() {
        Department department = departmentDaoJDBC.getByname("Отдел 000");
        assertNull(department);
    }


    @Test
    public void getList() {
        List<Department> list = departmentDaoJDBC.getList();
        assertNotNull(list);
    }

    @Test
    public void deleteAll(){
        departmentDaoJDBC.deleteAll();
        List<Department> listBeAfter = departmentDaoJDBC.getList();
        assertEquals(0,listBeAfter.size());
    }

    @Test
    public void saveExist(){
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        d1.setName("Отдел 11");
        departmentDaoJDBC.save(d1);
        Department tmp = departmentDaoJDBC.getByname("Отдел 11");
        assertNotNull(tmp);
    }

    @Test
    public void saveNew(){
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        d1.setName("Отдел 11");
        d1.setId(1);
        departmentDaoJDBC.save(d1);
        Department tmp = departmentDaoJDBC.getByname("Отдел 11");
        assertNull(tmp);
    }
}