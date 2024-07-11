package lk.gsbp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.gsbp.Utill.Regex;
import lk.gsbp.bo.BOFactory;
import lk.gsbp.bo.custom.DeliveryBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.impl.DeliveryDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Delivery;
import lk.gsbp.model.DeliveryDTO;
import lk.gsbp.tm.DeliveryTm;
import lk.gsbp.repository.DeliveryRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DeliveryFormController {

    public AnchorPane deliveryRootNode;
    public TextField txtDeliveryId;
    public TextField txtDate;
    public TextField txtAddress;
    public TableColumn tblDeliveryId;
    public TableColumn tblAddress;
    public TableColumn tblDate;
    public TableColumn tblStetus;
    public TextField txtStatus;
    public TableView tblDelivery;
    public TextField txtDeliverName;
    public TableColumn tblDeliverName;

    DeliveryBO deliveryBO = (DeliveryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.DELIVERY);

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException {
        if (isValied()) {
            String Id = txtDeliveryId.getText();
            String Deliver = txtDeliverName.getText();
            String Date = txtDate.getText();
            String Address = txtAddress.getText();
            String Status = txtStatus.getText();


            try {
            boolean isSaved = deliveryBO.save(new DeliveryDTO(Id, Deliver, Date, Address, Status));
                if (isSaved) {
                    new Alert(Alert.AlertType.INFORMATION, "Delivery Saved Successfully").show();
                    clearFields();
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
        loadAllDeliveries();
    }

    private void loadAllDeliveries() {
        ObservableList<DeliveryTm> objects = FXCollections.observableArrayList();

        try{
            List<DeliveryDTO> deliveryDTOList = deliveryBO.getAll();

            for (DeliveryDTO deliveryDTO : deliveryDTOList) {
                DeliveryTm deliveryTm = new DeliveryTm(
                        deliveryDTO.getDeliveryId(),
                        deliveryDTO.getDeliverName(),
                        deliveryDTO.getDate(),
                        deliveryDTO.getAddress(),
                        deliveryDTO.getStetus()
                );
                objects.add(deliveryTm);
                tblDelivery.setItems(objects);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tblDeliveryId.setCellValueFactory(new PropertyValueFactory<>("DeliveryId"));
        tblDeliverName.setCellValueFactory(new PropertyValueFactory<>("DeliverName"));
        tblAddress.setCellValueFactory(new PropertyValueFactory<>("Address"));
        tblDate.setCellValueFactory(new PropertyValueFactory<>("Date"));
        tblStetus.setCellValueFactory(new PropertyValueFactory<>("Stetus"));
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        clearFields();
    }

    private void clearFields() {
        txtDeliveryId.setText("");
        txtDeliverName.setText("");
        txtDate.setText("");
        txtAddress.setText("");
        txtStatus.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) {
        String DeliveryId = txtDeliveryId.getText();
        String DeliverName = txtDeliverName.getText();
        String Date = txtDate.getText();
        String Address = txtAddress.getText();
        String Stetus = txtStatus.getText();

        try{
            boolean isUpdate = deliveryBO.update2(new DeliveryDTO(DeliveryId, DeliverName, Date, Address, Stetus));
            if (isUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Delivery Updated Successfully").show();
                clearFields();
            } else {
                new Alert(Alert.AlertType.ERROR, "Delivery Not Updated").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException {
        String DeliveryId = txtDeliveryId.getText();

        try{
            boolean isDelete = deliveryBO.delete(DeliveryId);
            if (isDelete) {
                new Alert(Alert.AlertType.INFORMATION, "Delivery Deleted Successfully").show();
                clearFields();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    public void txtDeliverySearchOnAction(ActionEvent actionEvent) throws SQLException {
        String DeliveryId = txtDeliveryId.getText();

        try {
            DeliveryDTO delivery = deliveryBO.searchById(DeliveryId);
            if (delivery != null) {
                txtDeliverName.setText(delivery.getDeliverName());
                txtDate.setText(delivery.getDate());
                txtAddress.setText(delivery.getAddress());
                txtStatus.setText(delivery.getStetus());
            } else {
                new Alert(Alert.AlertType.ERROR, "Delivery Not Found").show();
            }
        } catch (SQLException e){
            new Alert(Alert.AlertType.INFORMATION, "Delivery ID Not Found").show();
        }
    }

    public boolean isValied(){

        boolean nameValid = Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtDeliverName);
        boolean idValid = Regex.setTextColor(lk.gsbp.Utill.TextField.IDD, txtDeliveryId);
        boolean AddressValid = Regex.setTextColor(lk.gsbp.Utill.TextField.ADDRESS, txtAddress );
        boolean dateValid = Regex.setTextColor(lk.gsbp.Utill.TextField.DATE, txtDate);

        return nameValid && idValid && AddressValid && dateValid;
    }
    public void DateOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.DATE, txtDate);
    }

    public void AddressOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.ADDRESS, txtAddress);
    }

    public void DeliveryIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.IDD, txtDeliveryId);
    }

    public void DeliverNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtDeliverName);
    }
}
