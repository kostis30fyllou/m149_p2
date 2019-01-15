package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.geo.GeoJsonPoint;

import java.math.BigDecimal;
import java.util.Date;

public class StreetLightOut extends Request{

    public StreetLightOut(Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest, String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict, Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, GeoJsonPoint point, Integer upVotes) {
        super(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point, upVotes);
    }
}
