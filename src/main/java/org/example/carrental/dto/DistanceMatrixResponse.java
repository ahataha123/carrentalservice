package org.example.carrental.dto;

public class DistanceMatrixResponse {
    private String distanceText;
    private String durationText;

    public DistanceMatrixResponse() {}

    public DistanceMatrixResponse(String distanceText, String durationText) {
        this.distanceText = distanceText;
        this.durationText = durationText;
    }

    public String getDistanceText() {
        return distanceText;
    }

    public void setDistanceText(String distanceText) {
        this.distanceText = distanceText;
    }

    public String getDurationText() {
        return durationText;
    }

    public void setDurationText(String durationText) {
        this.durationText = durationText;
    }
}
