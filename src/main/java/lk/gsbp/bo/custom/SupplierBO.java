package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.entity.Supplier;
import lk.gsbp.model.SupplierDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.gsbp.controller.SupplierFormController.splitCustomerId;

public interface SupplierBO extends SuperBO {
    public boolean save(SupplierDTO dto) throws SQLException;
    public boolean delete(String supplierId) throws SQLException;
    public boolean update(SupplierDTO dto) throws SQLException;
    public SupplierDTO searchById(String Id) throws SQLException;
    public String getCurrentSupId() throws SQLException, ClassNotFoundException;
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException;
    public List<SupplierDTO> getAll() throws SQLException;
}
