package lk.ijse.dep.fx.business.custom;

import lk.ijse.dep.fx.business.SuperBO;
import lk.ijse.dep.fx.dto.VehicleDTO;

import java.util.List;

public interface ManageVehicleBO extends SuperBO {

    List<VehicleDTO> getVehicle() throws Exception;

    boolean createVehicle(VehicleDTO dto) throws Exception;

    boolean updateVehicle(VehicleDTO dto) throws Exception;

    boolean deleteVehicle(String vehicle_number) throws Exception;

    VehicleDTO findVehicle(String id) throws Exception;
}
