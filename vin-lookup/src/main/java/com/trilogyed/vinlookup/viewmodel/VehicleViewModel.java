package com.trilogyed.vinlookup.viewmodel;

import java.util.Objects;


public class VehicleViewModel {


    private String vin;
    private String type;
    private String make;
    private String model;
    private String year;
    private String color;

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof VehicleViewModel)) return false;
        VehicleViewModel that = (VehicleViewModel) o;
        return getVin().equals(that.getVin()) &&
                getType().equals(that.getType()) &&
                getMake().equals(that.getMake()) &&
                getModel().equals(that.getModel()) &&
                getYear().equals(that.getYear()) &&
                getColor().equals(that.getColor());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getVin(), getType(), getMake(), getModel(), getYear(), getColor());
    }
}
