package lk.ijse.dep.fx.dao;

import lk.ijse.dep.fx.dao.custom.impl.*;

public class DAOFactory {

    private static DAOFactory daoFactory;
    public enum DAOTypes{
        VEHICLE,CUSTOMER,RENT,PAYMENT,QUERY
    }

    private DAOFactory(){

    }
    public static DAOFactory getInstance(){
        if(daoFactory == null){
             daoFactory = new DAOFactory();
        }
        return daoFactory;
    }
   public <T extends SuperDAO>T getDAO(DAOTypes daoTypes){
        switch (daoTypes){
            case VEHICLE:
                return (T) new VehicleDAOImpl();
            case CUSTOMER:
                return (T) new CustomerDAOImpl();
            case RENT:
                return (T) new RentDAOImpl();
            case PAYMENT:
                return (T) new PaymentDAOImpl();
            case QUERY:
                return (T) new QueryDAOImpl();
                default:
                    return null;


        }

   }
}
