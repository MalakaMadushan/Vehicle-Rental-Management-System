package lk.ijse.dep.fx.dao.custom.impl;

import lk.ijse.dep.fx.dao.CrudUtill;
import lk.ijse.dep.fx.dao.custom.QueryDAO;
import lk.ijse.dep.fx.entity.CustomEntity;

import java.sql.ResultSet;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class QueryDAOImpl implements QueryDAO {
    @Override
    public List<CustomEntity> findAvailableVehicleDetails(LocalDate rent_date, LocalDate return_date) throws Exception {
        ResultSet rst = CrudUtill.execute("SELECT distinct vehicle_number,v_name,model,type,vehicle_rate,colour FROM Vehicle v INNER JOIN Rent R on v.vehicle_number != " +
                "R.v_num WHERE R.v_num in\n" +
                "(? between rent_date AND return_date AND ? between return_date AND return_date);\n", rent_date, return_date);

        ArrayList<CustomEntity> array = new ArrayList<>();
        while (rst.next()){
            CustomEntity customEntity = new CustomEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6));
            array.add(customEntity);
        }
        return array;
    }
    @Override
    public List<CustomEntity> notRentAvailbleVehicle() throws Exception {
        ResultSet rst = CrudUtill.execute("SELECT *FROM Vehicle WHERE vehicle_number NOT IN ( SELECT v_num FROM Rent);");

        ArrayList<CustomEntity> array1 = new ArrayList<>();
        while (rst.next()){
            CustomEntity customEntity = new CustomEntity(rst.getString(1),
                    rst.getString(2),
                    rst.getString(3),
                    rst.getString(4),
                    rst.getDouble(5),
                    rst.getString(6));
            array1.add(customEntity);
        }
        return array1;
    }

    @Override
    public List<CustomEntity> findRentDetails(String c_nic, String v_num) throws Exception {
//        ResultSet rst = CrudUtill.execute("SELECT rent_date,return_date,name,address,telephone,model,type,colour,vehicle_rate FROM" +
//                "Customer INNER JOIN Rent ON Customer.nic=Rent.c_nic INNER JOIN Vehicle ON Rent.v_num=Vehicle.vehicle_number WHERE Rent.c_nic=? AND " +
//                "Rent.v_num=?",c_nic,v_num );

       ResultSet rst = CrudUtill.execute("SELECT rent_date,return_date,name,address,telephone,model,type,colour,vehicle_rate FROM " +
                       "Customer INNER JOIN Rent ON Customer.nic=Rent.c_nic INNER JOIN Vehicle ON Rent.v_num=Vehicle.vehicle_number WHERE Rent.c_nic=? AND Rent.v_num=?",
               c_nic,v_num);

        ArrayList<CustomEntity> array2 = new ArrayList<>();
        while (rst.next()){

            array2.add(new CustomEntity(rst.getDate(1).toLocalDate(),rst.getDate(2).toLocalDate(),rst.getString(3),rst.getString(4),
                    rst.getString(5),rst.getString(6),rst.getString(7),
                    rst.getString(8),rst.getDouble(9)));
        }
        return array2;
    }


}
