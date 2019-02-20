package lk.ijse.dep.fx.business.custom;

import lk.ijse.dep.fx.business.SuperBO;
import lk.ijse.dep.fx.dto.CustomDTO;
import lk.ijse.dep.fx.dto.RentDTO;
import lk.ijse.dep.fx.entity.RentPK;

import java.time.LocalDate;
import java.util.List;

public interface ManageRentBO extends SuperBO {

    List<CustomDTO> getfindAvailableVehicleDetails(LocalDate rentdate, LocalDate returnDate) throws Exception;

    List<CustomDTO> notRentAvailbleVehicle() throws Exception;

    List<RentDTO> getRent() throws Exception;

    boolean createRent(RentDTO dto) throws Exception;

    boolean updateRent(RentDTO dto) throws Exception;

    boolean deleteRent(RentPK rentPK) throws Exception;

    RentDTO findRent(RentPK rentPK) throws Exception;

    String generatePaymentID() throws Exception;

    List<CustomDTO> findRentDetails(String c_nic, String vnum) throws Exception;


}
