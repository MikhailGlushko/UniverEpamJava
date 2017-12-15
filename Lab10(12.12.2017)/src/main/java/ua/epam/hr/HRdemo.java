package ua.epam.hr;

import ua.epam.hr.dao.DepartmentDaoJDBC;
import ua.epam.hr.dao.EmployeeDaoJDBC;
import ua.epam.hr.dao.JDBC;
import ua.epam.hr.entity.Department;
import ua.epam.hr.entity.Employee;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class HRdemo {

    public static void main(String[] args) {
        JDBC jdbc = new JDBC();
        DepartmentDaoJDBC departmentDaoJDBC = new DepartmentDaoJDBC();
        departmentDaoJDBC.setConnection(jdbc.getConnection());
        EmployeeDaoJDBC employeeDaoJDBC = new EmployeeDaoJDBC();
        employeeDaoJDBC.setConnection(jdbc.getConnection());

        Department dep;
        Employee emp;


        List<String> deplist = new ArrayList<String>(){{
           add("Відділ Персоналу");
           add("Відділ Заробітної платні");
           add("Відділ податкового обліку");
           add("Відділ розробки");
           add("Відділ тестування");
           add("Відділ впровадження");
           add("Відділ аналізу");
           add("Відділ реклами");
        }};

        for (String name: deplist){
            dep = new Department(name);
            departmentDaoJDBC.add(dep);
        }

        Map<String,Integer> empllist = new HashMap<String, Integer>(){{
            int depId;
            depId = departmentDaoJDBC.getByname("Відділ Персоналу").getId();
            put("Петрова",depId); put("Сидорова",depId);
            depId = departmentDaoJDBC.getByname("Відділ Заробітної платні").getId();
            put("Іванова",depId);

        }};

        for (Map.Entry<String,Integer> entry: empllist.entrySet()){
            emp = new Employee(entry.getKey(), entry.getValue());
            employeeDaoJDBC.add(emp);
        }


        List<Department> departments = departmentDaoJDBC.getList();
        for (Department department:departments){
            int departmentId = department.getId();
            System.out.print("Отдел : ");
            System.out.println(department);
            List<Employee> employees = employeeDaoJDBC.getList(departmentId);
            System.out.print("Сотрудники : ");
            System.out.println(employees);
            System.out.println();
        }

        employeeDaoJDBC.deleteAll();
        departmentDaoJDBC.deleteAll();
    }
}
