package lk.gsbp.repository;

import lk.gsbp.db.DbConnection;
import lk.gsbp.model.StockDTO;
import lk.gsbp.model.stockDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



public class StockRepo {

    public static List<StockDTO> getAll() throws SQLException {
        String sql = "SELECT * FROM Stock";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        List<StockDTO> stockDTOList = new ArrayList<>();

        while (resultSet.next()) {
            String StockId = resultSet.getNString(1);
            String ItemName = resultSet.getNString(2);
            String CatogaryName = resultSet.getNString(3);
            String QTY = resultSet.getNString(4);

            StockDTO stockDTO = new StockDTO(StockId, ItemName, CatogaryName,QTY);

            stockDTOList.add(stockDTO);
        }
        return stockDTOList;
    }

    public static boolean update2(String StockId, String ItemName, String CatogaryName, String QTY) throws SQLException {
        String sql = "UPDATE Stock SET ItemName =?, CatogaryName =?, QTY =? WHERE StockId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1, ItemName);
        pstm.setObject(2, CatogaryName);
        pstm.setObject(3, QTY);
        pstm.setObject(4, StockId);

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(StockDTO stockDTO) throws SQLException {
        String sql = "UPDATE Stock SET ItemName =?, CatogaryName =?, QTY =? WHERE StockId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, stockDTO.getItemName());
        pstm.setString(2, stockDTO.getCatogaryName());
        pstm.setString(3, stockDTO.getQTY());
        pstm.setObject(4, stockDTO.getStockId());

        return pstm.executeUpdate() > 0;
    }

    public static boolean update(List<stockDetailsDTO> stList) throws SQLException {
        for (stockDetailsDTO st : stList) {
            boolean isUpdateQty = UpdateQty(st.getStockId(), String.valueOf(st.getQTY()));
            if (!isUpdateQty) {
                return false;
            }
        }
        return true;
    }

    private static boolean UpdateQty(String StockId, String QTY) throws SQLException {
        String sql = "UPDATE stock SET QTY = (QTY + ?) WHERE StockId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, QTY);
        pstm.setString(2, StockId);

        return pstm.executeUpdate() > 0;
    }


    public static boolean save(StockDTO stockDTO) throws SQLException {
        String sql = "INSERT INTO stock (StockId,ItemName,CatogaryName,QTY) VALUES (?,?,?,?)";


            Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1, stockDTO.getStockId());
            pstm.setString(2, stockDTO.getItemName());
            pstm.setString(3, stockDTO.getCatogaryName());
            pstm.setString(4, stockDTO.getQTY());

            return pstm.executeUpdate() > 0;
    }

    public static String GetStockId() throws SQLException {
        String sql = "SELECT StockId FROM stock ORDER BY StockId DESC LIMIT 1";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            String stockId = resultSet.getString(1);
            return stockId;
        }
        return null;
    }

    public static StockDTO searchById(String id) throws SQLException {
        String sql = "SELECT * FROM stock WHERE  StockId = ?";
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, id);
        ResultSet resultSet = pstm.executeQuery();

        if (resultSet.next()) {
            return new StockDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4)
            );
        }
        return null;
    }

    public static List<String> getAllStock() throws SQLException {
        String sql = "SELECT StockId FROM stock";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> Stockids = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            Stockids.add(resultSet.getString(1));
        }
        return Stockids;
    }
}