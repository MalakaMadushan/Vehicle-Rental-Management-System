package lk.ijse.dep.fx.business;

import lk.ijse.dep.fx.dto.*;
import lk.ijse.dep.fx.entity.*;

import java.util.List;
import java.util.stream.Collectors;

public class Converter {

    public static <T extends SuperDTO> T getDTO(SuperEntity entity) {
        if (entity instanceof Customer) {
            Customer c = (Customer) entity;
            return (T) new CustomerDTO(c.getNic(),c.getName(),c.getAddress(),c.getTelephone());
       } else if (entity instanceof Vehicle) {
            Vehicle v = (Vehicle) entity;
            return (T) new VehicleDTO(v.getVehicle_number(),v.getName(),v.getModel(),
                    v.getType(),v.getVehicle_rate(),v.getColour());
        } else if (entity instanceof Rent) {
            Rent r = (Rent) entity;
            return (T) new RentDTO(r.getRentPK().getC_nic(),r.getRentPK().getV_num(),
                    r.getRent_date(),r.getReturn_date(),r.getDuration());
        }
        else if (entity instanceof Payment) {
            Payment p = (Payment) entity;
            return (T) new PaymentDTO(p.getPid(),p.getRent_date(),p.getReturn_date(),p.getDate(),p.getRental_amount(),
                    p.getAdvanced(),p.getExtra(),p.getNet_total(),p.getC_nic(),p.getV_num());
        }
        else {
            throw new RuntimeException("This entity can't be converted to a DTO");
        }
    }

    public static <T extends SuperEntity> T getEntity(SuperDTO dto) {
        if (dto instanceof CustomerDTO) {
            CustomerDTO c = (CustomerDTO) dto;
            return (T) new Customer(c.getNic(),c.getName(),c.getAddress(),c.getTelephone());
      } else if (dto instanceof VehicleDTO) {
            VehicleDTO v = (VehicleDTO) dto;
            return (T) new Vehicle(v.getVehicle_number(),v.getName(),v.getModel(),v.getType(),
                    v.getVehicle_rate(),v.getColour());
        }else if (dto instanceof RentDTO) {
            RentDTO r = (RentDTO) dto;
            return (T) new Rent(r.getC_nic(),r.getV_num(),r.getRent_date(),r.getReturn_date(),r.getDuration());
        }
        else if (dto instanceof PaymentDTO) {
            PaymentDTO p = (PaymentDTO) dto;
            return (T) new Payment(p.getPid(),p.getRent_date(),p.getReturn_date(),p.getRent_date(),p.getRental_amount(),
                    p.getAdvanced(),p.getExtra(),p.getNet_total(),p.getC_nic(),p.getV_num());
        }
        else {
            throw new RuntimeException("This DTO can't be converted to an entity");
        }
    }

    public static <T extends SuperDTO> List<T> getDTOList(List<? extends SuperEntity> entities) {
        return entities.stream().map(Converter::<T>getDTO).collect(Collectors.toList());
    }

    public static <T extends SuperEntity> List<T> getEntityList(List<? extends SuperDTO> dtos) {
        return dtos.stream().map(Converter::<T>getEntity).collect(Collectors.toList());

    }

}
