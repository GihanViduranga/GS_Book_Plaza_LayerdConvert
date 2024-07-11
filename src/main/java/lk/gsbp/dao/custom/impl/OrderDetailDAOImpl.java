package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.OrderDetailDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.OrderDetails;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailDAOImpl implements OrderDetailDAO {

    @Override
    public List<OrderDetails> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public List<OrderDetails> getIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update2(OrderDetails entity) throws SQLException {
        return false;
    }

    @Override
    public OrderDetails searchById(String Id) throws SQLException {
        return null;
    }
    @Override
    public boolean save(OrderDetails entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO OrderDetails(ItemsId,OrderId,qty,unitPrice) VALUES(?, ?, ?, ?)",entity.getItemId(),entity.getOrderId(),entity.getQty(),entity.getUnitPrice());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
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
}
