package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class TreeDebris extends Request{

    private String debrisLocations;
    private String currentActivity;
    private String mostRecentAction;

    public TreeDebris(Long id, Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, GeoJsonPoint point, Integer upVotes, String debrisLocations, String currentActivity, String mostRecentAction) {
        super(id, creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
        this.debrisLocations = debrisLocations;
        this.currentActivity = currentActivity;
        this.mostRecentAction = mostRecentAction;
    }

    public String getDebrisLocations() {
        return debrisLocations;
    }

    public void setDebrisLocations(String debrisLocations) {
        this.debrisLocations = debrisLocations;
    }

    public String getMostRecentAction() {
        return mostRecentAction;
    }

    public void setMostRecentAction(String mostRecentAction) {
        this.mostRecentAction = mostRecentAction;
    }

    public String getCurrentActivity() {
        return currentActivity;
    }

    public void setCurrentActivity(String currentActivity) {
        this.currentActivity = currentActivity;
    }
}
