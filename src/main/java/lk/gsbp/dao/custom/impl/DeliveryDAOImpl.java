package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.DeliveryDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Delivery;
import lk.gsbp.model.DeliveryDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryDAOImpl implements DeliveryDAO {
    @Override
    public List<Delivery> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Delivery");

        List<Delivery> deliveryDTOList = new ArrayList<>();

        while (resultSet.next()) {
            String DeliveryId = resultSet.getString(1);
            String DeliveryName = resultSet.getString(2);
            String Date = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String Stetus = resultSet.getString(5);

            Delivery delivery = new Delivery(DeliveryId, DeliveryName, Date, Address, Stetus);

            deliveryDTOList.add(delivery);
        }

        return deliveryDTOList;
    }

    @Override
    public List<Delivery> getIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(Delivery entity) throws SQLException {
        return SQLUtil.execute("UPDATE Delivery SET DeliverName = ?, Date =?, Address =?, Stetus =? WHERE DeliveryId =?", entity.getDeliveryId(),entity.getDeliverName(),entity.getDate(),entity.getAddress(),entity.getStetus());
    }

    @Override
    public boolean update2(Delivery entity) throws SQLException {
        return SQLUtil.execute("UPDATE Delivery SET DeliverName = ?, Date =?, Address =?, Stetus =? WHERE DeliveryId =?",entity.getDeliverName(),entity.getDate(),entity.getAddress(),entity.getStetus(),entity.getDeliveryId());
    }

    @Override
    public Delivery searchById(String Id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Delivery WHERE DeliveryId =?",Id);
        rst.next();
        return new Delivery(
                rst.getString(1),
                rst.getString(2),
                rst.getString(3),
                rst.getString(4),
                rst.getString(5)
        );
    }

    @Override
    public boolean save(Delivery entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Delivery VALUES (?,?,?,?,?)",entity.getDeliveryId(),entity.getDeliverName(),entity.getDate(),entity.getAddress(),entity.getStetus());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Delivery WHERE DeliveryId =?",id);
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
