package lk.gsbp.tm;

import com.jfoenix.controls.JFXButton;
import lombok.*;


public class SupplierTm {
    private String SupplierId;
    private String SupplierName;
    private String Contact;
    private String Product;
    private String QTY;

    public SupplierTm() {
    }

    @Override
    public String toString() {
        return "SupplierTm{" +
                "SupplierId='" + SupplierId + '\'' +
                ", SupplierName='" + SupplierName + '\'' +
                ", Contact='" + Contact + '\'' +
                ", Product='" + Product + '\'' +
                ", QTY='" + QTY + '\'' +
                '}';
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public String getSupplierName() {
        return SupplierName;
    }

    public void setSupplierName(String supplierName) {
        SupplierName = supplierName;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        Contact = contact;
    }

    public String getProduct() {
        return Product;
    }

    public void setProduct(String product) {
        Product = product;
    }

    public String getQTY() {
        return QTY;
    }

    public void setQTY(String QTY) {
        this.QTY = QTY;
    }

    public SupplierTm(String supplierId, String supplierName, String contact, String product, String QTY) {
        SupplierId = supplierId;
        SupplierName = supplierName;
        Contact = contact;
        Product = product;
        this.QTY = QTY;
    }
}
