package lk.gsbp.repository;

import lk.gsbp.db.DbConnection;
import lk.gsbp.model.SupplierDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierRepo {
    public static boolean update (SupplierDTO supplierDTO) throws SQLException {
        String sql = "UPDATE supplier SET Name = ?, Contact = ?, Products = ?, ItemName = ?, Qty = ? WHERE SupplierId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, supplierDTO.getSuppName());
        pstm.setString(2, supplierDTO.getContact());
        pstm.setString(3, supplierDTO.getProduct());
        pstm.setString(4, supplierDTO.getItemName());
        pstm.setString(5, supplierDTO.getQty());
        pstm.setString(6, supplierDTO.getSupplierId());

        return pstm.executeUpdate() > 0;
    }
    public static boolean update2(String SupplierId,String SuppName,String Contact,String Products,String ItemName,String Qty) throws SQLException {
        String sql = "UPDATE supplier SET Name =?, Contact =?, Products =?, ItemName =?, Qty =? WHERE SupplierId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,SuppName);
        pstm.setObject(2,Contact);
        pstm.setObject(3,Products);
        pstm.setObject(4,ItemName);
        pstm.setObject(5,Qty);
        pstm.setObject(6,SupplierId);

        return pstm.executeUpdate() > 0;
    }

    public static List<SupplierDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM supplier";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

            List<SupplierDTO> supplierDTOS = new ArrayList<>();

            while (resultSet.next()) {
                String SupplierId = resultSet.getNString(1);
                String SupplierName = resultSet.getNString(2);
                String Contact = resultSet.getNString(3);
                String Products = resultSet.getNString(4);
                String ItemName = resultSet.getNString(5);
                String Qty = resultSet.getNString(6);
                String Itemprice = resultSet.getNString(7);

                SupplierDTO supplierDTO = new SupplierDTO(SupplierId, SupplierName, Contact, Products,ItemName, Qty, Itemprice);
                supplierDTOS.add(supplierDTO);
            }
            return supplierDTOS;
        }
    }
