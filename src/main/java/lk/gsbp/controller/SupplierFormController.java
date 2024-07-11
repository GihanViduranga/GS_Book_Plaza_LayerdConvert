package lk.gsbp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import lk.gsbp.bo.BOFactory;
import lk.gsbp.bo.custom.SupplierBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.SupplierDAO;
import lk.gsbp.dao.custom.impl.SupplierDAOImpl;
import lk.gsbp.entity.Supplier;
import lk.gsbp.model.SupplierDTO;
import lk.gsbp.tm.SupplierTm;

import java.sql.SQLException;
import java.util.List;

//import static sun.security.krb5.internal.crypto.KeyUsage.isValid;

public class SupplierFormController {
    public TextField txtSupplierID;
    public TextField txtSupplierName;
    public TextField txtContact;
    public TextField txtProduct;
    public TableView tblSupplier;

    public TextField txtItemName;
    public TextField txtQTY;
    public Label lblNetTotal;
    public AnchorPane SupplierRootNode;
    public TableColumn<?,?> colSupplierID;
    public TableColumn<?,?> colSupplierName;
    public TableColumn<?,?> colContact;
    public TableColumn<?,?> colProduct;
    public TableColumn<?,?> colQTY;

   // SupplierBO supplierBO = (SupplierBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.SUPPLIER);
    SupplierDAO supplierBO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    public void txtSupplierSearchOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String id = txtSupplierID.getText();

        Supplier supplier = supplierBO.searchById(id);

        if (supplier!= null){
            txtSupplierID.setText(supplier.getSupplierId());
            txtSupplierName.setText(supplier.getSuppName());
            txtContact.setText(supplier.getContact());
            txtProduct.setText(supplier.getProduct());
            txtItemName.setText(supplier.getItemName());
            txtQTY.setText(supplier.getQty());
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier not found").show();
        }
    }

    public void btnSaveOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String SupplierId = txtSupplierID.getText();
        String  SuppName= txtSupplierName.getText();
        String Contact = txtContact.getText();
        String Product = txtProduct.getText();
        String ItemName = txtItemName.getText();
        String Qty = txtQTY.getText();

        if (isValid()){
            Supplier supplier = new Supplier(SupplierId,SuppName,Contact,Product,ItemName,Qty);

            boolean isSaved = supplierBO.save(supplier);

            if (isSaved){
                new Alert(Alert.AlertType.INFORMATION, "Supplier saved successfully").show();
                initialize();
            }else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not saved").show();
            }
        }
    }

    private boolean isValid() {
        return true;
    }

    public void btnClearOnAction(ActionEvent actionEvent) {
        txtSupplierID.setText("");
        txtSupplierName.setText("");
        txtContact.setText("");
        txtProduct.setText("");
        txtItemName.setText("");
        txtQTY.setText("");
    }

    public void btnUpdateOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String SupplierId = txtSupplierID.getText();
        String SuppName= txtSupplierName.getText();
        String Contact = txtContact.getText();
        String Product = txtProduct.getText();
        String ItemName = txtItemName.getText();
        String Qty = txtQTY.getText();

        if (isValid()){
            Supplier supplier = new Supplier(SupplierId,SuppName,Contact,Product,ItemName, Qty);

            boolean isUpdated = supplierBO.update(supplier);

            if (isUpdated){
                new Alert(Alert.AlertType.INFORMATION, "Supplier updated successfully").show();
                initialize();
            }else {
                new Alert(Alert.AlertType.ERROR, "Supplier Not updated").show();
            }
        }
    }

    public void btnDeleteOnAction(ActionEvent actionEvent) throws SQLException, ClassNotFoundException {
        String SupplierId = txtSupplierID.getText();

        boolean isDeleted = supplierBO.delete(SupplierId);

        if (isDeleted){
            new Alert(Alert.AlertType.INFORMATION, "Supplier deleted successfully").show();
            initialize();
        }else {
            new Alert(Alert.AlertType.ERROR, "Supplier Not deleted").show();
        }
    }

    public void initialize(){
        getCurrentSupplierId();
        setCellValueFactory();
        loadAllSupplier();
    }

    private void loadAllSupplier() {
        ObservableList<SupplierTm> objects = FXCollections.observableArrayList();

        try {

            List<Supplier> supplierList = supplierBO.getAll();
            for (Supplier supplier : supplierList) {
                SupplierTm tm = new SupplierTm(
                        supplier.getSupplierId(),
                        supplier.getSuppName(),
                        supplier.getContact(),
                        supplier.getProduct(),
                        supplier.getQty()
                );

                objects.add(tm);
            }

            tblSupplier.setItems(objects);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        colSupplierID.setCellValueFactory(new PropertyValueFactory<>("SupplierId"));
        colSupplierName.setCellValueFactory(new PropertyValueFactory<>("SupplierName"));
        colContact.setCellValueFactory(new PropertyValueFactory<>("Contact"));
        colProduct.setCellValueFactory(new PropertyValueFactory<>("Product"));
        colQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
    }

    private String getCurrentSupplierId() {
        try {
            String SupplierId = supplierBO.getCurrentSupId();

            String nextSupplierId = generateNextAssestId();
            txtSupplierID.setText(nextSupplierId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    private String generateNextAssestId() throws SQLException, ClassNotFoundException {
        return supplierBO.generateNewSupplierID();
    }

    public static String splitCustomerId(String string) {
        if (string != null) {
            String[] strings = string.split("S0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2) {
                return "S00" + id;
            } else {
                if (length < 3) {
                    return "S0" + id;
                } else {
                    return "S" + id;
                }
            }
        }
        return "S001";
    }

}
