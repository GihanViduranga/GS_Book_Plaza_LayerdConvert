package lk.gsbp.repository;

import lk.gsbp.dao.custom.impl.ItemDAOImpl;
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

public class ItemRepo {
    public static boolean update(Item item) throws SQLException {
        /*String sql = "UPDATE Items SET ItemName =?, QTY =?, UnitPrice =?,StockId =? WHERE ItemsId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, itemDTO.getItemName());
        pstm.setString(2, itemDTO.getQTY());
        pstm.setString(3, itemDTO.getUnitPrice());
        pstm.setString(4, itemDTO.getItemsId());
        pstm.setString(5, itemDTO.getStockId());

        return pstm.executeUpdate() > 0;*/
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.update(new Item(item.getItemName(),item.getQTY(),item.getUnitPrice(),item.getItemsId(),item.getStockId()));
    }

    public static boolean Update2(String ItemsId, String ItemName, String QTY, String UnitPrice, String stockId) throws SQLException {
        /*String sql = "UPDATE Items SET ItemName =?, QTY =?, UnitPrice =?,StockId = ? WHERE ItemsId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);


        pstm.setString(1, ItemName);
        pstm.setString(2, QTY);
        pstm.setObject(3, UnitPrice);
        pstm.setObject(4, ItemsId);
        pstm.setObject(5, stockId);

        return pstm.executeUpdate() > 0;*/
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.update2(new Item(ItemName,QTY,UnitPrice,ItemsId,stockId));
    }

    public static List<Item> getItemID() throws SQLException {
        /*String sql = "SELECT * FROM Items";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> ItemIds = new ArrayList<String>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            String ItemsId = resultSet.getString(1);
            ItemIds.add(ItemsId);
        }
        return ItemIds;*/
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.getIds();
    }

    public static Item searchById(String id) throws SQLException {
        /*String sql = "SELECT * FROM Items WHERE ItemsId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new ItemDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;*/
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.searchById(id);
    }

    public static List<Item> getAll() throws SQLException {
        /*String sql = "SELECT * FROM Items";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<ItemDTO> itemDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String ItemId = resultSet.getNString(1);
            String ItemName = resultSet.getNString(2);
            String QTY = resultSet.getNString(3);
            String UnitPrice = resultSet.getNString(4);
            String StockId = resultSet.getNString(5);

            ItemDTO itemDTO = new ItemDTO(ItemId, ItemName, QTY, UnitPrice, StockId);

            itemDTOS.add(itemDTO);
        }
        return itemDTOS;*/
        ItemDAOImpl itemDAO = new ItemDAOImpl();
        return itemDAO.getAll();
    }

    /*public static boolean update(List<orderDetailsDTO> odList) throws SQLException {
        for (orderDetailsDTO od : odList) {
            boolean isUpdateQty = updateQty(od.getItemId(), od.getQty());
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean updateQty(String itemCode, int qty) throws SQLException {
        System.out.println(qty);
        String sql = "UPDATE items SET QTY = (QTY - ?) WHERE ItemsId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setInt(1, qty);
        pstm.setString(2, itemCode);

        return pstm.executeUpdate() > 0;
    }*/

    /*public static String GetItemIds() throws SQLException {
        String sql = "SELECT ItemsId FROM items ORDER BY ItemsId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String ItemId = resultSet.getString(1);
            return ItemId;
        }
        return null;
    }*/
}
