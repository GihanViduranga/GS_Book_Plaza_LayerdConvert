package lk.gsbp.repository;

import lk.gsbp.db.DbConnection;
import lk.gsbp.model.DeliveryDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DeliveryRepo {
    public static List<DeliveryDTO> getAllDelivery() throws SQLException {
        String sql = "SELECT * FROM Delivery";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<DeliveryDTO> deliveryDTOList = new ArrayList<>();

        while (resultSet.next()) {
            String DeliveryId = resultSet.getString(1);
            String DeliveryName = resultSet.getString(2);
            String Date = resultSet.getString(3);
            String Address = resultSet.getString(4);
            String Stetus = resultSet.getString(5);

            DeliveryDTO deliveryDTO = new DeliveryDTO(DeliveryId, DeliveryName, Date, Address, Stetus);

            deliveryDTOList.add(deliveryDTO);
        }

        return deliveryDTOList;
    }
    public static boolean update (DeliveryDTO deliveryDTO) throws SQLException {
        String sql = "UPDATE Delivery SET DeliveryId =?, DeliverName = ?, Date =?, Address =?, Stetus =? WHERE DeliveryId";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, deliveryDTO.getDeliveryId());
        pstm.setObject(2, deliveryDTO.getDeliverName());
        pstm.setObject(2, deliveryDTO.getDate());
        pstm.setObject(3, deliveryDTO.getAddress());
        pstm.setObject(4, deliveryDTO.getStetus());

        return pstm.executeUpdate() > 0;
    }
    public static boolean update2(String DeliveryId, String DeliverName, String Date, String Address, String Stetus ) throws SQLException {
        String sql = "UPDATE Delivery SET Date =?, Address =?, Stetus =? WHERE DeliveryId = ?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,DeliverName);
        pstm.setObject(1,Date);
        pstm.setObject(2,Address);
        pstm.setObject(3,Stetus);
        pstm.setObject(4,DeliveryId);

        return pstm.executeUpdate() > 0;
    }
}
