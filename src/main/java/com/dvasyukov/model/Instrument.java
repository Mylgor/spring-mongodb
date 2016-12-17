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
    private int Price;
    private Date DateAdded;

    public Instrument(){}

    public Instrument(String name, String type, int price, Date dateAdded) {
        //Id = id;
        Name = name;
        Type = type;
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

    public int getPrice() {
        return Price;
    }

    public void setPrice(int price) {
        Price = price;
    }

    public Date getDateAdded() {
        return DateAdded;
    }

    public void setDateAdded(Date dateAdded) {
        DateAdded = dateAdded;
    }

    @Override
    public final String toString() {
        String text = "";
        String closeP = "</p>";

        if (Name != null) {
            text += "<p>Name: " + Name + closeP;
        }
        if (Type != null) {
            text += "<p>Type: " + Type + closeP;
        }
        if (Price != 0) {
            text += "<p>Price: " + Price + closeP;
        }
        if (DateAdded != null){
            text += "<p>Date added: " + DateAdded + closeP;
        }


        return text;
    }
}
