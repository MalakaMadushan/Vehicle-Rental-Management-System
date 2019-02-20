package lk.ijse.dep.fx.dto;

import java.time.LocalDate;

public class CustomDTO {


    private LocalDate rent_date;
    private LocalDate return_date;
    private String name;
    private String address;
    private String telephone;


    private String v_name;
    private String vehicle_number;
    private String model;
    private String type;
    private double vehicle_rate;
    private String colour;

    public CustomDTO(LocalDate rent_date, LocalDate return_date, String name, String address, String telephone,
                     String model, String type, String colour, double vehicle_rate) {
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.model = model;
        this.type = type;
        this.colour = colour;
        this.vehicle_rate = vehicle_rate;
    }



    public CustomDTO() {
    }

    public CustomDTO(String vehicle_number, String v_name, String model, String type,
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
        return "CustomDTO{" +
                "vehicle_number='" + vehicle_number + '\'' +
                ", name='" + v_name + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", vehicle_rate=" + vehicle_rate +
                ", colour='" + colour + '\'' +
                '}';
    }

    public LocalDate getRent_date() {
        return rent_date;
    }

    public void setRent_date(LocalDate rent_date) {
        this.rent_date = rent_date;
    }

    public LocalDate getReturn_date() {
        return return_date;
    }

    public void setReturn_date(LocalDate return_date) {
        this.return_date = return_date;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getV_name() {
        return v_name;
    }

    public void setV_name(String v_name) {
        this.v_name = v_name;
    }
}
