package lk.gsbp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import lk.gsbp.Utill.Regex;
import lk.gsbp.bo.BOFactory;
import lk.gsbp.bo.custom.CustomerBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.CustomerDAO;
import lk.gsbp.dao.custom.impl.CustomerDAOImpl;
import lk.gsbp.entity.Customer;
import lk.gsbp.model.CustomerDTO;
import lk.gsbp.tm.CustomerTm;

import java.sql.SQLException;
import java.util.List;

public class CustomerFormController {

    public AnchorPane root;
    public TextField txtCustomerId;
    public TextField txtCustomerAddress;
    public TextField txtCustomerContact;
    public TextField txtCustomerName;
    public TextField txtCustomerEmail;
    public TableView<CustomerTm> tblCusTable;
    public TableColumn colID;
    public TableColumn colName;
    public TableColumn colAddress;
    public TableColumn colContact;
    public TableColumn colEmail;

    CustomerDAOImpl customerDAO = (CustomerDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    CustomerBO customerBO = (CustomerBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CUSTOMER);

    public void btnCusSaveOnAction(ActionEvent actionEvent) {
        if (isValied()) {
            String id = txtCustomerId.getText();
            String name = txtCustomerName.getText();
            String address = txtCustomerAddress.getText();
            String contact = txtCustomerContact.getText();
            String email = txtCustomerEmail.getText();

            /*String sql = "INSERT INTO customer Values(?,?,?,?,?)";*/

            try {
                /*Connection connection = DbConnection.getInstance().getConnection();
                PreparedStatement pstm = connection.prepareStatement(sql);

                pstm.setString(1, id);
                pstm.setString(2, name);
                pstm.setString(3, address);
                pstm.setString(4, contact);
                pstm.setString(5, email);*/


                boolean isSaved = customerBO.saveCustomer(new CustomerDTO(id, name, address, contact, email));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Customer Saved Successfully").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Validation Error");
            alert.setHeaderText("Validation Failed");
            alert.setContentText("Please fill in all fields correctly.");
            alert.showAndWait();
        }
    }
    public void initialize() {
        setCellValueFactory();
        loadAllCustomers();
    }

    private void loadAllCustomers() {
        ObservableList<CustomerTm> objects = FXCollections.observableArrayList();

        try {
            List<CustomerDTO> customerList = customerBO.getAllCustomer();

            for (CustomerDTO customer : customerList) {
                CustomerTm customerTm = new CustomerTm(
                        customer.getCustomerId(),
                        customer.getName(),
                        customer.getAddress(),
                        customer.getContact(),
                        customer.getEmail()
                );
                objects.add(customerTm);
                tblCusTable.setItems(objects);

            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    private void setCellValueFactory() {
        colID.setCellValueFactory(new PropertyValueFactory<>("CustomerId"));
        colName.setCellValueFactory(new PropertyValueFactory<>("Name"));
        colAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colEmail.setCellValueFactory(new PropertyValueFactory<>("Email"));
    }

    public void btnCusClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtCustomerId.setText("");
        txtCustomerName.setText("");
        txtCustomerAddress.setText("");
        txtCustomerContact.setText("");
        txtCustomerEmail.setText("");
    }

    public void btnCusUpdateOnAction(ActionEvent actionEvent) {
        String CustomerId = txtCustomerId.getText();
        String Name = txtCustomerName.getText();
        String Address = txtCustomerAddress.getText();
        String Contact = txtCustomerContact.getText();
        String Email = txtCustomerEmail.getText();

        /*String sql = "UPDATE customer SET Name =?, Address =?, Contact =?, Email =? WHERE CustomerId =?";*/

       try {
           boolean isUpdate = customerBO.updateCustomer(new CustomerDTO(CustomerId, Name, Address, Contact, Email));
           if (isUpdate) {
               new Alert(Alert.AlertType.INFORMATION, "Customer Updated Successfully").show();
           }else {
               new Alert(Alert.AlertType.ERROR, "Customer Not Updated").show();
           }
       } catch (SQLException e) {
           new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
       }
    }

    public void btnCusDeleteOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();

        /*String sql = "DELETE FROM customer WHERE CustomerId =?";*/

        try {
            /*Connection connection = DbConnection.getInstance().getConnection();
            PreparedStatement pstm = connection.prepareStatement(sql);

            pstm.setString(1,id);*/


            boolean isDeleted = customerBO.deleteCustomer(id);
            if (isDeleted) {
                new Alert(Alert.AlertType.INFORMATION, "Customer Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }


    public void txtSearchOnAction(ActionEvent actionEvent) {
        String id = txtCustomerId.getText();

       /* String sql = "SELECT * FROM customer WHERE CustomerId =?";*/

        try {
            //System.out.println(id);
            Customer customer = customerBO.searchByCustomerId(id);

            if (customer != null){
                txtCustomerName.setText(customer.getName());
                txtCustomerAddress.setText(customer.getAddress());
                txtCustomerContact.setText(customer.getContact());
                txtCustomerEmail.setText(customer.getEmail());
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION,"Customer ID Not Found!").show();
        }
    }

    public boolean isValied(){
        boolean IdValid = Regex.setTextColor(lk.gsbp.Utill.TextField.IDC, txtCustomerId);
        boolean nameValid = Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtCustomerName);
        boolean addressValid = Regex.setTextColor(lk.gsbp.Utill.TextField.ADDRESS, txtCustomerAddress);
        boolean contactValid = Regex.setTextColor(lk.gsbp.Utill.TextField.CONTACT, txtCustomerContact);
        boolean emailValid = Regex.setTextColor(lk.gsbp.Utill.TextField.EMAIL, txtCustomerEmail);

        return IdValid && nameValid && addressValid && contactValid && emailValid;
    }
    public void CustomerIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.IDC, txtCustomerId);
    }

    public void CustomerAddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.ADDRESS, txtCustomerAddress);
    }

    public void CustomerContactOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.CONTACT, txtCustomerContact);
    }

    public void CustomerNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtCustomerName);
    }

    public void CustomerEmailOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.EMAIL, txtCustomerEmail);
    }
}
