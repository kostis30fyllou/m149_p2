package gr.uoa.di.m149_p2.models.queries;

import java.util.Date;

public class DailyRequests {

    private Date creationDate;
    private Long count;

    public Date getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public Long getCount() {
        return count;
    }

    public void setCount(Long count) {
        this.count = count;
    }
}
