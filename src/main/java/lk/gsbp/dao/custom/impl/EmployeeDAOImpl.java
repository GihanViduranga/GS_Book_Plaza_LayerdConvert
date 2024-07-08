package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.EmployeeDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Employee;
import lk.gsbp.model.EmployeeDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDAOImpl implements EmployeeDAO {
    @Override
    public List<Employee> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM employee");

        List<Employee> employees = new ArrayList<>();

        while (resultSet.next()) {
            String EmployeeId = resultSet.getNString(1);
            String Name = resultSet.getNString(3);
            String Address = resultSet.getNString(4);
            String Contact = resultSet.getNString(5);
            String Date = resultSet.getNString(6);
            String Position = resultSet.getNString(7);
            String Salary = resultSet.getNString(8);

            Employee employee = new Employee(EmployeeId, Name, Address, Contact, Date, Position, Salary);

            employees.add(employee);
        }
        return employees;
    }

    @Override
    public List<Employee> getIds() throws SQLException {
        /*String sql = "SELECT EmployeeId FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/

        List<Employee> Ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT EmployeeId FROM employee");
        while (resultSet.next()) {
            Employee employee = new Employee(
                    resultSet.getString("EmployeeId"),
                    resultSet.getString("Name"),
                    resultSet.getString("Address"),
                    resultSet.getString("Contact"),
                    resultSet.getString("Date"),
                    resultSet.getString("Position"),
                    resultSet.getString("Salary")
                    );
            Ids.add(employee);
        }
        return Ids;
    }

    @Override
    public boolean update(Employee entity) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET Name =?, Address =?, Contact =?, Date =?, Position =?, Salary =? WHERE EmployeeId =?",entity.getName(),entity.getAddress(),entity.getContact(),entity.getDate(),entity.getPosition(),entity.getSalary(),entity.getEmployeeId());
    }

    @Override
    public boolean update2(Employee entity) throws SQLException {
        return SQLUtil.execute("UPDATE employee SET Name =?, Address =?, Contact =?, Date =?, Position =?, Salary =? WHERE EmployeeId =?",entity.getName(),entity.getAddress(),entity.getContact(),entity.getDate(),entity.getPosition(),entity.getSalary(),entity.getEmployeeId());
    }

    @Override
    public Employee searchById(String Id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM employee WHERE EmployeeId =?",Id);
        rst.next();
        return new Employee(
                Id,
                rst.getString("Name"),
                rst.getString("Address"),
                rst.getString("Contact"),
                rst.getString("Date"),
                rst.getString("Position"),
                rst.getString("Salary"));
    }

    @Override
    public boolean save(Employee entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO employee (EmployeeId,Name,Address,Contact,Date,Position,Salary) Values(?,?,?,?,?,?,?)",entity.getEmployeeId(),entity.getName(),entity.getAddress(),entity.getContact(),entity.getDate(),entity.getSalary(),entity.getPosition());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM employee WHERE EmployeeId =?",id);
    }
}
