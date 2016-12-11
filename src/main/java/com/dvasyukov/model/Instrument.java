package com.dvasyukov.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = Instrument.COLLECTION_NAME)
public class Instrument {
    public static final String COLLECTION_NAME = "instruments";

    @Id
    private Long Id;

    private String Name;
    private String Type;
    private String Price;
    private String DateAdded;

    public Instrument(){}

    public Instrument(Long id, String name, String price, String dateAdded) {
        Id = id;
        Name = name;
        Price = price;
        DateAdded = dateAdded;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(String dateAdded) {
        DateAdded = dateAdded;
    }
}
