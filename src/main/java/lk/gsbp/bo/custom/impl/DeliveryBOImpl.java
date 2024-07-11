package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.custom.DeliveryBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.DeliveryDAO;
import lk.gsbp.entity.Delivery;
import lk.gsbp.model.DeliveryDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryBOImpl implements DeliveryBO {
    DeliveryDAO deliveryDAO = (DeliveryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.DELIVERY);
    @Override
    public List<DeliveryDTO> getAll() throws SQLException {
        List<DeliveryDTO> allDelivery = new ArrayList();
        List<Delivery> all = deliveryDAO.getAll();

        for (Delivery delivery : all){
            allDelivery.add(new DeliveryDTO(delivery.getDeliveryId(), delivery.getDeliverName(), delivery.getDate(), delivery.getAddress(), delivery.getStetus()));
        }
        return allDelivery;
    }

    @Override
    public boolean update(DeliveryDTO entity) throws SQLException {
        return deliveryDAO.update(new Delivery(entity.getDeliveryId(), entity.getDeliverName(),entity.getDate(),entity.getAddress(),entity.getStetus()));
    }

    @Override
    public boolean update2(DeliveryDTO entity) throws SQLException {
        return deliveryDAO.update(new Delivery(entity.getDeliveryId(), entity.getDeliverName(),entity.getDate(),entity.getAddress(),entity.getStetus()));
    }

    @Override
    public DeliveryDTO searchById(String Id) throws SQLException {
       Delivery delivery = deliveryDAO.searchById(Id);
       return new  DeliveryDTO(delivery.getDeliveryId(),delivery.getDeliverName(),delivery.getDate(),delivery.getAddress(),delivery.getStetus());
    }

    @Override
    public boolean save(DeliveryDTO entity) throws SQLException {
        return deliveryDAO.save(new Delivery(entity.getDeliveryId(),entity.getDeliverName(),entity.getDate(),entity.getAddress(),entity.getStetus()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return deliveryDAO.delete(id);
    }
}
