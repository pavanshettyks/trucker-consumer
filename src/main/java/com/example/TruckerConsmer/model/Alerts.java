package com.example.TruckerConsmer.model;

import lombok.Data;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.util.UUID;

@Data
@Entity
public class Alerts {
    @Id
    private String id;
    private String vin;
    private String priority;
    private String description;
//    @OneToOne(cascade= CascadeType.ALL)
//    private Reading reading;

    public Alerts(){
        this.id = UUID.randomUUID().toString();
    }


}
