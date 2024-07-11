package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.custom.SupplierBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.SupplierDAO;
import lk.gsbp.entity.Supplier;
import lk.gsbp.model.SupplierDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SupplierBOImpl implements SupplierBO {

    SupplierDAO supplierDAO = (SupplierDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.SUPPLIER);
    @Override
    public boolean save(SupplierDTO dto) throws SQLException {
        return supplierDAO.save(new Supplier(dto.getSupplierId(),dto.getSuppName(),dto.getContact(),dto.getProduct(),dto.getItemName(),dto.getQty()));
    }

    @Override
    public boolean delete(String supplierId) throws SQLException {
        return supplierDAO.delete(supplierId);
    }

    @Override
    public boolean update(SupplierDTO dto) throws SQLException {
        return supplierDAO.update(new Supplier(dto.getSupplierId(),dto.getSuppName(),dto.getContact(),dto.getProduct(),dto.getItemName(),dto.getQty()));
    }


    @Override
    public SupplierDTO searchById(String Id) throws SQLException {
        Supplier supplier = supplierDAO.searchById(Id);
        return new SupplierDTO(supplier.getSupplierId(),supplier.getSuppName(),supplier.getContact(),supplier.getProduct(),supplier.getItemName(),supplier.getQty());
    }

    @Override
    public String getCurrentSupId() throws SQLException, ClassNotFoundException {
        return supplierDAO.getCurrentSupId();
    }

    @Override
    public String generateNewSupplierID() throws SQLException, ClassNotFoundException {
        return supplierDAO.generateNewSupplierID();
    }

    @Override
    public List<SupplierDTO> getAll() throws SQLException {
        List<Supplier> suppliers = supplierDAO.getAll();
        List<SupplierDTO> supplierDTOS = new ArrayList<>();
        for (Supplier supplier : suppliers) {
           supplierDTOS.add(new SupplierDTO(supplier.getSupplierId(),supplier.getSuppName(),supplier.getContact(),supplier.getProduct(),supplier.getItemName(),supplier.getQty()));
        }
        return supplierDTOS;
    }
}
