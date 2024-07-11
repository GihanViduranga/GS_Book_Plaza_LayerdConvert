package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.BOFactory;
import lk.gsbp.bo.custom.EmployeeBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.EmployeeDAO;
import lk.gsbp.entity.Employee;
import lk.gsbp.model.EmployeeDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class EmployeeBOImpl implements EmployeeBO {
    EmployeeDAO employeeDAO = (EmployeeDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.EMPLOYEE);

    @Override
    public List<EmployeeDTO> getAll() throws SQLException {
        List<EmployeeDTO> employees = new ArrayList();
        List<Employee> allEmployees = employeeDAO.getAll();

        for (Employee employee : allEmployees) {
            employees.add(new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getContact(),employee.getDate(),employee.getPosition(),employee.getSalary()));
        }
        return employees;
    }

    @Override
    public List<EmployeeDTO> getIds() throws SQLException {
        List<EmployeeDTO> employees = new ArrayList();
        List<Employee> allEmployeeIds = employeeDAO.getIds();

        for (Employee employee : allEmployeeIds) {
            employees.add(new EmployeeDTO(employee.getEmployeeId(),employee.getName(),employee.getAddress(),employee.getContact(),employee.getDate(),employee.getPosition(),employee.getSalary()));
        }
        return employees;
    }

    @Override
    public boolean update(EmployeeDTO entity) throws SQLException {
        return employeeDAO.update(new Employee(entity.getEmployeeId(), entity.getName(), entity.getAddress(), entity.getContact(), entity.getDate(), entity.getPosition(), entity.getSalary()));
    }

    @Override
    public boolean update2(EmployeeDTO entity) throws SQLException {
        return employeeDAO.update2(new Employee(entity.getEmployeeId(),entity.getName(),entity.getAddress(),entity.getContact(),entity.getDate(),entity.getPosition(),entity.getSalary()));
    }

    @Override
    public EmployeeDTO searchById(String Id) throws SQLException {
        Employee employee = employeeDAO.searchById(Id);
        return new EmployeeDTO(employee.getEmployeeId(), employee.getName(),employee.getAddress(),employee.getContact(),employee.getDate(),employee.getPosition(),employee.getSalary());
    }

    @Override
    public boolean save(EmployeeDTO entity) throws SQLException {
        return employeeDAO.save(new Employee(entity.getEmployeeId(),entity.getName(),entity.getAddress(),entity.getContact(),entity.getDate(),entity.getPosition(),entity.getSalary()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return employeeDAO.delete(id);
    }
}
