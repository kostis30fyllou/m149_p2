package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class RodentBaiting extends Request {

    private Float premisesBaited;
    private Integer premisesWithGarbage;
    private Float premisesWithRats;
    private String currentActivity;
    private String mostRecentAction;

    public RodentBaiting(Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, Point point, Integer upVotes, Float premisesBaited, Integer premisesWithGarbage, Float premisesWithRats, String currentActivity, String mostRecentAction) {
        super(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
        this.premisesBaited = premisesBaited;
        this.premisesWithGarbage = premisesWithGarbage;
        this.premisesWithRats = premisesWithRats;
        this.currentActivity = currentActivity;
        this.mostRecentAction = mostRecentAction;
    }

    public Float getPremisesBaited() {
        return premisesBaited;
    }

    public void setPremisesBaited(Float premisesBaited) {
        this.premisesBaited = premisesBaited;
    }

    public Integer getPremisesWithGarbage() {
        return premisesWithGarbage;
    }

    public void setPremisesWithGarbage(Integer premisesWithGarbage) {
        this.premisesWithGarbage = premisesWithGarbage;
    }

    public Float getPremisesWithRats() {
        return premisesWithRats;
    }

    public void setPremisesWithRats(Float premisesWithRats) {
        this.premisesWithRats = premisesWithRats;
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
}
