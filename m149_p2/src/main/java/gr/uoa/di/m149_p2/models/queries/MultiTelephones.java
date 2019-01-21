package gr.uoa.di.m149_p2.models.queries;

import java.util.List;

public class MultiTelephones {

    private String telephone;
    private List<String> names;
    private Long count;

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
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
