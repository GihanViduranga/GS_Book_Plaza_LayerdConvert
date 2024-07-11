package lk.gsbp.repository;

import lk.gsbp.dao.custom.impl.DeliveryDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Delivery;
import lk.gsbp.model.DeliveryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepo {
    public static List<Delivery> getAllDelivery() throws SQLException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.getAll();
    }
    public static boolean update (Delivery delivery) throws SQLException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.update(new Delivery(delivery.getDeliveryId(),delivery.getDeliverName(),delivery.getDate(),delivery.getAddress(),delivery.getStetus()));
    }
    public static boolean update2(String DeliveryId, String DeliverName, String Date, String Address, String Stetus ) throws SQLException {
        DeliveryDAOImpl deliveryDAO = new DeliveryDAOImpl();
        return deliveryDAO.update2(new Delivery(DeliveryId,DeliverName,Date,Address,Stetus));
    }
}
