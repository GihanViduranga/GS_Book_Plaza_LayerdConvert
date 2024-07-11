package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.PaymentDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Payment;
import lk.gsbp.model.PaymentDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentDAOImpl implements PaymentDAO {

    @Override
    public List<Payment> getAll() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM payment");

        List<Payment> paymentS = new ArrayList<>();

        while (resultSet.next()) {
            String PaymentId = resultSet.getString(1);
            String PaymentMethod = resultSet.getString(2);
            String Date = resultSet.getString(3);
            String Payment = resultSet.getString(4);

            Payment payment = new Payment(PaymentId, PaymentMethod, Date, Payment);
            paymentS.add(payment);
        }
        return paymentS;
    }

    @Override
    public List<Payment> getIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(Payment entity) throws SQLException {
        return SQLUtil.execute("UPDATE Payment SET PaymentMethod = ?, Date = ?, Payment = ? WHERE PaymentId = ?",entity.getPaymentMethod(),entity.getDate(),entity.getPayment(),entity.getPaymentId());
    }

    @Override
    public boolean update2(Payment entity) throws SQLException {
        return SQLUtil.execute("UPDATE Payment SET PaymentMethod = ?, Date = ?, Payment = ? WHERE PaymentId = ?",entity.getPaymentMethod(),entity.getDate(),entity.getPayment(),entity.getPaymentId());
    }

    @Override
    public Payment searchById(String Id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM payment WHERE PaymentId =?",Id);
        rst.next();
        return new Payment(
                Id,
                rst.getString("PaymentMethod"),
                rst.getString("Date"),
                rst.getString("Payment")
        );
    }

    @Override
    public boolean save(Payment entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO payment VALUES (?,?,?,?)",entity.getPaymentId(),entity.getPaymentMethod(),entity.getDate(),entity.getPayment());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM payment WHERE PaymentId =?",id);
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

    public String GetPaymentId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT PaymentId FROM payment ORDER BY PaymentId DESC LIMIT 1");

        if (resultSet.next()) {
            String PaymentId = resultSet.getString(1);
            return PaymentId;
        }
        return null;

    }
}
