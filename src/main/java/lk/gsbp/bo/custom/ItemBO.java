package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Item;
import lk.gsbp.model.ItemDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemBO extends SuperBO {

    public List<ItemDTO> getAllItems() throws SQLException;

    public List<ItemDTO> getItemIds() throws SQLException;

    public boolean updateItem(ItemDTO dto) throws SQLException;

    public boolean update2Item(ItemDTO dto) throws SQLException;

    public ItemDTO searchByItemId(String Id) throws SQLException;

    public boolean saveItem(ItemDTO dto) throws SQLException;

    public boolean deleteItem(String id) throws SQLException;

    public boolean update3Item(List<orderDetailsDTO> odList) throws SQLException;

    public String GetItemIds() throws SQLException;
}
