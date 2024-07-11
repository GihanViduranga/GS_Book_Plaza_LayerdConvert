package lk.gsbp.repository;

import lk.gsbp.dao.custom.impl.PaymentDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Payment;
import lk.gsbp.model.PaymentDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentRepo {
    public static boolean update(Payment payment) throws SQLException {
        /*String sql = "UPDATE Payment SET PaymentMethod = ?, Date = ?, Payment = ? WHERE PaymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, paymentDTO.getPaymentId());
        pstm.setObject(2, paymentDTO.getPaymentMethod());
        pstm.setString(3, paymentDTO.getDate());
        pstm.setObject(4, paymentDTO.getPayment());


        return pstm.executeUpdate() > 0;*/
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        return paymentDAO.update2(new Payment(payment.getPaymentId(), payment.getPaymentMethod(), payment.getDate(), payment.getPayment()));
    }
    public static boolean update2(String PaymentId, String PaymentMethod, String Date,String Payment) throws SQLException {
        /*String sql = "UPDATE Payment SET PaymentMethod = ?, Date = ?, Payment = ? WHERE PaymentId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, PaymentId);
        pstm.setObject(2, PaymentMethod);
        pstm.setObject(3, Date);
        pstm.setObject(4, Payment);


        return pstm.executeUpdate() > 0;*/
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        return paymentDAO.update2(new Payment(PaymentId, PaymentMethod, Date, Payment));
    }

    public static List<Payment> getAllPayments() throws SQLException {
        /*String sql = "SELECT * FROM payment";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<PaymentDTO> paymentDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String PaymentId = resultSet.getString(1);
            String PaymentMethod = resultSet.getString(2);
            String Date = resultSet.getString(3);
            String Payment = resultSet.getString(4);

            PaymentDTO paymentDTO = new PaymentDTO(PaymentId, PaymentMethod, Date, Payment);
            paymentDTOS.add(paymentDTO);
        }
        return paymentDTOS;*/
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        return paymentDAO.getAll();
    }

    public static String GetPaymentId() throws SQLException {
        /*String sql = "SELECT PaymentId FROM payment ORDER BY PaymentId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String PaymentId = resultSet.getString(1);
            return PaymentId;
        }
        return null;*/
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        return paymentDAO.GetPaymentId();
    }

    public boolean save(String paymentId, String paymentMethod, String date, String payment) throws SQLException {
        /*String sql = "INSERT INTO payment VALUES (?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, paymentId);
        pstm.setString(2, paymentMethod);
        pstm.setString(3, date);
        pstm.setString(4, payment);

        return pstm.executeUpdate() > 0;*/
        PaymentDAOImpl paymentDAO = new PaymentDAOImpl();
        return paymentDAO.save(new Payment(paymentId, paymentMethod, date, payment));
    }
}
