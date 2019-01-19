package gr.uoa.di.m149_p2.dto;


public class GeoPass {

    private String date;
    private CustomPoint p1;
    private CustomPoint p2;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public CustomPoint getP1() {
        return p1;
    }

    public void setP1(CustomPoint p1) {
        this.p1 = p1;
    }

    public CustomPoint getP2() {
        return p2;
    }

    public void setP2(CustomPoint p2) {
        this.p2 = p2;
    }
}
