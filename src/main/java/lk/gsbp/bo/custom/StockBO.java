package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.entity.Stock;
import lk.gsbp.model.StockDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface StockBO extends SuperBO {
    public List<StockDTO> getAll() throws SQLException;

       public List<StockDTO> getIds() throws SQLException;

       public boolean update(StockDTO entity) throws SQLException;

       public boolean update2(StockDTO entity) throws SQLException;

       public StockDTO searchById(String Id) throws SQLException;

       public boolean save(StockDTO entity) throws SQLException;

       public boolean delete(String id) throws SQLException;

       public boolean update3(List<orderDetailsDTO> odList) throws SQLException;

       public String getOrderIds() throws SQLException;


    public List<String> getAllOrder() throws SQLException;
    public List<String> getAllStock() throws SQLException;
}
