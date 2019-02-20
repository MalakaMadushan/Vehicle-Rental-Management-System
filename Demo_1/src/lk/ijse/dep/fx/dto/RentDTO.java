package lk.ijse.dep.fx.dto;

import java.time.LocalDate;

public class RentDTO extends SuperDTO{
    private String c_nic;
    private String v_num;
    private LocalDate rent_date;
    private LocalDate return_date;
    private int duration;

    public RentDTO() {
    }

    public RentDTO(String c_nic, String v_num, LocalDate rent_date, LocalDate return_date, int duration) {
        this.c_nic = c_nic;
        this.v_num = v_num;
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.duration = duration;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "RentDTO{" +
                "c_nic='" + c_nic + '\'' +
                ", v_num='" + v_num + '\'' +
                ", rent_date=" + rent_date +
                ", return_date=" + return_date +
                ", duration=" + duration +
                '}';
    }
}
