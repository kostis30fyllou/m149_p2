package gr.uoa.di.m149_p2.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;

@Document
public class User {

    @Id
    private String id;
    private String name;
    private String telephone;
    private String address;
    private ArrayList<Request> upVoted;

    public User(String name, String telephone, String address, ArrayList<Request> upVoted) {
        this.name = name;
        this.telephone = telephone;
        this.address = address;
        this.upVoted = upVoted;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public ArrayList<Request> getUpVoted() {
        return upVoted;
    }

    public void setUpVoted(ArrayList<Request> upVoted) {
        this.upVoted = upVoted;
    }
}
