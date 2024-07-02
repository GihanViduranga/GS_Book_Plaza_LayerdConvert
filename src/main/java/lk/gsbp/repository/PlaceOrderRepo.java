package lk.gsbp.repository;

import javafx.scene.control.Alert;
import lk.gsbp.db.DbConnection;

import java.sql.Connection;
import java.sql.SQLException;

public class PlaceOrderRepo {
    public static boolean placeOrder(PlaceOrder po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            boolean isOrderSaved = OrderRepo.save(po.getOrderDTO());

            if (isOrderSaved) {
                boolean isQtyUpdated = ItemRepo.update(po.getOdList());

                if (isQtyUpdated) {
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
        }
    }
}
