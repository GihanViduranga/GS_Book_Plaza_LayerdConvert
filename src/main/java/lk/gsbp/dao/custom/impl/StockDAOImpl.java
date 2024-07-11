package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.StockDAO;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Stock;
import lk.gsbp.model.StockDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockDAOImpl implements StockDAO {
    @Override
    public List<Stock> getAll() throws SQLException {

        ResultSet resultSet = SQLUtil.execute("SELECT * FROM Stock");

        List<Stock> stockDTOList = new ArrayList<>();

        while (resultSet.next()) {
            String StockId = resultSet.getNString(1);
            String ItemName = resultSet.getNString(2);
            String CatogaryName = resultSet.getNString(3);
            String QTY = resultSet.getNString(4);

            Stock stock = new Stock(StockId, ItemName, CatogaryName,QTY);

            stockDTOList.add(stock);
        }
        return stockDTOList;
    }

    @Override
    public List<Stock> getIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(Stock entity) throws SQLException {
        return SQLUtil.execute("UPDATE Stock SET ItemName =?, CatogaryName =?, QTY =? WHERE StockId =?",entity.getStockId(),entity.getItemName(),entity.getCatogaryName(),entity.getQTY());
    }

    @Override
    public boolean update2(Stock entity) throws SQLException {
        return SQLUtil.execute("UPDATE Stock SET ItemName =?, CatogaryName =?, QTY =? WHERE StockId =?",entity.getStockId(),entity.getItemName(),entity.getCatogaryName(),entity.getQTY());
    }

    @Override
    public Stock searchById(String Id) throws SQLException {
        return SQLUtil.execute("SELECT * FROM stock WHERE  StockId = ?",Id);
    }

    @Override
    public boolean save(Stock entity) throws SQLException {
        return SQLUtil.execute("INSERT INTO stock (StockId,ItemName,CatogaryName,QTY) VALUES (?,?,?,?)",entity.getStockId(),entity.getItemName(),entity.getCatogaryName(),entity.getQTY());
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return SQLUtil.execute("DELETE FROM Stock WHERE StockId =?",id);
    }

    @Override
    public boolean update3(List<orderDetailsDTO> odList) throws SQLException {
        return false;
    }

    @Override
    public String getOrderIds() throws SQLException {
        return "";
    }

    @Override
    public List<String> getAllOrder() throws SQLException {
        return List.of();
    }
    public List<String> getAllStock() throws SQLException {
        List<String> Stockids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT StockId FROM stock");
        while (resultSet.next()) {
            Stockids.add(resultSet.getString(1));
        }
        return Stockids;
    }
    //BO eka karala na bo ekatath dalama controller walata danna
}
