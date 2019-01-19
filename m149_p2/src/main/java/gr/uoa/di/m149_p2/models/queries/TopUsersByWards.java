package gr.uoa.di.m149_p2.models.queries;

public class TopUsersByWards {

    private Long id;
    private String name;
    private Integer wards;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getWards() {
        return wards;
    }

    public void setWards(Integer wards) {
        this.wards = wards;
    }
}
