package lk.gsbp.dao;

import lk.gsbp.model.orderDetailsDTO;

import java.sql.SQLException;
import java.util.List;

public interface CrudDAO<T> extends SuperDAO{
    public List<T> getAll() throws SQLException;
    public List<T> getIds() throws SQLException;
    public boolean update(T entity) throws SQLException;
    public boolean update2(T entity) throws SQLException;
    public T searchById(String Id) throws SQLException;
    public boolean save(T entity) throws SQLException;
    public boolean delete(String id) throws SQLException;
    public boolean update3(List<orderDetailsDTO> odList) throws SQLException;
    public String getOrderIds() throws SQLException;
    public  List<String> getAllOrder() throws SQLException;
}
