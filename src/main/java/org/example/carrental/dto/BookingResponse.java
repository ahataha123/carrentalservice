package org.example.carrental.dto;

import java.time.LocalDate;

public class BookingResponse {

    private Long bookingId;
    private String userName;
    private String carModel;
    private String carBrand;
    private String licensePlate;
    private LocalDate startDate;
    private LocalDate endDate;
    private double totalCostUsd;

    public BookingResponse(Long bookingId, String userName, String carModel,
                           String carBrand, String licensePlate,
                           LocalDate startDate, LocalDate endDate, double totalCostUsd) {
        this.bookingId = bookingId;
        this.userName = userName;
        this.carModel = carModel;
        this.carBrand = carBrand;
        this.licensePlate = licensePlate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.totalCostUsd = totalCostUsd;
    }

    public Long getBookingId() {
        return bookingId;
    }

    public String getUserName() {
        return userName;
    }

    public String getCarModel() {
        return carModel;
    }

    public String getCarBrand() {
        return carBrand;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getTotalCostUsd() {
        return totalCostUsd;
    }
}
