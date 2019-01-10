package gr.uoa.di.m149_p2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class sanitation extends Request {

    @Id
    private String codeViolation;

    

    public String getCodeViolation() {
        return codeViolation;
    }

    public void setCodeViolation(String codeViolation) {
        this.codeViolation = codeViolation;
    }
}
