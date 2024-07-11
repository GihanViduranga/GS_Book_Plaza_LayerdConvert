package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.SupplierDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Supplier;
import lk.gsbp.model.orderDetailsDTO;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static lk.gsbp.controller.SupplierFormController.splitCustomerId;

public class SupplierDAOImpl implements SupplierDAO {
    public boolean save(Supplier dto) throws SQLException{
        return SQLUtil.execute("INSERT INTO supplier(SupplierID,Name,Contact,Products,ItemName,Qty) VALUES (?,?,?,?,?,?)",dto.getSupplierId(),dto.getSuppName(),dto.getContact(),dto.getProduct(),dto.getItemName(),dto.getQty());

    }

    public boolean delete(String supplierId) throws SQLException{
        return SQLUtil.execute("DELETE FROM supplier WHERE SupplierID = ?", supplierId);
    }

    @Override
    public boolean update3(List<orderDetailsDTO> odList) throws SQLException {
        return false;
    }

    @Override
    public String getOrderIds() throws SQLException {
        return "";
    }

    @Override
    public List<String> getAllOrder() throws SQLException {
        return List.of();
    }

    public boolean update(Supplier dto) throws SQLException {
        return SQLUtil.execute("UPDATE supplier SET Name = ?, Contact = ?, Products = ?, ItemName = ?, Qty = ?  WHERE SupplierID = ?", dto.getSuppName(), dto.getContact(), dto.getProduct(), dto.getItemName(), dto.getQty(), dto.getSupplierId());
    }

    @Override
    public boolean update2(Supplier entity) throws SQLException {
        return false;
    }

    public Supplier searchById(String Id) throws SQLException {

        ResultSet resultSet = SQLUtil.execute( "SELECT * FROM supplier WHERE SupplierID = ?",Id);
        if (resultSet.next()) {
            return new Supplier(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5),
                    resultSet.getString(6)
            );
        }

        return null;
    }

    public String getCurrentSupId() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1");

        if (resultSet.next()) {
            String SupplierID = resultSet.getString(1);
            return SupplierID;
        }
        return null;
    }

    public String generateNewSupplierID() throws SQLException, ClassNotFoundException {

        ResultSet resultSet = SQLUtil.execute("SELECT SupplierID FROM supplier ORDER BY SupplierID DESC LIMIT 1");

        if (resultSet.next()) {
            return splitCustomerId(resultSet.getString(1));
        }
        return splitCustomerId(null);
    }

    public List<Supplier> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM supplier");

        List<Supplier> supplierList = new ArrayList<>();

        while (resultSet.next()) {
            String SupplierId = resultSet.getString(1);
            String SuppName = resultSet.getString(2);
            String Contact = resultSet.getString(3);
            String Product = resultSet.getString(4);
            String ItemName = resultSet.getString(5);
            String Qty = resultSet.getString(6);


            Supplier supplier = new Supplier(SupplierId, SuppName, Contact, Product,ItemName, Qty);
            supplierList.add(supplier);
        }
        return supplierList;
    }

    @Override
    public List<Supplier> getIds() throws SQLException {
        return List.of();
    }
}
