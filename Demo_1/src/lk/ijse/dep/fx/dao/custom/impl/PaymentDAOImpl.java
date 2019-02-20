package lk.ijse.dep.fx.dao.custom.impl;

import lk.ijse.dep.fx.dao.CrudUtill;
import lk.ijse.dep.fx.dao.custom.PaymentDAO;
import lk.ijse.dep.fx.entity.Payment;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PaymentDAOImpl implements PaymentDAO {
    @Override
    public Optional<Payment> find(String pid) throws Exception {
        ResultSet rst = CrudUtill.execute("SELECT * FROM Payment WHERE pid=?", pid);
        Payment pay = null;
        while (rst.next()){
            pay = new Payment(rst.getString(1),rst.getDate(2).toLocalDate(),rst.getDate(3).toLocalDate(),
                    rst.getDate(4).toLocalDate(),rst.getDouble(5),
                    rst.getDouble(6),rst.getDouble(7),rst.getDouble(8),rst.getString(9),
                    rst.getString(10));
        }
        return Optional.ofNullable(pay);
    }

    @Override
    public Optional<List<Payment>> findAll() throws Exception {
        ArrayList<Payment> alPayment = new ArrayList<>();
        ResultSet rst = CrudUtill.execute("SELECT * FROM Payment");
        while (rst.next()){
            String pid = rst.getString(1);
            Date rent_date = rst.getDate(2);
            Date return_date = rst.getDate(3);
            Date date = rst.getDate(4);
            double rental_amount = rst.getDouble(5);
            double advanced = rst.getDouble(6);
            double extra = rst.getDouble(7);
            double net_total = rst.getDouble(8);
            String c_nic = rst.getString(9);
            String v_num = rst.getString(10);

            Payment payment = new Payment(pid,rent_date.toLocalDate(),return_date.toLocalDate(),date.toLocalDate(),rental_amount,advanced,extra,net_total,c_nic,v_num);
            alPayment.add(payment);
        }
        return Optional.ofNullable(alPayment);
    }

    @Override
    public boolean save(Payment payment) throws Exception {
        return CrudUtill.<Integer>execute("INSERT INTO Payment VALUES (?,?,?,?,?,?,?,?,?,?)",
               payment.getPid(),payment.getRent_date(),payment.getReturn_date(),payment.getDate(),payment.getRental_amount(),payment.getAdvanced(),
                payment.getExtra(),payment.getNet_total(),payment.getC_nic(),payment.getV_num())>0;

    }

    @Override
    public boolean update(Payment payment) throws Exception {
        return CrudUtill.<Integer>execute("UPDATE Payment SET rent_date=?,return_date=?,date=?,rental_amount=?,advanced=?,extra=?,net_total=? WHERE pid=?",
                payment.getRent_date(),payment.getReturn_date(),payment.getDate(),payment.getRental_amount(),payment.getAdvanced(),
                payment.getExtra(),payment.getNet_total(),payment.getPid())>0;

    }

    @Override
    public boolean delete(String pid) throws Exception {
        return CrudUtill.<Integer>execute("DELETE FROM Payment WHERE pid=?",pid)>0;

    }
}
