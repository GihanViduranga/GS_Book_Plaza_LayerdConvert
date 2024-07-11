package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.custom.PaymentBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.PaymentDAO;
import lk.gsbp.entity.Payment;
import lk.gsbp.model.PaymentDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentBOImpl implements PaymentBO {

    PaymentDAO paymentDAO = (PaymentDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PAYMENT);
    @Override
    public List<PaymentDTO> getAllPayment() throws SQLException {
        List<PaymentDTO> allPayments = new ArrayList();
        List<Payment> all = paymentDAO.getAll();

        for(Payment payment : all){
            allPayments.add(new PaymentDTO(payment.getPaymentId(),payment.getPaymentMethod(),payment.getDate(),payment.getPayment()));
        }
        return allPayments;
    }

    @Override
    public boolean updatePayment(PaymentDTO entity) throws SQLException {
        return paymentDAO.update(new Payment(entity.getPaymentMethod(),entity.getDate(),entity.getPayment(),entity.getPaymentId()));
    }

    @Override
    public boolean update2Payment(PaymentDTO entity) throws SQLException {
        return paymentDAO.update(new Payment(entity.getPaymentMethod(),entity.getDate(),entity.getPayment(),entity.getPaymentId()));
    }

    @Override
    public PaymentDTO searchPaymentById(String Id) throws SQLException {
        Payment payment = paymentDAO.searchById(Id);
        return new PaymentDTO(payment.getPaymentId(), payment.getPaymentMethod(), payment.getDate(), payment.getPayment());
    }

    @Override
    public boolean savePayment(PaymentDTO entity) throws SQLException {
        return paymentDAO.save(new Payment(entity.getPaymentId(), entity.getPaymentMethod(), entity.getDate(), entity.getPayment()));
    }

    @Override
    public boolean deletePayment(String id) throws SQLException {
        return paymentDAO.delete(id);
    }

    @Override
    public String GetPaymentId() throws SQLException {
        return paymentDAO.GetPaymentId();
    }
}
