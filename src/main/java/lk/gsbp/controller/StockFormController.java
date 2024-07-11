package lk.gsbp.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
import lk.gsbp.bo.custom.StockBO;
import lk.gsbp.dao.SQLUtil;
import lk.gsbp.dao.custom.StockDAO;
import lk.gsbp.dao.custom.impl.StockDAOImpl;
import lk.gsbp.db.DbConnection;
import lk.gsbp.entity.Stock;
import lk.gsbp.model.StockDTO;
import lk.gsbp.tm.StockTm;
import lk.gsbp.repository.StockRepo;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;


public class StockFormController {

    public TableColumn tblStockId;
    public TableColumn tblItemName;
    public TableColumn tblQTY;
    public TableView tblStock;
    @FXML
    private AnchorPane StockRoot;

    @FXML
    private TextField txtStokeId;

    @FXML
    private TextField txtCatogaryName;

    @FXML
    private TextField txtQTY;

    @FXML
    private TableColumn<?, ?> tblCatagoryName;

    @FXML
    private TextField txtItemName;

    StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);

    public void initialize()  {
        setCellValueFactory();
        loadAllStocks();
    }

    private void loadAllStocks() {
        ObservableList<StockTm> StockList = FXCollections.observableArrayList();

        try {
            List<StockDTO> stockDTOList = stockBO.getAll();

            for (StockDTO stockDTO : stockDTOList) {
                StockTm stockTm = new StockTm(
                        stockDTO.getStockId(),
                        stockDTO.getItemName(),
                        stockDTO.getCatogaryName(),
                        stockDTO.getQTY()
                );
                StockList.add(stockTm);
                tblStock.setItems(StockList);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private void setCellValueFactory() {
        tblStockId.setCellValueFactory(new PropertyValueFactory<>("StockId"));
        tblItemName.setCellValueFactory(new PropertyValueFactory<>("ItemName"));
        tblCatagoryName.setCellValueFactory(new PropertyValueFactory<>("CatogaryName"));
        tblQTY.setCellValueFactory(new PropertyValueFactory<>("QTY"));

    }

    @FXML
    void btnBackOnAction(ActionEvent event) throws IOException {
        AnchorPane rootNode = FXMLLoader.load(getClass().getResource("/View/dashboard_form.fxml"));
        Stage stage = (Stage) StockRoot.getScene().getWindow();

        stage.setScene(new Scene(rootNode));
        stage.setTitle("Gs Book Plaza Dashboard");
        stage.centerOnScreen();
    }

    @FXML
    void btnClearOnAction(ActionEvent event) {
        ClearFields();
    }

    private void ClearFields() {
        txtStokeId.setText("");
        txtCatogaryName.setText("");
        txtQTY.setText("");
        txtItemName.setText("");
    }

    @FXML
    void btnDeleteOnAction(ActionEvent event) throws SQLException {
        String Id = txtStokeId.getText();

        try {
            boolean isDeleted = stockBO.delete(Id);
            if (isDeleted){
                new Alert(Alert.AlertType.INFORMATION, "Stock Deleted Successfully").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.ERROR, e.getMessage()).show();
        }
    }

    @FXML
    void btnSaveOnAction(ActionEvent event) throws SQLException {
        if (isValied()){
        String stockId = txtStokeId.getText();
        String itemName = txtItemName.getText();
        String catogaryName = txtCatogaryName.getText();
        String QTY = txtQTY.getText();

            boolean isSaved = false;
            try {
                isSaved = stockBO.save(new StockDTO(stockId,itemName,catogaryName,QTY));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            if (isSaved) {
                new Alert(Alert.AlertType.INFORMATION, "Stock Saved Successfully").show();
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
        String StockId = txtStokeId.getText();
        String itemName = txtItemName.getText();
        String catogaryName = txtCatogaryName.getText();
        String QTY = txtQTY.getText();

        try {
            boolean isUpdate = stockBO.update(new StockDTO(StockId,itemName,catogaryName,QTY));
            if (isUpdate) {
                new Alert(Alert.AlertType.INFORMATION, "Stock Updated Successfully").show();
            }else {
                new Alert(Alert.AlertType.ERROR, "Stock Not Updated").show();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void txtStockeSearchOnAction(ActionEvent actionEvent) throws SQLException {
        String Id = txtStokeId.getText();

        try {
            StockDTO stock = stockBO.searchById(Id);

            if (stock != null) {
                txtCatogaryName.setText(stock.getCatogaryName());
                txtQTY.setText(stock.getQTY());
                txtItemName.setText(stock.getItemName());
            } else {
                new Alert(Alert.AlertType.ERROR, "Stock not found").show();
            }
        } catch (SQLException e) {
            new Alert(Alert.AlertType.INFORMATION, "Stock Id not found").show();
        }
    }

    public boolean isValied(){
        boolean idValid = Regex.setTextColor(lk.gsbp.Utill.TextField.IDS, txtStokeId);
        boolean nameValid = Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtItemName);
        boolean catogaryValid = Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtCatogaryName);
        boolean QTYValid = Regex.setTextColor(lk.gsbp.Utill.TextField.QTY, txtQTY);

        return idValid && nameValid && catogaryValid && QTYValid;
    }
    public void StockIdOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.IDS, txtStokeId);
    }

    public void CatagaryNameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtCatogaryName);
    }

    public void QtyOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.QTY, txtQTY);
    }

    public void NameOnKeyReleased(KeyEvent keyEvent) {
        Regex.setTextColor(lk.gsbp.Utill.TextField.NAME, txtItemName);
    }
}
