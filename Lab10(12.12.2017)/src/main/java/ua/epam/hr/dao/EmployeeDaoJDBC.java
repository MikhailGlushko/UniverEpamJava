package ua.epam.hr.dao;

import org.apache.log4j.Logger;
import ua.epam.hr.entity.Employee;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDaoJDBC implements EmployeeDao {

    public static Logger logger = Logger.getLogger(EmployeeDaoJDBC.class.getSimpleName());

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void add(Employee employee) {
        try {
            String sql = "insert into employee (name, departmentId) values(?,?)";
            PreparedStatement pst = connection.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,employee.getName());
            pst.setInt(2,employee.getDepartmentId());
            int affectedRows  = pst.executeUpdate();
            if(affectedRows ==0){
                throw new SQLException("Помилка вставки в базу");
            }
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next())
                employee.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void save(Employee employee){
        try {
            String sql = "update employee set name=?, departmentId=? where id=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,employee.getName());
            pst.setInt(2,employee.getDepartmentId());
            pst.setInt(3,employee.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public Employee delete(int id) {
        Employee employee = getById(id);
        try {
            String sql = "delete from employee where id=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
        return employee;
    }

    @Override
    public void deleteAll(){
        try {
            String sql = "delete from employee";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();

        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public Employee getById(int id) {
        Employee employee=null;
        try {
            String sql = "select id, name, departmentId from employee where id=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next())
                employee = new Employee(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getInt(3));
        } catch (SQLException e) {
            logger.error(e);
        }
        return employee;
    }

    @Override
    public Employee getByName(String name) {
        Employee employee=null;
        try {
            String sql = "select id, name, departmentId from employee where name=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,name);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next())
                employee = new Employee(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getInt(3));
        } catch (SQLException e) {
            logger.error(e);
        }
        return employee;

    }

    @Override
    public List<Employee> getList() {
        List<Employee> list = new ArrayList<>();
        Employee employee=null;
        try {
            String sql = "select id, name, departmentId from employee";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                employee = new Employee(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getInt(3));
                list.add(employee);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }

    public List<Employee> getList(int departmentId){
        List<Employee> list = new ArrayList<>();
        Employee employee=null;
        try {
            String sql = "select id, name, departmentId from employee where departmentId=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,departmentId);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                employee = new Employee(resultSet.getInt(1),
                                        resultSet.getString(2),
                                        resultSet.getInt(3));
                list.add(employee);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }
}
