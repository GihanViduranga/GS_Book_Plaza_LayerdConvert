package lk.gsbp.bo.custom.impl;

import lk.gsbp.bo.custom.ItemBO;
import lk.gsbp.dao.DAOFactory;
import lk.gsbp.dao.custom.ItemDAO;
import lk.gsbp.dao.custom.impl.ItemDAOImpl;
import lk.gsbp.entity.Item;
import lk.gsbp.model.ItemDTO;
import lk.gsbp.model.orderDetailsDTO;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ItemBOImpl implements ItemBO {
    ItemDAOImpl itemDAO = (ItemDAOImpl) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.ITEM);

    @Override
    public List<ItemDTO> getAllItems() throws SQLException {
        List<ItemDTO> items = new ArrayList();
        List<Item> all = itemDAO.getAll();

        for (Item item : all){
            items.add(new ItemDTO(item.getItemsId(), item.getItemName(),item.getQTY(),item.getUnitPrice(),item.getStockId()));
        }
        return items;
    }

    @Override
    public List<ItemDTO> getItemIds() throws SQLException {
        List<ItemDTO> items = new ArrayList();
        List<Item> all = itemDAO.getIds();

        for (Item item : all){
            items.add(new ItemDTO(item.getItemsId(), item.getItemName(), item.getQTY(), item.getUnitPrice(), item.getStockId()));
        }
        return items;
    }

    @Override
    public boolean updateItem(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getItemsId(), dto.getItemName(), dto.getQTY(), dto.getUnitPrice(), dto.getStockId()));
    }

    @Override
    public boolean update2Item(ItemDTO dto) throws SQLException {
        return itemDAO.update(new Item(dto.getItemsId(), dto.getItemName(), dto.getQTY(), dto.getUnitPrice(), dto.getStockId()));
    }

    @Override
    public ItemDTO searchByItemId(String Id) throws SQLException {
        Item item = itemDAO.searchById(Id);
        return new ItemDTO(item.getItemsId(),item.getItemName(),item.getQTY(),item.getUnitPrice(),item.getStockId());
    }

    @Override
    public boolean saveItem(ItemDTO dto) throws SQLException {
        return itemDAO.save(new Item(dto.getItemsId(), dto.getItemName(), dto.getQTY(), dto.getUnitPrice(), dto.getStockId()));
    }

    @Override
    public boolean deleteItem(String id) throws SQLException {
        return itemDAO.delete(id);
    }

    @Override
    public boolean update3Item(List<orderDetailsDTO> odList) throws SQLException {
        return itemDAO.update3(odList);
    }

    @Override
    public String GetItemIds() throws SQLException {
        return itemDAO.GetItemIds();
    }
}
