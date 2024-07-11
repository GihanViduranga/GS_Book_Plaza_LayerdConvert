package lk.gsbp.repository;

import lk.gsbp.dao.custom.impl.OrderDetailDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.OrderDetails;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class OrderDetailRepo {
    public static boolean save(List<OrderDetails> odList) throws SQLException {
        for (OrderDetails Od : odList) {
            boolean isSaved = save(Od);
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    private static boolean save(OrderDetails od) throws SQLException {
        /*String sql = "INSERT INTO OrderDetails(ItemsId,OrderId,qty,unitPrice) VALUES(?, ?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, od.getItemId());
        pstm.setString(2, od.getOrderId());
        pstm.setInt(3,  od.getQty());
        pstm.setDouble(4, od.getUnitPrice());

        return pstm.executeUpdate() > 0;*/
        OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();
        return orderDetailDAO.save(od);
    }
}
