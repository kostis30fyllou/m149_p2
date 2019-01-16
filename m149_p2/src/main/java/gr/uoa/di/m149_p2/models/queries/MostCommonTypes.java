package gr.uoa.di.m149_p2.models.queries;

import java.util.List;

public class MostCommonTypes {

    private Integer zipCode;
    private List<TotalTypeRequests> types;

    public Integer getZipCode() {
        return zipCode;
    }

    public void setZipCode(Integer zipCode) {
        this.zipCode = zipCode;
    }

    public List<TotalTypeRequests> getTypes() {
        return types;
    }

    public void setTypes(List<TotalTypeRequests> types) {
        this.types = types;
    }
}
