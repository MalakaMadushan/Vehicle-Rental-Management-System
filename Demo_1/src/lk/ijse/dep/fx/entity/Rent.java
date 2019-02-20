package lk.ijse.dep.fx.entity;

import java.time.LocalDate;

public class Rent extends SuperEntity {
    private RentPK rentPK;
    private LocalDate rent_date;
    private LocalDate return_date;
    private int duration;

    public Rent() {
    }

    public Rent(RentPK rentPK, LocalDate rent_date, LocalDate return_date, int duration) {
        this.rentPK = rentPK;
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.duration = duration;
    }
    public Rent(String c_nic,String v_num, LocalDate rent_date, LocalDate return_date, int duration) {
        this.rentPK = new RentPK(c_nic,v_num);
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.duration = duration;
    }

    public RentPK getRentPK() {
        return rentPK;
    }

    public void setRentPK(RentPK rentPK) {
        this.rentPK = rentPK;
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
        return "Rent{" +
                "rentPK=" + rentPK +
                ", rent_date=" + rent_date +
                ", return_date=" + return_date +
                ", duration=" + duration +
                '}';
    }
}
