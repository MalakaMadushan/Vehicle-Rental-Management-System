package lk.ijse.dep.fx.dao.custom;

import lk.ijse.dep.fx.dao.SuperDAO;
import lk.ijse.dep.fx.entity.CustomEntity;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface QueryDAO extends SuperDAO {

    List<CustomEntity>findAvailableVehicleDetails(LocalDate rent_date,LocalDate return_date) throws Exception;

    List<CustomEntity>findRentDetails(String nic,String v_num) throws Exception;

    List<CustomEntity> notRentAvailbleVehicle() throws Exception;
}
