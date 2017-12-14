package ua.epam.hr;

import ua.epam.hr.dao.DepartmentDaoJDBC;
import ua.epam.hr.dao.EmployeeDaoJDBC;
import ua.epam.hr.dao.JDBC;
import ua.epam.hr.entity.Department;
import ua.epam.hr.entity.Employee;

import java.util.List;

public class HRdemo {

    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        DepartmentDaoJDBC departmentDaoJDBC = new DepartmentDaoJDBC();
        departmentDaoJDBC.setConnection(jdbc.getConnection());
        EmployeeDaoJDBC employeeDaoJDBC = new EmployeeDaoJDBC();
        employeeDaoJDBC.setConnection(jdbc.getConnection());
        List<Department> departments = departmentDaoJDBC.getList();
        for (Department department:departments){
            int departmentId = department.getId();
            System.out.println(department);
            List<Employee> employees = employeeDaoJDBC.getList(departmentId);
            System.out.println(employees);
        }
    }
}
