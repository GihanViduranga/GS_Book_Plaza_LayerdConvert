package lk.gsbp.dao;

import lk.gsbp.dao.custom.impl.*;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes {
        CUSTOMER,EMPLOYEE,ITEM,DELIVERY,PAYMENT,ORDERDETAILS,ORDER,STOCK,SUPPLIER
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER :
                return new CustomerDAOImpl();
            case EMPLOYEE:
                return new EmployeeDAOImpl();
            case ITEM:
                return new ItemDAOImpl();
            case DELIVERY:
                return new DeliveryDAOImpl();
            case PAYMENT:
                return new PaymentDAOImpl();
            case ORDERDETAILS:
                return new OrderDetailDAOImpl();
            case ORDER:
                return new OrderDAOImpl();
            case STOCK:
                return new StockDAOImpl();
            case SUPPLIER:
                return new SupplierDAOImpl();
            default:
                return null;
        }
    }
}
