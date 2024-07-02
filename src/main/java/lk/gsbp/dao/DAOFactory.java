package lk.gsbp.dao;

import lk.gsbp.dao.custom.impl.CustomerDAOImpl;

public class DAOFactory {
    private static DAOFactory daoFactory;

    public DAOFactory() {
    }
    public static DAOFactory getDaoFactory(){
        return (daoFactory == null) ? daoFactory = new DAOFactory() : daoFactory;
    }
    public enum DAOTypes {
        CUSTOMER
    }
    public SuperDAO getDAO(DAOTypes types){
        switch (types){
            case CUSTOMER :
                return new CustomerDAOImpl();
            default:
                return null;
        }
    }
}
