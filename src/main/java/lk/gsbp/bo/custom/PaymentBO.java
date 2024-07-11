package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.entity.Payment;
import lk.gsbp.model.PaymentDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface PaymentBO extends SuperBO {
    public List<PaymentDTO> getAllPayment() throws SQLException;
    public boolean updatePayment(PaymentDTO entity) throws SQLException;
    public boolean update2Payment(PaymentDTO entity) throws SQLException;
    public PaymentDTO searchPaymentById(String Id) throws SQLException;
    public boolean savePayment(PaymentDTO entity) throws SQLException;
    public boolean deletePayment(String id) throws SQLException;
    public String GetPaymentId() throws SQLException;
}
