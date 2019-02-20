package lk.ijse.dep.fx.dao.custom.impl;

import lk.ijse.dep.fx.dao.CrudUtill;
import lk.ijse.dep.fx.dao.custom.RentDAO;
import lk.ijse.dep.fx.entity.Rent;
import lk.ijse.dep.fx.entity.RentPK;

import java.sql.Date;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class RentDAOImpl implements RentDAO {
    @Override
    public Optional<Rent> find(RentPK rentPK) throws Exception {
        ResultSet rst = CrudUtill.execute("SELECT * FROM Rent WHERE c_nic=? AND v_num=?",
                rentPK.getC_nic(),rentPK.getV_num());
        Rent r = null;
        while (rst.next()){
            r = new Rent(rst.getString(1),rst.getString(2),rst.getDate(3).toLocalDate(),
                    rst.getDate(4).toLocalDate(),rst.getInt(5));
        }
        return Optional.ofNullable(r);
    }

    @Override
    public Optional<List<Rent>> findAll() throws Exception {
        ArrayList<Rent> alRent = new ArrayList<>();
        ResultSet rst = CrudUtill.execute("SELECT * FROM Rent");
        while (rst.next()){
            String c_nic = rst.getString(1);
            String v_num = rst.getString(2);
            Date rent_date = rst.getDate(3);
            Date return_date = rst.getDate(4);
            int duration = rst.getInt(5);
            Rent rent = new Rent(c_nic,v_num,rent_date.toLocalDate(),return_date.toLocalDate(),duration);
            alRent.add(rent);
        }
        return Optional.ofNullable(alRent);
    }

    @Override
    public boolean save(Rent rent) throws Exception {
        return CrudUtill.<Integer>execute("INSERT INTO Rent VALUES (?,?,?,?,?)",
                rent.getRentPK().getC_nic(),rent.getRentPK().getV_num(),rent.getRent_date(),rent.getReturn_date(),rent.getDuration())>0;

    }

    @Override
    public boolean update(Rent rent) throws Exception {
        return CrudUtill.<Integer>execute("UPDATE Rent SET rent_date=?,return_date=?,duration=? WHERE c_nic=? AND c_num=?",
               rent.getRent_date(),rent.getReturn_date(),rent.getDuration(),rent.getRentPK().getC_nic(),rent.getRentPK().getV_num() )>0;

    }

    @Override
    public boolean delete(RentPK rentPK) throws Exception {
        return CrudUtill.<Integer>execute("DELETE FROM Rent WHERE c_nic=? AND v_num=?",rentPK.getC_nic(),rentPK.getV_num())>0;

    }
}
