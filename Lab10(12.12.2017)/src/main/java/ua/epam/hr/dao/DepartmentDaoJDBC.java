package ua.epam.hr.dao;

import org.apache.log4j.Logger;
import ua.epam.hr.entity.Department;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartmentDaoJDBC implements DepartmentDao {

    public static Logger logger = Logger.getLogger(EmployeeDaoJDBC.class.getSimpleName());

    private Connection connection;

    public Connection getConnection() {
        return connection;
    }

    public void setConnection(Connection connection) {
        this.connection = connection;
    }

    @Override
    /**
     * Добавити в базу даних новий дкпартамент
     * Якщо депатрамент вже існує - відбувається перевірка на рівні бази даних на унікальність і запис не добавляжться
     * Після успішного добавлення в базу даних, записужмо в обєкт згенерований базою ID
     */
    public void add(Department department) {
        try {
            String sql = "insert into department (name) values(?)";
            PreparedStatement pst = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pst.setString(1,department.getName());
            int affectedRows  = pst.executeUpdate();
            if(affectedRows ==0){
                throw new SQLException("Помилка вставки в базу");
            }
            ResultSet generatedKeys = pst.getGeneratedKeys();
            if (generatedKeys.next())
                department.setId(generatedKeys.getInt(1));
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public void save(Department department){
        try {
            String sql = "update department set name=? where id=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,department.getName());
            pst.setInt(2,department.getId());
            pst.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Department delete(int id) {
        Department department = getById(id);
        try {
            String sql = "delete from department where id=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            pst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
        return department;
    }

    @Override
    public void deleteAll(){
        try {
            String sql = "delete from department";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.executeUpdate();
        } catch (SQLException e) {
            logger.error(e);
        }
    }

    @Override
    public Department getById(int id) {
        Department department = null;
        try {
            String sql = "select id, name from department where id=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setInt(1,id);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next())
                department = new Department(resultSet.getInt(1),
                                            resultSet.getString(2));
        } catch (SQLException e) {
            logger.error(e);
        }
        return department;
    }

    @Override
    public Department getByname(String name) {
        Department department = null;
        try {
            String sql = "select id, name from department where name=?";
            PreparedStatement pst = connection.prepareStatement(sql);
            pst.setString(1,name);
            ResultSet resultSet = pst.executeQuery();
            if(resultSet.next())
                department = new Department(resultSet.getInt(1),
                                            resultSet.getString(2));
        } catch (SQLException e) {
            logger.error(e);
        }
        return department;
    }

    @Override
    public List<Department> getList() {
        List<Department> list = new ArrayList<>();
        Department department = null;
        try {
            String sql = "select id, name from department";
            PreparedStatement pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            while (resultSet.next()){
                department = new Department(resultSet.getInt(1),
                                            resultSet.getString(2));
                list.add(department);
            }
        } catch (SQLException e) {
            logger.error(e);
        }
        return list;
    }
}
