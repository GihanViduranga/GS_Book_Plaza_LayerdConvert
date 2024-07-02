package lk.gsbp.dao.custom.impl;

import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.CustomerDAO;
import lk.gsbp.entity.Customer;
import lk.gsbp.model.CustomerDTO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerDAOImpl implements CustomerDAO {
    @Override
    public List<Customer> getAll() throws SQLException {
       /* String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
        ResultSet resultSet = SQLUtil.execute("SELECT * FROM customer");

        List<Customer> customers = new ArrayList<>();

        while (resultSet.next()) {
            String CustomerId = resultSet.getNString(1);
            String Name = resultSet.getNString(2);
            String Address = resultSet.getNString(3);
            String Contact = resultSet.getNString(4);
            String Email = resultSet.getNString(5);

            Customer customer = new Customer(CustomerId, Name, Address, Contact, Email);

            customers.add(customer);
        }
        return customers;
    }
    @Override
    public List<Customer> getIds() throws SQLException {
        /*String sql = "SELECT CustomerId FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/

        List<Customer> ids = new ArrayList<>();

        ResultSet resultSet = SQLUtil.execute("SELECT CustomerId FROM customer");
        while (resultSet.next()) {
            Customer customer = new Customer(resultSet.getString("CustomerId"),resultSet.getString("Name"),resultSet.getString("Address"),resultSet.getString("Contact"),resultSet.getString("Email"));
            ids.add(customer);
        }
        return ids;
    }
    @Override
    public boolean update(Customer dto) throws SQLException {
        /*String sql = "UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, dto.getName());
        pstm.setString(2, dto.getAddress());
        pstm.setString(3, dto.getContact());
        pstm.setString(4, dto.getEmail());
        pstm.setString(5, dto.getCustomerId());

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?",dto.getName(),dto.getAddress(),dto.getContact(),dto.getEmail(),dto.getCustomerId());
    }
    @Override
    public boolean update2(Customer customer) throws SQLException {
       /* String sql = "UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);*/
        /*pstm.setObject(1,Name);
        pstm.setObject(2,Address);
        pstm.setObject(3,Contact);
        pstm.setObject(4,Email);
        pstm.setObject(5,customerId);*/

        //return pstm.executeUpdate() > 0;
        return SQLUtil.execute("UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?",customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail(),customer.getCustomerId());
    }
    @Override
    public Customer searchById(String customerId) throws SQLException {
        /*String sql = "SELECT * FROM customer WHERE CustomerId = ?";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, customerId);
        ResultSet resultSet = pstm.executeQuery();
        if (resultSet.next()) {
            return new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getString(4),
                    resultSet.getString(5)
            );
        }
        return null;*/
        ResultSet rst = SQLUtil.execute("SELECT * FROM customer WHERE CustomerId = ?",customerId);
        rst.next();
        return new Customer(customerId, rst.getString("Name"),rst.getString("Address"),rst.getString("Contact"),rst.getString("Email"));
    }
    @Override
    public boolean save(Customer customer) throws SQLException {
        /*String sql = "INSERT INTO customer Values(?,?,?,?,?)";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1, id);
        pstm.setString(2, name);
        pstm.setString(3, address);
        pstm.setString(4, contact);
        pstm.setString(5, email);

        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("INSERT INTO customer VALUES(?,?,?,?,?)",customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail());
    }
    @Override
    public boolean delete(String id) throws SQLException {
       /* String sql = "DELETE FROM customer WHERE CustomerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = connection.prepareStatement(sql);

        pstm.setString(1,id);
        return pstm.executeUpdate() > 0;*/
        return SQLUtil.execute("DELETE FROM customer WHERE CustomerId =?",id);
    }
}
