package lk.gsbp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import lk.gsbp.Utill.Regex;
import lk.gsbp.bo.BOFactory;
import lk.gsbp.bo.custom.ItemBO;
import lk.gsbp.bo.custom.StockBO;
import lk.gsbp.bo.custom.impl.StockBOImpl;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.impl.ItemDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Item;
import lk.gsbp.model.ItemDTO;
import lk.gsbp.model.StockDTO;
import lk.gsbp.tm.ItemTm;
import lk.gsbp.repository.ItemRepo;
import lk.gsbp.repository.StockRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static lk.gsbp.repository.ItemRepo.Update2;

public class ItemFormController {

        @FXML
        public ComboBox <String> cmbStocId;
        @FXML
        private AnchorPane ItemRoot;

        @FXML
        private TextField txtId;

        @FXML
        private TextField txtQTY;

        @FXML
        private TextField txtName;

        @FXML
        private TextField txtPrice;

        @FXML
        private TableView<ItemTm> tblItemTable;

        @FXML
        private TableColumn<?, ?> tblID;

        @FXML
        private TableColumn<?, ?> tblName;

        @FXML
        private TableColumn<?, ?> tblQTY;

        @FXML
        private TableColumn<?, ?> tblUnitPrice;

        ItemBO itemBO = (ItemBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.ITEM);
        StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);

    public void initialize() {
        setCellValueFactory();
        loadAllItems();
        setStockId();
        getCurrentOrderId();
    }

    private void loadAllItems() {
        ObservableList<ItemTm> ItemList = FXCollections.observableArrayList();

        try {
            List<ItemDTO> itemDTOS = itemBO.getAllItems();

            for (ItemDTO item : itemDTOS) {
                ItemTm itemTm = new ItemTm(
                        item.getItemsId(),
                        item.getItemName(),
                        item.getQTY(),
                        item.getUnitPrice()
                );
                ItemList.add(itemTm);
                tblItemTable.setItems(ItemList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tblID.setCellValueFactory(new PropertyValueFactory<>("ItemsId"));
        tblName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        tblQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));
        tblUnitPrice.setCellValueFactory(new PropertyValueFactory<>("UnitPrice"));
    }

    @FXML
        void btnBackOnAction(ActionEvent event) throws IOException {
            AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/View/dashboard_form.fxml"));
            Stage stage = (Stage) ItemRoot.getScene().getWindow();

            stage.setScene(new Scene(rootNode));
            stage.setTitle("Gs Book Plaza Dashboard");
            stage.centerOnScreen();
        }

        @FXML
        void btnClearOnAction(ActionEvent event) {
            ClearFields();
        }
    private void ClearFields() {
        txtId.setText("");
        txtQTY.setText("");
        txtName.setText("");
        txtPrice.setText("");
    }

        @FXML
        void btnDeleteOnAction(ActionEvent event) {
            String id = txtId.getText();

            try {
                boolean isDeleted = itemBO.deleteItem(id);
                if (isDeleted){
                    new Alert(Alert.AlertType.INFORMATION, "Item Deleted Successfully").show();
                    ClearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }

        @FXML
        void btnSaveOnAction(ActionEvent event) {
            if (isValied()){
            String id = txtId.getText();
            String qty = txtQTY.getText();
            String name = txtName.getText();
            String price = txtPrice.getText();
            String stockId = cmbStocId.getSelectionModel().getSelectedItem();

            try{
                boolean isSaved = itemBO.saveItem(new ItemDTO(id,qty,name,price,stockId));
                if (isSaved){
                    new Alert(Alert.AlertType.INFORMATION, "Item Saved Successfully").show();
                    ClearFields();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setTitle("Validation Error");
                alert.setHeaderText("Validation Failed");
                alert.setContentText("Please fill in all fields correctly.");
                alert.showAndWait();
            }
        }

        @FXML
        void btnUpdateOnAction(ActionEvent event) {
            String Id = txtId.getText();
            String Name = txtName.getText();
            String Qty = txtQTY.getText();
            String Price = txtPrice.getText();
            String stockId = cmbStocId.getSelectionModel().getSelectedItem();

            String sql = "UPDATE items SET ItemName =?, QTY =?, UnitPrice =? WHERE ItemsId =?";

            try {
                boolean isUpdate = itemBO.update2Item(new ItemDTO(Id, Name, Qty, Price,stockId));
                if (isUpdate) {
                    new Alert(Alert.AlertType.INFORMATION, "Item updated").show();
                } else {
                    new Alert(Alert.AlertType.ERROR, "Item is Not updated").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
            }

        }

        @FXML
        void txtItemSearchOnAction(ActionEvent event) {
            String id = txtId.getText();

            try {
                ItemDTO item = itemBO.searchByItemId(id);
                if (item != null) {
                    txtName.setText(item.getItemName());
                    txtQTY.setText(item.getQTY());
                    txtPrice.setText(item.getUnitPrice());

                } else {
                    new Alert(Alert.AlertType.ERROR, "Item not found").show();
                }
            } catch (SQLException e) {
                new Alert(Alert.AlertType.INFORMATION, "Item Id not found").show();
            }

        }
    public boolean isValied(){
        boolean idValid = Regex.setTextColor(lk.gsbp.Utill.TextField.IDI, txtId);
        boolean nameValid = Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtName);
        boolean qtyValid = Regex.setTextColor(lk.gsbp.Utill.TextField.QTY, txtQTY);

        return idValid && nameValid && qtyValid;

    }

    public void ItemIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.IDI, txtId);
    }

    public void QTYOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.QTY, txtQTY);
    }

    public void NameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtName);
    }

    public void cmbStockIdOnAction(ActionEvent actionEvent) {
        String id = cmbStocId.getValue();

        try {
            StockDTO stockDTO = stockBO.searchById(id);
            if (stockDTO != null) {
                txtName.setText(stockDTO.getItemName());
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public void setStockId() {
        ObservableList<String> StockIdList = FXCollections.observableArrayList();

        try {
            List<String> stockList =stockBO.getAllStock();

            for (String stock : stockList) {
                StockIdList.add(stock);
            }
            cmbStocId.setItems(StockIdList);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void getCurrentOrderId() {
        try {
            String itemId = itemBO.GetItemIds();

            String nextItemId = generateNextAssestId();
            txtId.setText(nextItemId);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public String generateNextAssestId() throws SQLException {
        ResultSet resultSet = SQLUtil.execute("SELECT ItemsId FROM items ORDER BY ItemsId DESC LIMIT 1");
        if(resultSet.next()) {
            return splitItemId(resultSet.getString(1));
        }
        return splitItemId(null);
    }

    private static String splitItemId(String string) {
        if(string != null) {
            String[] strings = string.split("I0");
            int id = Integer.parseInt(strings[1]);
            id++;
            String ID = String.valueOf(id);
            int length = ID.length();
            if (length < 2){
                return "I00"+id;
            }else {
                if (length < 3){
                    return "I0"+id;
                }else {
                    return "I"+id;
                }
            }
        }
        return "I001";
    }
}
