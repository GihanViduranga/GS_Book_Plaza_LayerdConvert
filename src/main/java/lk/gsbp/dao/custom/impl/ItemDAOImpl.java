package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.ItemDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Item;
import lk.gsbp.model.ItemDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemDAOImpl implements ItemDAO {
    @Override
    public List<Item> getAll() throws SQLException {
        /*String sql = "SELECT * FROM Items";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
*/
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Items");

        List<Item> item = new ArrayList<>();

        while (resultSet.next()) {
            String ItemId = resultSet.getNString(1);
            String ItemName = resultSet.getNString(2);
            String QTY = resultSet.getNString(3);
            String UnitPrice = resultSet.getNString(4);
            String StockId = resultSet.getNString(5);

            Item items = new Item(ItemId, ItemName, QTY, UnitPrice, StockId);

            item.add(items);
        }
        return item;
    }

    @Override
    public List<Item> getIds() throws SQLException {
        /*String sql = "SELECT * FROM Items";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/

        List<Item> ItemIds = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Items");
        while (resultSet.next()) {
            Item item = new Item(
                    resultSet.getString("ItemsId"),
                    resultSet.getString("ItemName"),
                    resultSet.getString("QTY"),
                    resultSet.getString("UnitPrice"),
                    resultSet.getString("StockId")
            );
            ItemIds.add(item);
        }
        return ItemIds;
    }

    @Override
    public boolean update(Item entity) throws SQLException {
        return SQLUtil.execute("UPDATE Items SET ItemName =?, QTY =?, UnitPrice =?,StockId =? WHERE ItemsId =?",entity.getItemName(),entity.getQTY(),entity.getUnitPrice(),entity.getStockId(),entity.getItemsId());
    }

    @Override
    public boolean update2(Item entity) throws SQLException {
        return SQLUtil.execute("UPDATE Items SET ItemName =?, QTY =?, UnitPrice =?,StockId = ? WHERE ItemsId =?",entity.getItemName(),entity.getQTY(),entity.getUnitPrice(),entity.getStockId(),entity.getItemsId());
    }

    @Override
    public Item searchById(String Id) throws SQLException {
        ResultSet rst = SQLUtil.execute("SELECT * FROM Items WHERE ItemsId = ?",Id);
        rst.next();
        return new Item(
                Id,
                rst.getString("ItemName"),
                rst.getString("QTY"),
                rst.getString("UnitPrice"),
                rst.getString("StockId")
        );
    }

    @Override
    public boolean save(Item entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO Items (ItemsId,ItemName,QTY,UnitPrice,StockId) VALUES(?,?,?,?,?)",entity.getItemsId(),entity.getQTY(),entity.getItemName(),entity.getUnitPrice(),entity.getStockId());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Items WHERE ItemsId =?",id);
    }
    @Override
    public boolean update3(List<orderDetailsDTO> odList) throws SQLException {
        for (orderDetailsDTO od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String getOrderIds() throws SQLException {
        return "";
    }

    @Override
    public List<String> getAllOrder() throws SQLException {
        return List.of();
    }

    public boolean updateQty(String itemCode, int qty) throws SQLException {
        String sql = "UPDATE items SET QTY = (QTY - ?) WHERE ItemsId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemCode);

        return pstm.executeUpdate() > 0;
    }

    public String GetItemIds() throws SQLException {
        /*String sql = "SELECT ItemsId FROM items ORDER BY ItemsId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/

        ResultSet resultSet = SQLUtil.execute("SELECT ItemsId FROM items ORDER BY ItemsId DESC LIMIT 1");

        if (resultSet.next()) {
            String ItemId = resultSet.getString(1);
            return ItemId;
        }
        return null;
    }
}
