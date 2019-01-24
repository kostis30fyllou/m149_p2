package gr.uoa.di.m149_p2.models.queries;

import java.util.List;

public class MultiTelephones {

    private String telephone;
    private Long incident_id;
    private List<String> names;
    private Long count;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Long getIncident_id() {
        return incident_id;
    }

    public void setIncident_id(Long incident_id) {
        this.incident_id = incident_id;
    }

    public List<String> getNames() {
        return names;
    }

    public void setNames(List<String> names) {
        this.names = names;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
