package lk.ijse.dep.fx.business.custom.impl;

import lk.ijse.dep.fx.business.Converter;
import lk.ijse.dep.fx.business.custom.ManageCustomerBO;
import lk.ijse.dep.fx.dao.DAOFactory;
import lk.ijse.dep.fx.dao.custom.CustomerDAO;
import lk.ijse.dep.fx.dto.CustomerDTO;

import java.util.List;

public class ManageCustomerBOImpl implements ManageCustomerBO {


    private CustomerDAO customerDAO;

    public  ManageCustomerBOImpl(){
        customerDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.CUSTOMER);
    }
    @Override
    public List<CustomerDTO> getCustomer() throws Exception {
        return customerDAO.findAll().map(Converter::<CustomerDTO>getDTOList).get();
    }

    @Override
    public boolean createCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updateCustomer(CustomerDTO dto) throws Exception {
        return customerDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deleteCustomer(String nic) throws Exception {
        return customerDAO.delete(nic);
    }

    @Override
    public CustomerDTO findCustomer(String id) throws Exception {
        return customerDAO.find(id).map(Converter::<CustomerDTO>getDTO).orElse(null);
    }
}
