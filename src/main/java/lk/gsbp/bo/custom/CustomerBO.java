package lk.gsbp.bo.custom;

import lk.gsbp.bo.SuperBO;
import lk.gsbp.entity.Customer;
import lk.gsbp.model.CustomerDTO;


import java.sql.SQLException;
import java.util.List;

public interface CustomerBO extends SuperBO {
    public List<CustomerDTO> getAllCustomer() throws SQLException;
    public List<CustomerDTO> getCustomerIds() throws SQLException;
    public boolean updateCustomer(CustomerDTO dto) throws SQLException;
    public boolean update2Customer(CustomerDTO customer) throws SQLException;
    public Customer searchByCustomerId(String customerId) throws SQLException;
    public boolean saveCustomer(CustomerDTO customer) throws SQLException;
    public boolean deleteCustomer(String id) throws SQLException;
}
