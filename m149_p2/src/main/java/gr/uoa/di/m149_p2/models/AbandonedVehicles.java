package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class AbandonedVehicles extends Request{
    private String plate;
    private String model;
    private String color;
    private String currentActivity;
    private String mostRecentAction;
    private Double daysParked;
    private String ssa;

    public AbandonedVehicles(Date creationDate, String status, Date completionDate, String serviceRequestNumber,
                             String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x,
                             BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea,
                             BigDecimal latitude, BigDecimal longitude, String location, GeoJsonPoint point,
                             Integer upVotes, String plate, String model, String color, String currentActivity,
                             String mostRecentAction, Double daysParked, String ssa) {
        super(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
        this.plate = plate;
        this.model = model;
        this.color = color;
        this.currentActivity = currentActivity;
        this.mostRecentAction = mostRecentAction;
        this.daysParked = daysParked;
        this.ssa = ssa;
    }

    public String getPlate() {
        return plate;
    }

    public void setPlate(String plate) {
        this.plate = plate;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
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

    public Double getDaysParked() {
        return daysParked;
    }

    public void setDaysParked(Double daysParked) {
        this.daysParked = daysParked;
    }

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }
}
