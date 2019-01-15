package gr.uoa.di.m149_p2.models.queries;

public class AvgRequestCompletion {

    private String typeOfServiceRequest;
    private Double avg;

    public String getTypeOfServiceRequest() {
        return typeOfServiceRequest;
    }

    public void setTypeOfServiceRequest(String typeOfServiceRequest) {
        this.typeOfServiceRequest = typeOfServiceRequest;
    }

    public Double getAvg() {
        return avg;
    }

    public void setAvg(Double avg) {
        this.avg = avg;
    }
}
