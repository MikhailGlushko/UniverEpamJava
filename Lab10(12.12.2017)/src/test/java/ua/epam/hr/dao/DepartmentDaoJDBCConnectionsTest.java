package ua.epam.hr.dao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import ua.epam.hr.entity.Department;

import java.sql.Connection;
import java.util.List;

import static org.junit.Assert.*;

public class DepartmentDaoJDBCConnectionsTest {

    JDBCConnections jdbcConnections;
    Department department;
    DepartmentDaoJDBC departmentDaoJDBC;

    @Before
    public void init(){
        jdbcConnections = new JDBCConnections();
        department = new Department();
        departmentDaoJDBC = new DepartmentDaoJDBC();
        departmentDaoJDBC.setConnection(jdbcConnections.getConnection());
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
    public void addNewDepartmentWhereExistOneWithSameName() {
        Department d1 = new Department();
        d1.setName("Отдел 6");
        departmentDaoJDBC.add(d1);
        departmentDaoJDBC.add(d1);
    }

    @Test
    public void addNewDepartment() {
        Department d1 = new Department();
        d1.setName("Отдел "+(int)(Math.random()*10+10));
        departmentDaoJDBC.add(d1);
        assertNotEquals(0,d1.getId());
    }

    @Test
    public void deleteNoExistDepartment() {
        Department delete = departmentDaoJDBC.delete(2);
        assertNull(delete);
    }

    @Test
    public void deleteExistDepartment() {
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        Department delete = departmentDaoJDBC.delete(d1.getId());
        assertNotNull(delete);
    }


    @Test
    public void getExistDepartmentById() {
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        Department department = departmentDaoJDBC.getById(d1.getId());
        assertNotNull(department);
    }

    @Test
    public void getNoExistDepartmentById() {
        Department department = departmentDaoJDBC.getById(1);
        assertNull(department);
    }


    @Test
    public void getExistDepartmentByName() {
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        Department department = departmentDaoJDBC.getByName("Отдел 00");
        assertNotNull(department);
    }

    @Test
    public void getNoExistDepartmentByName() {
        Department department = departmentDaoJDBC.getByName("Отдел 000");
        assertNull(department);
    }


    @Test
    public void getDepartmentList() {
        List<Department> list = departmentDaoJDBC.getList();
        assertNotNull(list);
    }

    @Test
    public void deleteAllDepartment(){
        departmentDaoJDBC.deleteAll();
        List<Department> listBeAfter = departmentDaoJDBC.getList();
        assertEquals(0,listBeAfter.size());
    }

    @Test
    public void saveExistDepartment(){
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        d1.setName("Отдел 11");
        departmentDaoJDBC.save(d1);
        Department tmp = departmentDaoJDBC.getByName("Отдел 11");
        assertNotNull(tmp);
    }

    @Test
    public void saveNoExistDepartment(){
        Department d1 = new Department();
        d1.setName("Отдел 00");
        departmentDaoJDBC.add(d1);
        d1.setName("Отдел 11");
        d1.setId(1);
        departmentDaoJDBC.save(d1);
        Department tmp = departmentDaoJDBC.getByName("Отдел 11");
        assertNull(tmp);
    }
}