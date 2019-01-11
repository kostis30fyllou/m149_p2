package gr.uoa.di.m149_p2.models;

import com.mongodb.client.model.geojson.Point;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.util.Date;

@Document
public class TreeTrims extends Request {
    private String treeLocation;

    public TreeTrims(Date creationDate, String status, Date completionDate, String serviceRequestNumber, String typeOfServiceRequest,
                     String streetAddress, Integer zipCode, BigDecimal x, BigDecimal y, Integer ward, Integer policeDistrict,
                     Integer communityArea, BigDecimal latitude, BigDecimal longitude, String location, Point point,
                     String treeLocation) {
        super(creationDate, status, completionDate, serviceRequestNumber, typeOfServiceRequest, streetAddress, zipCode, x, y, ward, policeDistrict, communityArea, latitude, longitude, location, point);
        this.treeLocation = treeLocation;
    }

    public String getTreeLocation() {
        return treeLocation;
    }

    public void setTreeLocation(String treeLocation) {
        this.treeLocation = treeLocation;
    }
}
