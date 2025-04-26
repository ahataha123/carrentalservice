package org.example.carrental.model;

import jakarta.persistence.*;

@Entity
public class Car {
    @Id @GeneratedValue
    private Long id;

    // rename “make” → “brand”
    private String brand;

    private String model;

    // new field for the plate number
    private String licensePlate;

    // now always stored in USD
    private double price;
    private String currency;

    private boolean available;

    // — getters/setters —

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getBrand() { return brand; }
    public void setBrand(String brand) { this.brand = brand; }

    public String getModel() { return model; }
    public void setModel(String model) { this.model = model; }

    public String getLicensePlate() { return licensePlate; }
    public void setLicensePlate(String licensePlate) { this.licensePlate = licensePlate; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCurrency() { return currency; }
    public void setCurrency(String currency) { this.currency = currency; }

    public boolean isAvailable() { return available; }
    public void setAvailable(boolean available) { this.available = available; }
}
