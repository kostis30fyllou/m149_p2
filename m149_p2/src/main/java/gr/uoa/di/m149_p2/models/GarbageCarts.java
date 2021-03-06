package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class GarbageCarts extends Request{
    private Double numberOfCarts;
    private String currentActivity;
    private String mostRecentAction;
    private String ssa;

    public GarbageCarts(Long id, Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, GeoJsonPoint point, Integer upVotes, Double numberOfCarts, String currentActivity, String mostRecentAction, String ssa) {
        super(id, creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
        this.numberOfCarts = numberOfCarts;
        this.currentActivity = currentActivity;
        this.mostRecentAction = mostRecentAction;
        this.ssa = ssa;
    }

    public Double getNumberOfCarts() {
        return numberOfCarts;
    }

    public void setNumberOfCarts(Double numberOfCarts) {
        this.numberOfCarts = numberOfCarts;
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

    public String getSsa() {
        return ssa;
    }

    public void setSsa(String ssa) {
        this.ssa = ssa;
    }
}
