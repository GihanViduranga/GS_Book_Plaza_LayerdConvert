package lk.gsbp.dao.custom;

import lk.gsbp.dao.CrudDAO;
import lk.gsbp.entity.Stock;

import java.sql.SQLException;
import java.util.List;

public interface StockDAO extends CrudDAO<Stock> {
    public List<String> getAllStock() throws SQLException;
}
