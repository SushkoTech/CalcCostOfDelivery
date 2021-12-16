package com.sushko.projectcalcofcostofgoods.DataPriceRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class MethodProperties
{
    public MethodProperties(String RefSender, String RefRecipient, int Weight, String ServiceType, String CargoType, int cost, int seatsAmount) {
        this.citySender = RefSender;
        this.cityRecipient = RefRecipient;
        this.weight = Weight;
        this.serviceType = ServiceType;
        this.cargoType = CargoType;
        this.cost = cost;
        this.seatsAmount = seatsAmount;
    }

    @SerializedName("CitySender")
    @Expose
    private String citySender;

    @SerializedName("CityRecipient")
    @Expose
    private String cityRecipient;

    @SerializedName("Weight")
    @Expose
    private int weight;

    @SerializedName("ServiceType")
    @Expose
    private String serviceType;

    @SerializedName("Cost")
    @Expose
    private int cost;

    @SerializedName("CargoType")
    @Expose
    private String cargoType;

    @SerializedName("SeatsAmount")
    @Expose
    private int seatsAmount;

    public String getCitySender() {
        return citySender;
    }

    public void setCitySender(String citySender) {
        this.citySender = citySender;
    }

    public String getCityRecipient() {
        return cityRecipient;
    }

    public void setCityRecipient(String cityRecipient) {
        this.cityRecipient = cityRecipient;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public String getCargoType() {
        return cargoType;
    }

    public void setCargoType(String cargoType) {
        this.cargoType = cargoType;
    }

    public int getSeatsAmount() {
        return seatsAmount;
    }

    public void setSeatsAmount(int seatsAmount) {
        this.seatsAmount = seatsAmount;
    }

}
