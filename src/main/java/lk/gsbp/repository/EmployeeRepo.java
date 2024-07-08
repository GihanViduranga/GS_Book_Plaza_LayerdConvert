package lk.gsbp.repository;


import lk.gsbp.dao.custom.impl.EmployeeDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Employee;
import lk.gsbp.model.EmployeeDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeRepo {
    public static List<Employee> getEmployees() throws SQLException {
        /*String sql = "SELECT * FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<EmployeeDTO> employeeDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String EmployeeId = resultSet.getNString(1);
            String Name = resultSet.getNString(3);
            String Address = resultSet.getNString(4);
            String Contact = resultSet.getNString(5);
            String Date = resultSet.getNString(6);
            String Position = resultSet.getNString(7);
            String Salary = resultSet.getNString(8);

            EmployeeDTO employeeDTO = new EmployeeDTO(EmployeeId, Name, Address, Contact, Date, Position, Salary);

            employeeDTOS.add(employeeDTO);
        }
        return employeeDTOS;*/
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.getAll();
    }
    public static List<Employee> getIds() throws SQLException {
        /*String sql = "SELECT EmployeeId FROM employee";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> Ids = new ArrayList<String>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String EmployeeId = resultSet.getNString(1);
            Ids.add(EmployeeId);
        }
        return Ids;*/
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.getIds();
    }
    public static boolean update (Employee employee) throws SQLException {
        /*String sql = "UPDATE employee SET Name =?, Address =?, Contact =?, Date =?, Position =?, Salary =? WHERE EmployeeId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, employeeDTO.getName());
        pstm.setString(2, employeeDTO.getAddress());
        pstm.setString(3, employeeDTO.getContact());
        pstm.setString(4, employeeDTO.getDate());
        pstm.setString(5, employeeDTO.getPosition());
        pstm.setString(6, employeeDTO.getSalary());
        pstm.setObject(7, employeeDTO.getEmployeeId());

        return pstm.executeUpdate() > 0;*/
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.update(new Employee(
                employee.getName(),
                employee.getAddress(),
                employee.getContact(),
                employee.getDate(),
                employee.getPosition(),
                employee.getSalary(),
                employee.getEmployeeId()
        ));
    }
    public static boolean update2 (String EmployeeId, String Name, String Address, String Contact, String Date, String Position, String Salary) throws SQLException {
        /*String sql = "UPDATE employee SET Name =?, Address =?, Contact =?, Date =?, Position =?, Salary =? WHERE EmployeeId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,Name);
        pstm.setObject(2,Address);
        pstm.setObject(3,Contact);
        pstm.setObject(4,Date);
        pstm.setObject(5,Position);
        pstm.setObject(6,Salary);
        pstm.setObject(7,EmployeeId);

        return pstm.executeUpdate() > 0;*/
        EmployeeDAOImpl employeeDAO = new EmployeeDAOImpl();
        return employeeDAO.update2(new Employee(EmployeeId, Name, Address, Contact, Date, Position, Salary));
    }
}
