package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.custom.StockBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.StockDAO;
import lk.gsbp.entity.Stock;
import lk.gsbp.model.StockDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StockBOImpl implements StockBO {
    StockDAO stockDAO = (StockDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.STOCK);
    @Override
    public List<StockDTO> getAll() throws SQLException {
        List<StockDTO> allStock = new ArrayList();
        List<Stock> all = stockDAO.getAll();

        for (Stock stock : all) {
            allStock.add(new StockDTO(stock.getStockId(),stock.getItemName(),stock.getCatogaryName(),stock.getQTY()));
        }
        return allStock;
    }

    @Override
    public List<StockDTO> getIds() throws SQLException {
        return List.of();
    }

    @Override
    public boolean update(StockDTO entity) throws SQLException {
        return stockDAO.update(new Stock(entity.getStockId(), entity.getItemName(),entity.getCatogaryName(),entity.getQTY()));
    }

    @Override
    public boolean update2(StockDTO entity) throws SQLException {
        return stockDAO.update(new Stock(entity.getStockId(), entity.getItemName(),entity.getCatogaryName(),entity.getQTY()));
    }

    @Override
    public StockDTO searchById(String Id) throws SQLException {
       // return stockDAO.searchById(Id);
        Stock stock =stockDAO.searchById(Id);
        return new StockDTO(stock.getStockId(), stock.getItemName(), stock.getCatogaryName(), stock.getQTY());
    }

    @Override
    public boolean save(StockDTO entity) throws SQLException {
        return stockDAO.save(new Stock(entity.getStockId(),entity.getItemName(),entity.getCatogaryName(),entity.getQTY()));
    }

    @Override
    public boolean delete(String id) throws SQLException {
        return stockDAO.delete(id);
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

    @Override
    public List<String> getAllStock() throws SQLException {
        return stockDAO.getAllStock();
    }
}
