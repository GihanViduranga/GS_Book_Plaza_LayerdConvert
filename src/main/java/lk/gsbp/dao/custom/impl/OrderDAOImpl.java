package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.OrderDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Order;
import lk.gsbp.model.OrderDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderDAOImpl implements OrderDAO {

    @Override
    public List<Order> getAll() throws SQLException {
        return List.of();
    }

    @Override
    public List<Order> getIds() throws SQLException {
        return List.of();
    }
    @Override
    public String getOrderIds() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1");

        if (resultSet.next()) {
            String OrderId = resultSet.getString(1);
            return OrderId;
        }
        return null;
    }
    @Override
    public  List<String> getAllOrder() throws SQLException {
        List<String> orderIds = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT OrderId FROM orders");
        while (resultSet.next()) {
            orderIds.add(resultSet.getString(1));
        }
        return orderIds;
    }

    @Override
    public boolean update(Order entity) throws SQLException {
        return false;
    }

    @Override
    public boolean update2(Order entity) throws SQLException {
        return false;
    }

    @Override
    public Order searchById(String Id) throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT *  FROM orders WHERE OrderId = ?",Id);

        if (resultSet.next()) {
            return new Order(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;
    }

    @Override
    public boolean save(Order entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Orders ( OrderId , Date , CustomerId,NetTotal ) VALUES (?, ?, ?,?)",entity.getOrderId(),entity.getDate(),entity.getCustomerId(),entity.getNetTotal());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return false;
    }

    @Override
    public boolean update3(List<orderDetailsDTO> odList) throws SQLException {
        return false;
    }
}
