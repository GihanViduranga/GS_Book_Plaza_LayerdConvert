package lk.gsbp.repository;

import lk.gsbp.dao.custom.impl.OrderDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Order;
import lk.gsbp.model.OrderDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderRepo {

    public static String GetOrderId() throws SQLException {
        /*String sql = "SELECT OrderId FROM Orders ORDER BY OrderId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String OrderId = resultSet.getString(1);
            return OrderId;
        }
        return null;*/
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.getOrderIds();
    }

    public static boolean save(OrderDTO orderDTO) throws SQLException {
        /*String sql = "INSERT INTO Orders ( OrderId , Date , CustomerId,NetTotal ) VALUES (?, ?, ?,?)";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, orderDTO.getOrderId());
        pstm.setString(2, orderDTO.getDate());
        pstm.setString(3, orderDTO.getCustomerId());
        pstm.setDouble(4, orderDTO.getNetTotal());

        //pstm.setDate(3, Date.valueOf(order.getDate()));

        return pstm.executeUpdate() > 0;*/
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.save(new Order(orderDTO.getOrderId(), orderDTO.getDate(), orderDTO.getCustomerId(), orderDTO.getNetTotal()));
    }

    public static Order searchById(String id) throws SQLException {
        /*String sql = "SELECT *  FROM orders WHERE OrderId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return new OrderDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
        }
        return null;*/
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.searchById(id);
    }

    public static List<String> getAllOrders() throws SQLException {
        /*String sql = "SELECT OrderId FROM orders";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> orderIds = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            orderIds.add(resultSet.getString(1));
        }
        return orderIds;
    }*/
        OrderDAOImpl orderDAO = new OrderDAOImpl();
        return orderDAO.getAllOrder();
    }
}
