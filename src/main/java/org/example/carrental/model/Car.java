package org.example.carrental.model;



import jakarta.persistence.*;


@Entity
@Table(name = "cars")
public class Car {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String brand;

    @Column(nullable = false)
    private String model;

    @Column(name = "manufacture_year", nullable = false)
    private int manufactureYear;

    @Column(nullable = false, unique = true)
    private String licensePlate;

    @Column(nullable = false)
    private double pricePerDayUsd;

    @Column(nullable = false)
    private boolean available = true;

    public Car() {}

    public Car(String brand, String model, int manufactureYear, String licensePlate, double pricePerDayUsd, boolean available) {
        this.brand = brand;
        this.model = model;
        this.manufactureYear = manufactureYear;
        this.licensePlate = licensePlate;
        this.pricePerDayUsd = pricePerDayUsd;
        this.available = available;
    }

    public Long getId() {
        return id;
    }

    public String getBrand() {
        return brand;
    }
    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getModel() {
        return model;
    }
    public void setModel(String model) {
        this.model = model;
    }

    public int getmanufactureYear() {
        return manufactureYear;
    }
    public void setmanufactureYear(int manufactureYear) {
        this.manufactureYear = manufactureYear;
    }

    public String getLicensePlate() {
        return licensePlate;
    }
    public void setLicensePlate(String licensePlate) {
        this.licensePlate = licensePlate;
    }

    public double getPricePerDayUsd() {
        return pricePerDayUsd;
    }
    public void setPricePerDayUsd(double pricePerDayUsd) {
        this.pricePerDayUsd = pricePerDayUsd;
    }

    public boolean isAvailable() {
        return available;
    }
    public void setAvailable(boolean available) {
        this.available = available;
    }
}
