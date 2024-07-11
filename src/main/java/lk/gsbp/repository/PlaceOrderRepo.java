package lk.gsbp.repository;

import javafx.scene.control.Alert;
import lk.gsbp.bo.BOFactory;
import lk.gsbp.dao.custom.impl.ItemDAOImpl;
import lk.gsbp.dao.custom.impl.OrderDAOImpl;
import lk.gsbp.dao.custom.impl.OrderDetailDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.model.PlaceOrderDTO;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {

    /*public static boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        *//*Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            OrderDAOImpl orderDAO = new OrderDAOImpl();
            boolean isOrderSaved = OrderRepo.save(po.getOrderDTO());

            if (isOrderSaved) {
                ItemDAOImpl itemDAO = new ItemDAOImpl();
                boolean isQtyUpdated = itemDAO.update3(po.getOdList());

                if (isQtyUpdated) {
                    OrderDetailDAOImpl orderDetailDAO = new OrderDetailDAOImpl();
                    boolean isOrderDetailSaved = OrderDetailRepo.save(po.getOdList());

                    if (isOrderDetailSaved) {
                        connection.commit();
                        return true;
                    }
                }
            }

            connection.rollback();
            return false;
        } finally {
            connection.setAutoCommit(true);
        }*//*
    }*/
}
