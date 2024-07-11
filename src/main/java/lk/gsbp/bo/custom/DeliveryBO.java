package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.entity.Delivery;
import lk.gsbp.model.DeliveryDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface DeliveryBO extends SuperBO {
    public List<DeliveryDTO> getAll() throws SQLException;

    public boolean update(DeliveryDTO entity) throws SQLException;

    public boolean update2(DeliveryDTO entity) throws SQLException;

    public DeliveryDTO searchById(String Id) throws SQLException;

    public boolean save(DeliveryDTO entity) throws SQLException;

    public boolean delete(String id) throws SQLException;
}
