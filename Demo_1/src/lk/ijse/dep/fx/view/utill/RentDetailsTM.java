package lk.ijse.dep.fx.view.utill;

import java.time.LocalDate;

public class RentDetailsTM {
    private String c_nic;
    private String v_num;
    private LocalDate rent_date;
    private LocalDate return_date;
    private String name;
    private String address;
    private String telephone;
    private String model;
    private String type;
    private String colour;
    private String rate;
    private String Total;

    public RentDetailsTM() {
    }

    public RentDetailsTM(String c_nic, String v_num, LocalDate rent_date,
                         LocalDate return_date, String name, String address,
                         String telephone, String model,
                         String type, String colour, String rate, String total) {
        this.c_nic = c_nic;
        this.v_num = v_num;
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.name = name;
        this.address = address;
        this.telephone = telephone;
        this.model = model;
        this.type = type;
        this.colour = colour;
        this.rate = rate;
        Total = total;
    }

    public String getC_nic() {
        return c_nic;
    }

    public void setC_nic(String c_nic) {
        this.c_nic = c_nic;
    }

    public String getV_num() {
        return v_num;
    }

    public void setV_num(String v_num) {
        this.v_num = v_num;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getTotal() {
        return Total;
    }

    public void setTotal(String total) {
        Total = total;
    }

    @Override
    public String toString() {
        return "RentDetailsTM{" +
                "c_nic='" + c_nic + '\'' +
                ", v_num='" + v_num + '\'' +
                ", rent_date=" + rent_date +
                ", return_date=" + return_date +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", telephone='" + telephone + '\'' +
                ", model='" + model + '\'' +
                ", type='" + type + '\'' +
                ", colour='" + colour + '\'' +
                ", rate='" + rate + '\'' +
                ", Total='" + Total + '\'' +
                '}';
    }
}
