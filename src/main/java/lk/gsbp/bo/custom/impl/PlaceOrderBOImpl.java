package lk.gsbp.bo.custom.impl;

import javafx.scene.control.Alert;
import lk.gsbp.bo.custom.PlaceOrderBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.ItemDAO;
import lk.gsbp.dao.custom.OrderDAO;
import lk.gsbp.dao.custom.OrderDetailDAO;
import lk.gsbp.dao.custom.impl.ItemDAOImpl;
import lk.gsbp.dao.custom.impl.OrderDAOImpl;
import lk.gsbp.dao.custom.impl.OrderDetailDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Order;
import lk.gsbp.entity.OrderDetails;
import lk.gsbp.model.OrderDTO;
import lk.gsbp.model.PlaceOrderDTO;
import lk.gsbp.model.orderDetailsDTO;
import lk.gsbp.repository.OrderDetailRepo;
import lk.gsbp.repository.OrderRepo;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import static lk.gsbp.repository.OrderRepo.save;

public class PlaceOrderBOImpl implements PlaceOrderBO {
OrderDetailDAO orderDetailDAO = (OrderDetailDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDERDETAILS);
OrderDAO orderDAO = (OrderDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ORDER);
ItemDAO itemDAO = (ItemDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);
    @Override
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException {
        Connection connection = DbConnection.getInstance().getConnection();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
        try {
            boolean isOrderSaved = saveOrder(po.getOrderDTO());

            if (isOrderSaved) {
                boolean isQtyUpdated = itemDAO.update3(po.getOdList());

                if (isQtyUpdated) {
                    boolean isOrderDetailSaved = saveList(po.getOdList());

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

    @Override
    public boolean saveList(List<orderDetailsDTO> odList) throws SQLException {
        for (orderDetailsDTO Od : odList) {
            boolean isSaved = orderDetailDAO.save(new OrderDetails(Od.getItemId(),Od.getOrderId(),Od.getQty(),Od.getUnitPrice()));
            if(!isSaved) {
                return false;
            }
        }
        return true;
    }
    @Override
    public boolean saveOrderDetails(orderDetailsDTO entity) throws SQLException {
        return orderDetailDAO.save(new OrderDetails(entity.getItemId(), entity.getOrderId(), entity.getQty(), entity.getUnitPrice()));
    }

    @Override
    public String getOrderIds() throws SQLException {
        return orderDAO.getOrderIds();
    }

    @Override
    public List<String> getAllOrder() throws SQLException {
        return orderDAO.getAllOrder();
    }

    @Override
    public OrderDTO searchById(String Id) throws SQLException {
        Order order = orderDAO.searchById(Id);
        return new OrderDTO(order.getOrderId(),order.getDate(),order.getCustomerId(),order.getNetTotal());
    }

    @Override
    public boolean saveOrder(OrderDTO entity) throws SQLException {
        return orderDAO.save(new Order(entity.getOrderId(), entity.getDate(),entity.getCustomerId(),entity.getNetTotal()));
    }
}
