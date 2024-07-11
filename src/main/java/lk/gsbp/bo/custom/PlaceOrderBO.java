package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.entity.Order;
import lk.gsbp.model.OrderDTO;
import lk.gsbp.model.PlaceOrderDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface PlaceOrderBO extends SuperBO {
    public boolean placeOrder(PlaceOrderDTO po) throws SQLException;
    public boolean saveList(List<orderDetailsDTO> odList) throws SQLException;
    public boolean saveOrderDetails(orderDetailsDTO entity) throws SQLException;
    public String getOrderIds() throws SQLException;
    public  List<String> getAllOrder() throws SQLException;
    public OrderDTO searchById(String Id) throws SQLException;
    public boolean saveOrder(OrderDTO entity) throws SQLException;
}
