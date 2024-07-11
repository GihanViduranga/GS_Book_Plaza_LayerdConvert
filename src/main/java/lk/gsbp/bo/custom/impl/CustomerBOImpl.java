package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.custom.CustomerBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.CustomerDAO;
import lk.gsbp.entity.Customer;
import lk.gsbp.model.CustomerDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CustomerBOImpl implements CustomerBO {
    CustomerDAO customerDAO = (CustomerDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);

    @Override
    public List<CustomerDTO> getAllCustomer() throws SQLException {
        List<Customer> all = customerDAO.getAll();
        List<CustomerDTO> allCustomers = new ArrayList();

        for (Customer customer : all){
            allCustomers.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));
        }
        return allCustomers;
    }

    @Override
    public List<CustomerDTO> getCustomerIds() throws SQLException {
        List<CustomerDTO> allCustomers = new ArrayList();
        List<Customer> all = customerDAO.getIds();

        for (Customer customer : all){
            allCustomers.add(new CustomerDTO(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));
        }
        return allCustomers;
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws SQLException {
        return customerDAO.update(new Customer(dto.getCustomerId(), dto.getName(), dto.getAddress(), dto.getContact(), dto.getEmail()));
    }

    @Override
    public boolean update2Customer(CustomerDTO customer) throws SQLException {
        return customerDAO.update2(new Customer(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));
    }

    @Override
    public CustomerDTO searchByCustomerId(String customerId) throws SQLException {
        Customer customer = customerDAO.searchById(customerId);
        return new CustomerDTO(customer.getCustomerId(),customer.getName(),customer.getAddress(),customer.getContact(),customer.getEmail());
    }

    @Override
    public boolean saveCustomer(CustomerDTO customer) throws SQLException {
        return customerDAO.save(new Customer(customer.getCustomerId(), customer.getName(), customer.getAddress(), customer.getContact(), customer.getEmail()));
    }

    @Override
    public boolean deleteCustomer(String id) throws SQLException {
        return customerDAO.delete(id);
    }
}
