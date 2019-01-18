package gr.uoa.di.m149_p2.models.queries;

public class MostVotedRequests {
    private Long id;
    private String typeOfServiceRequest;
    private Integer upVotes;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTypeOfServiceRequest() {
        return typeOfServiceRequest;
    }

    public void setTypeOfServiceRequest(String typeOfServiceRequest) {
        this.typeOfServiceRequest = typeOfServiceRequest;
    }

    public Integer getUpVotes() {
        return upVotes;
    }

    public void setUpVotes(Integer upVotes) {
        this.upVotes = upVotes;
    }
}
