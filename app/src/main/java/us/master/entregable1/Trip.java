package us.master.entregable1;

import java.time.LocalDate;

public class Trip {
    private String destination;
    private String startPoint;
    private LocalDate arrivalDate;
    private LocalDate departureDate;
    private double price;
    private boolean isSelected;
    private String description;

    public Trip(String destination, String startPoint, LocalDate arrivalDate, LocalDate departureDate, double price, boolean isSelected, String description) {
        this.destination = destination;
        this.startPoint = startPoint;
        this.arrivalDate = arrivalDate;
        this.departureDate = departureDate;
        this.price = price;
        this.isSelected = isSelected;
        this.description = description;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getStartPoint() {
        return startPoint;
    }

    public void setStartPoint(String startPoint) {
        this.startPoint = startPoint;
    }

    public LocalDate getArrivalDate() {
        return arrivalDate;
    }

    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
    }

    public LocalDate getDepartureDate() {
        return departureDate;
    }

    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Trip{" +
                "destination='" + destination + '\'' +
                ", startPoint='" + startPoint + '\'' +
                ", arrivalDate=" + arrivalDate +
                ", departureDate=" + departureDate +
                ", price=" + price +
                ", isSelected=" + isSelected +
                ", description='" + description + '\'' +
                '}';
    }
}
