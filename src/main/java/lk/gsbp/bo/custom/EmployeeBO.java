package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.entity.Employee;
import lk.gsbp.model.EmployeeDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface EmployeeBO extends SuperBO {
    public List<EmployeeDTO> getAll() throws SQLException;

    public List<EmployeeDTO> getIds() throws SQLException;

    public boolean update(EmployeeDTO entity) throws SQLException;

    public boolean update2(EmployeeDTO entity) throws SQLException;

    public EmployeeDTO searchById(String Id) throws SQLException;

    public boolean save(EmployeeDTO entity) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
