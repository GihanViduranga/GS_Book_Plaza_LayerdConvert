package lk.gsbp.bo;


import lk.gsbp.bo.custom.impl.*;

public class BOFactory {
    private static BOFactory boFactory;
    private BOFactory(){
    }
    public static BOFactory getBoFactory(){
        return (boFactory==null)? boFactory=new BOFactory() : boFactory;
    }

    public enum BOTypes{
        CUSTOMER,EMPLOYEE,PAYMENT,DELIVERY,ITEM,PLACEORDER,STOCK,SUPPLIER;
    }

    public SuperBO getBO(BOTypes types){
        switch (types){
            case CUSTOMER:
                return new CustomerBOImpl();
            case EMPLOYEE:
                return new EmployeeBOImpl();
            case DELIVERY:
                return new DeliveryBOImpl();
            case ITEM:
                return new ItemBOImpl();
            case PLACEORDER:
                return new PlaceOrderBOImpl();
            case PAYMENT:
                return new PaymentBOImpl();
            case STOCK:
                return new StockBOImpl();
            case SUPPLIER:
                return new StockBOImpl();
            default:
                return null;
        }
    }
}
