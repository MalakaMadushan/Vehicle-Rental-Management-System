package lk.ijse.dep.fx.business.custom.impl;

import lk.ijse.dep.fx.business.Converter;
import lk.ijse.dep.fx.business.custom.ManageVehicleBO;
import lk.ijse.dep.fx.dao.DAOFactory;
import lk.ijse.dep.fx.dao.custom.VehicleDAO;
import lk.ijse.dep.fx.dto.VehicleDTO;

import java.util.List;

public class ManageVehicleBOImpl implements ManageVehicleBO {

    private VehicleDAO vehicleDAO;

   public ManageVehicleBOImpl(){
       vehicleDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.VEHICLE);

   }

    @Override
    public List<VehicleDTO> getVehicle() throws Exception {
        return vehicleDAO.findAll().map(Converter::<VehicleDTO>getDTOList).get();
    }

    @Override
    public boolean createVehicle(VehicleDTO dto) throws Exception {
        return vehicleDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateVehicle(VehicleDTO dto) throws Exception {
        return vehicleDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteVehicle(String vehicle_number) throws Exception {
        return vehicleDAO.delete(vehicle_number);
    }

    @Override
    public VehicleDTO findVehicle(String id) throws Exception {
        return vehicleDAO.find(id).map(Converter::<VehicleDTO>getDTO).orElse(null);
    }


}
