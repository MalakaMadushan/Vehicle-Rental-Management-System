package lk.ijse.dep.fx.dto;

import java.time.LocalDate;

public class PaymentDTO extends SuperDTO{
    private String pid;
    private LocalDate rent_date;
    private  LocalDate return_date;
    private LocalDate date;
    private double rental_amount;
    private double advanced;
    private double extra;
    private double net_total;
    private String c_nic;
    private String v_num;

    public PaymentDTO() {
    }

    public PaymentDTO(String pid, LocalDate rent_date, LocalDate return_date, LocalDate date,
                      double rental_amount, double advanced, double extra, double net_total,
                      String c_nic, String v_num) {
        this.pid = pid;
        this.rent_date = rent_date;
        this.return_date = return_date;
        this.date = date;
        this.rental_amount = rental_amount;
        this.advanced = advanced;
        this.extra = extra;
        this.net_total = net_total;
        this.c_nic = c_nic;
        this.v_num = v_num;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
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

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getRental_amount() {
        return rental_amount;
    }

    public void setRental_amount(double rental_amount) {
        this.rental_amount = rental_amount;
    }

    public double getAdvanced() {
        return advanced;
    }

    public void setAdvanced(double advanced) {
        this.advanced = advanced;
    }

    public double getExtra() {
        return extra;
    }

    public void setExtra(double extra) {
        this.extra = extra;
    }

    public double getNet_total() {
        return net_total;
    }

    public void setNet_total(double net_total) {
        this.net_total = net_total;
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

    @Override
    public String toString() {
        return "PaymentDTO{" +
                "pid='" + pid + '\'' +
                ", rent_date=" + rent_date +
                ", return_date=" + return_date +
                ", date=" + date +
                ", rental_amount=" + rental_amount +
                ", advanced=" + advanced +
                ", extra=" + extra +
                ", net_total=" + net_total +
                ", c_nic='" + c_nic + '\'' +
                ", v_num='" + v_num + '\'' +
                '}';
    }
}
