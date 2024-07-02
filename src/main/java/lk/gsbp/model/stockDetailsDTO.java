package lk.gsbp.model;

public class stockDetailsDTO {
    private String SupplierId;
    private int QTY;
    private int ItemPrice;
    private String stockId;

    @Override
    public String toString() {
        return "stockDetails{" +
                "SupplierId='" + SupplierId + '\'' +
                ", QTY=" + QTY +
                ", ItemPrice=" + ItemPrice +
                '}';
    }

    public String getSupplierId() {
        return SupplierId;
    }

    public void setSupplierId(String supplierId) {
        SupplierId = supplierId;
    }

    public int getQTY() {
        return QTY;
    }

    public void setQTY(int QTY) {
        this.QTY = QTY;
    }

    public int getItemPrice() {
        return ItemPrice;
    }

    public void setItemPrice(int itemPrice) {
        ItemPrice = itemPrice;
    }

    public stockDetailsDTO(String supplierId, int QTY, int itemPrice) {
        SupplierId = supplierId;
        this.QTY = QTY;
        ItemPrice = itemPrice;
    }

    public stockDetailsDTO() {
    }

    public String getStockId() {
        return stockId;
    }

    public void setStockId(String stockId) {
        this.stockId = stockId;
    }
}
