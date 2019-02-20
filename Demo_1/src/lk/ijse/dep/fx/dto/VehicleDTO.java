package lk.ijse.dep.fx.dto;

public class VehicleDTO extends SuperDTO{
    private String vehicle_number;
    private String v_name;
    private String model;
    private String type;
    private double vehicle_rate;
    private String colour;

    public VehicleDTO() {
    }

    public VehicleDTO(String vehicle_number, String v_name, String model, String type,
                      double vehicle_rate, String colour) {
        this.vehicle_number = vehicle_number;
        this.v_name = v_name;
        this.model = model;
        this.type = type;
        this.vehicle_rate = vehicle_rate;
        this.colour = colour;
    }

    public String getVehicle_number() {
        return vehicle_number;
    }

    public void setVehicle_number(String vehicle_number) {
        this.vehicle_number = vehicle_number;
    }

    public String getName() {
        return v_name;
    }

    public void setName(String v_name) {
        this.v_name = v_name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getVehicle_rate() {
        return vehicle_rate;
    }

    public void setVehicle_rate(double vehicle_rate) {
        this.vehicle_rate = vehicle_rate;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "VehicleDTO{" +
                "vehicle_number='" + vehicle_number + '\'' +
                ", name='" + v_name + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", vehicle_rate=" + vehicle_rate +
                ", colour='" + colour + '\'' +
                '}';
    }
}
