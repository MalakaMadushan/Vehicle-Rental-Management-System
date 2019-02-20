package lk.ijse.dep.fx.business;

import lk.ijse.dep.fx.business.custom.impl.ManageVehicleBOImpl;
import lk.ijse.dep.fx.business.custom.impl.ManageCustomerBOImpl;
import lk.ijse.dep.fx.business.custom.impl.ManagePaymentBOImpl;
import lk.ijse.dep.fx.business.custom.impl.ManageRentBOImpl;

public class BOFactory {
    private static BOFactory boFactory;

    public enum BOTypes{
        MANAGEVEHICLE,MANAGECUSTOMER,MANAGERENT,MANAGEPAYMENT
    }

    private BOFactory(){

    }

    public static BOFactory getInstance(){
        if(boFactory == null){
             boFactory = new BOFactory();
        }
        return boFactory;
    }

    public<T extends SuperBO> T getBO(BOTypes boTypes){
        switch (boTypes){
            case MANAGEVEHICLE:
                return (T) new ManageVehicleBOImpl();
            case MANAGECUSTOMER:
                return (T) new ManageCustomerBOImpl();
            case MANAGERENT:
                return (T) new ManageRentBOImpl();
            case MANAGEPAYMENT:
                return (T) new ManagePaymentBOImpl();
                default:
                    return null;
        }

    }
}
