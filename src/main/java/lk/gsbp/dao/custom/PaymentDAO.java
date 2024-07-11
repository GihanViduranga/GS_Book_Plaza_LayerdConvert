package lk.gsbp.dao.custom;

import lk.gsbp.dao.CrudDAO;
import lk.gsbp.entity.Payment;

import java.sql.SQLException;

public interface PaymentDAO extends CrudDAO<Payment> {
    public String GetPaymentId() throws SQLException;
}
