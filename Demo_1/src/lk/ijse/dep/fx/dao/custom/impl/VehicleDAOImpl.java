package lk.ijse.dep.fx.dao.custom.impl;

import lk.ijse.dep.fx.dao.CrudUtill;
import lk.ijse.dep.fx.dao.custom.VehicleDAO;
import lk.ijse.dep.fx.entity.Vehicle;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class VehicleDAOImpl implements VehicleDAO {
    @Override
    public Optional<Vehicle> find(String vehicle_number) throws Exception {
        ResultSet rst = CrudUtill.execute("SELECT * FROM Vehicle WHERE vehicle_number=?", vehicle_number);
        Vehicle v = null;
        while (rst.next()){
            v = new Vehicle(rst.getString(1),rst.getString(2),
                    rst.getString(3),rst.getString(4),
                    rst.getDouble(5),rst.getString(6));
        }
        return Optional.ofNullable(v);
    }

    @Override
    public Optional<List<Vehicle>> findAll() throws Exception {
        ArrayList<Vehicle> alVehicle = new ArrayList<>();
        ResultSet rst = CrudUtill.execute("SELECT * FROM Vehicle");
        while (rst.next()){
            String vehicle_number = rst.getString(1);
            String v_name = rst.getString(2);
            String model = rst.getString(3);
            String type = rst.getString(4);
            double vehicle_rate = rst.getDouble(5);
            String colour = rst.getString(6);

            Vehicle vehicle = new Vehicle(vehicle_number,v_name,model,type,vehicle_rate,colour);
            alVehicle.add(vehicle);
        }
        return Optional.ofNullable(alVehicle);
    }

    @Override
    public boolean save(Vehicle vehicle) throws Exception {
        return CrudUtill.<Integer>execute("INSERT INTO Vehicle VALUES (?,?,?,?,?,?)",
                vehicle.getVehicle_number(),vehicle.getName(),vehicle.getModel(),vehicle.getType(),
                vehicle.getVehicle_rate(),vehicle.getColour())>0;

    }

    @Override
    public boolean update(Vehicle vehicle) throws Exception {
        return CrudUtill.<Integer>execute("UPDATE Vehicle SET name=?,model=?,type=?,vehicle_rate=?,colour=? WHERE vehicle_number=?",
               vehicle.getName(), vehicle.getModel(),vehicle.getType(),vehicle.getVehicle_rate(),
                vehicle.getColour(),vehicle.getVehicle_number())>0;

    }

    @Override
    public boolean delete(String vehicle_number) throws Exception {
        return CrudUtill.<Integer>execute("DELETE FROM Vehicle WHERE vehicle_number=?",vehicle_number)>0;

    }
}
