package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class Potholes extends Request{
    private String currentActivity;
    private String mostRecentAction;
    private Float potholes;
    private String SSA;

    public Potholes(Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest,
                    String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict,
                    Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, Point point,
                    String currentActivity, String mostRecentAction, Float potholes, String SSA) {
        super(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y,
                ward, policeDistrict, communityArea, latitude, longitude, location, point);
        this.currentActivity = currentActivity;
        this.mostRecentAction = mostRecentAction;
        this.potholes = potholes;
        this.SSA = SSA;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }

    public String getMostRecentAction() {
        return mostRecentAction;
    }

    public void setMostRecentAction(String mostRecentAction) {
        this.mostRecentAction = mostRecentAction;
    }

    public Float getPotholes() {
        return potholes;
    }

    public void setPotholes(Float potholes) {
        this.potholes = potholes;
    }

    public String getSSA() {
        return SSA;
    }

    public void setSSA(String SSA) {
        this.SSA = SSA;
    }
}
