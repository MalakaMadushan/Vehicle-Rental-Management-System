package lk.ijse.dep.fx.business.custom;

import lk.ijse.dep.fx.business.SuperBO;
import lk.ijse.dep.fx.dto.PaymentDTO;

import java.util.List;

public interface ManagePaymentBO extends SuperBO {
    List<PaymentDTO> getPayment() throws Exception;

    boolean createPayment(PaymentDTO dto) throws Exception;

    boolean updatePayment(PaymentDTO dto) throws Exception;

    boolean deletePayment(String pid) throws Exception;

    PaymentDTO findPayment(String id) throws Exception;

    String generatePaymentID() throws Exception;


}
