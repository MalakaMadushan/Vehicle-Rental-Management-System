package lk.ijse.dep.fx.business.custom;

import lk.ijse.dep.fx.business.SuperBO;
import lk.ijse.dep.fx.dto.CustomerDTO;

import java.util.List;

public interface ManageCustomerBO extends SuperBO {
    List<CustomerDTO> getCustomer() throws Exception;

    boolean createCustomer(CustomerDTO dto) throws Exception;

    boolean updateCustomer(CustomerDTO dto) throws Exception;

    boolean deleteCustomer(String nic) throws Exception;

    CustomerDTO findCustomer(String id) throws Exception;
}
