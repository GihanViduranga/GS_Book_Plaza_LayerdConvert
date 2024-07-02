package lk.gsbp.repository;

import lk.gsbp.db.DbConnection;
import lk.gsbp.model.stockDetailsDTO;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class StockDetailRepo {
    public static boolean save(List<stockDetailsDTO> stList) throws SQLException {
        for (stockDetailsDTO st : stList) {
            boolean isSaved = save(st);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }
    private static boolean save(stockDetailsDTO st) throws SQLException {
        String sql = "INSERT INTO stockdetails(SupplierId,StockId,QTY) VALUES(?, ?, ?)";

        PreparedStatement pstm = DbConnection.getInstance().getConnection().prepareStatement(sql);

        pstm.setString(1, st.getSupplierId());
        pstm.setString(2, String.valueOf(st.getQTY()));

        return pstm.executeUpdate() > 0;
    }
}
