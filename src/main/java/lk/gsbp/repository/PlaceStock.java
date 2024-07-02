package lk.gsbp.repository;

import lk.gsbp.model.StockDTO;
import lk.gsbp.model.stockDetailsDTO;

import java.util.List;

public class PlaceStock {
    private StockDTO stockDTO;
    private List<stockDetailsDTO> stList;

    public PlaceStock() {
    }

    public PlaceStock(StockDTO stockDTO, List<stockDetailsDTO> stList) {
        this.stockDTO = stockDTO;
        this.stList = stList;
    }

    public StockDTO getStock() {
        return stockDTO;
    }

    public void setStock(StockDTO stockDTO) {
        this.stockDTO = stockDTO;
    }

    public List<stockDetailsDTO> getStList() {
        return stList;
    }

    public void setStList(List<stockDetailsDTO> stList) {
        this.stList = stList;
    }

    @Override
    public String toString() {
        return "PlaceStock{" +
                "stock=" + stockDTO +
                ", stList=" + stList +
                '}';
    }
}
