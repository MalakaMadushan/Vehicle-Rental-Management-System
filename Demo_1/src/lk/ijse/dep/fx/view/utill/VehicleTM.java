package lk.ijse.dep.fx.view.utill;

public class VehicleTM {
    private String vehicle_number;
    private String name;
    private String model;
    private String type;
    private Double vehicle_rate;
    private String colour;

    public VehicleTM() {
    }

    public VehicleTM(String vehicle_number, String name, String model, String type,
                     Double vehicle_rate, String colour) {
        this.vehicle_number = vehicle_number;
        this.name = name;
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
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public Double getVehicle_rate() {
        return vehicle_rate;
    }

    public void setVehicle_rate(Double vehicle_rate) {
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
        return "VehicleTM{" +
                "vehicle_number='" + vehicle_number + '\'' +
                ", name='" + name + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", vehicle_rate=" + vehicle_rate +
                ", colour='" + colour + '\'' +
                '}';
    }
}
