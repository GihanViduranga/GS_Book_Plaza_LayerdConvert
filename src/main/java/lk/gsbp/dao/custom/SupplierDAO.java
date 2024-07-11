package lk.gsbp.dao.custom;

import lk.gsbp.dao.CrudDAO;
import lk.gsbp.entity.Supplier;

import java.sql.SQLException;

public interface SupplierDAO extends CrudDAO<Supplier> {
    public String getCurrentSupId() throws SQLException, ClassNotFoundException;
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException;
}
