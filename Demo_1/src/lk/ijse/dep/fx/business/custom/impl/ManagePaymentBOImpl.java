package lk.ijse.dep.fx.business.custom.impl;

import lk.ijse.dep.fx.business.Converter;
import lk.ijse.dep.fx.business.custom.ManagePaymentBO;
import lk.ijse.dep.fx.dao.DAOFactory;
import lk.ijse.dep.fx.dao.custom.PaymentDAO;
import lk.ijse.dep.fx.dto.PaymentDTO;

import java.util.List;

public class ManagePaymentBOImpl implements ManagePaymentBO {

    private PaymentDAO paymentDAO;
    public ManagePaymentBOImpl(){
        paymentDAO = DAOFactory.getInstance().getDAO(DAOFactory.DAOTypes.PAYMENT);
    }
    @Override
    public List<PaymentDTO> getPayment() throws Exception {
        return paymentDAO.findAll().map(Converter::<PaymentDTO>getDTOList).get();
    }

    @Override
    public boolean createPayment(PaymentDTO dto) throws Exception {
        return paymentDAO.save(Converter.getEntity(dto));
    }

    @Override
    public boolean updatePayment(PaymentDTO dto) throws Exception {
        return  paymentDAO.update(Converter.getEntity(dto));
    }

    @Override
    public boolean deletePayment(String pid) throws Exception {
        return paymentDAO.delete(pid);
    }

    @Override
    public PaymentDTO findPayment(String id) throws Exception {
        return paymentDAO.find(id).map(Converter::<PaymentDTO>getDTO).orElse(null);
    }

    @Override
    public String generatePaymentID() throws Exception {

        return null;
    }
}
