package lk.ijse.dep.fx.business.custom.impl;

import lk.ijse.dep.fx.business.Converter;
import lk.ijse.dep.fx.business.custom.ManageRentBO;
import lk.ijse.dep.fx.dao.DAOFactory;
import lk.ijse.dep.fx.dao.custom.QueryDAO;
import lk.ijse.dep.fx.dao.custom.RentDAO;
import lk.ijse.dep.fx.dto.CustomDTO;
import lk.ijse.dep.fx.dto.RentDTO;
import lk.ijse.dep.fx.entity.CustomEntity;
import lk.ijse.dep.fx.entity.RentPK;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ManageRentBOImpl implements ManageRentBO {

    private RentDAO rentDAO;
    private QueryDAO queryDAO;

    public ManageRentBOImpl(){
        rentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.RENT);
        queryDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.QUERY);
    }

    @Override
    public List<CustomDTO> getfindAvailableVehicleDetails(LocalDate rentdate,LocalDate returnDate) throws Exception {
        List<CustomEntity>customEntityList = queryDAO.findAvailableVehicleDetails(rentdate, returnDate);
        ArrayList<CustomDTO> customDTOS = new ArrayList<>();
        for (CustomEntity customEntity : customEntityList) {
            customDTOS.add(new CustomDTO(customEntity.getVehicle_number(),customEntity.getV_name(),customEntity.getModel(),
                    customEntity.getType(),customEntity.getVehicle_rate(),customEntity.getColour()));
        }return customDTOS;
    }

    @Override
    public List<CustomDTO> notRentAvailbleVehicle() throws Exception {
        List<CustomEntity>customList = queryDAO.notRentAvailbleVehicle();
        ArrayList<CustomDTO> customDTOSnew = new ArrayList<>();
        for (CustomEntity customEntity : customList) {
            customDTOSnew.add(new CustomDTO(customEntity.getVehicle_number(),customEntity.getV_name(),customEntity.getModel(),
                    customEntity.getType(),customEntity.getVehicle_rate(),customEntity.getColour()));
        }
        return customDTOSnew;
    }


    @Override
    public List<CustomDTO> findRentDetails(String c_nic, String v_num) throws Exception {
        List<CustomEntity> rentDetails = queryDAO.findRentDetails(c_nic, v_num);
        ArrayList<CustomDTO> customeDTOS = new ArrayList<>();
        for (CustomEntity customEntity : rentDetails) {
            customeDTOS.add(new CustomDTO(customEntity.getRent_date(),customEntity.getReturn_date(),customEntity.getName(),customEntity.getAddress(),
                    customEntity.getTelephone(),customEntity.getModel(),customEntity.getType(),customEntity.getColour(),customEntity.getVehicle_rate()));
        }

        return customeDTOS;
    }

    @Override
    public List<RentDTO> getRent() throws Exception {
        return rentDAO.findAll().map(Converter::<RentDTO>getDTOList).get();
    }

    @Override
    public boolean createRent(RentDTO dto) throws Exception {
        return rentDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateRent(RentDTO dto) throws Exception {
        return rentDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteRent(RentPK rentPK) throws Exception {
        return  rentDAO.delete(rentPK);
    }

    @Override
    public RentDTO findRent(RentPK rentPK) throws Exception {
        return rentDAO.find(rentPK).map(Converter::<RentDTO>getDTO).orElse(null);
    }

    @Override
    public String generatePaymentID() throws Exception {
        return getRent().size()+ 1+"";
    }



}
