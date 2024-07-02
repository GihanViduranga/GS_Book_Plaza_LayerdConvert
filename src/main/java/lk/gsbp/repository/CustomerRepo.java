package lk.gsbp.repository;

import lk.gsbp.dao.custom.CustomerDAO;
import lk.gsbp.dao.custom.impl.CustomerDAOImpl;
import lk.gsbp.entity.Customer;
import lk.gsbp.model.CustomerDTO;

import java.sql.SQLException;
import java.util.List;

public class CustomerRepo {
    public static List<Customer> getAll() throws SQLException {
        /*String sql = "SELECT * FROM customer";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);
        ResultSet resultSet = pstm.executeQuery();

        List<CustomerDTO> customerDTOS = new ArrayList<>();

        while (resultSet.next()) {
            String CustomerId = resultSet.getNString(1);
            String Name = resultSet.getNString(2);
            String Address = resultSet.getNString(3);
            String Contact = resultSet.getNString(4);
            String Email = resultSet.getNString(5);

            CustomerDTO customerDTO = new CustomerDTO(CustomerId, Name, Address, Contact, Email);

            customerDTOS.add(customerDTO);
        }
        return customerDTOS;*/
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        return customerDAO.getAll();
    }
    public static List<Customer> getIds() throws SQLException {
        /*String sql = "SELECT CustomerId FROM customer";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        List<String> ids = new ArrayList<>();

        ResultSet resultSet = pstm.executeQuery();
        while (resultSet.next()) {
            ids.add(resultSet.getString(1));
        }
        return ids;*/
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        return customerDAO.getIds();
    }

    public static void update (Customer customer) throws SQLException {
        /*String sql = "UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

            pstm.setString(1, customerDTO.getName());
            pstm.setString(2, customerDTO.getAddress());
            pstm.setString(3, customerDTO.getContact());
            pstm.setString(4, customerDTO.getEmail());
            pstm.setString(5, customerDTO.getCustomerId());

            return pstm.executeUpdate() > 0;*/
        CustomerDAOImpl customerDAO = new CustomerDAOImpl();
        customerDAO.update(new Customer(customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail(),customer.getCustomerId()));

    }

        public static boolean update2(String customerId, String Name, String Address, String Contact, String Email) throws SQLException {
        /*String sql = "UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?";

        Connection connection = DbConnection.getInstance().getConnection();
        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setObject(1,Name);
        pstm.setObject(2,Address);
        pstm.setObject(3,Contact);
        pstm.setObject(4,Email);
        pstm.setObject(5,customerId);

        return pstm.executeUpdate() > 0;*/
            CustomerDAOImpl customerDAO = new CustomerDAOImpl();
            return customerDAO.update2(new Customer(customerId,Name,Address,Contact,Email));
        }

    public static Customer searchByCustomerID(String customerId) throws SQLException {
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
        CustomerDAO customerDAO = new CustomerDAOImpl();
        return customerDAO.searchById(customerId);
    }
}
