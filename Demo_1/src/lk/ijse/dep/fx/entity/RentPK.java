package lk.ijse.dep.fx.entity;

public class RentPK {
    private String c_nic;
    private String v_num;

    public RentPK() {
    }

    public RentPK(String c_nic, String v_num) {
        this.c_nic = c_nic;
        this.v_num = v_num;
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
        return "RentPK{" +
                "c_nic='" + c_nic + '\'' +
                ", v_num='" + v_num + '\'' +
                '}';
    }
}
