package com.sushko.projectcalcofcostofgoods.DataPriceRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Datum
{
    @SerializedName("AssessedCost")
    @Expose
    private int assessedCost;

    @SerializedName("Cost")
    @Expose
    private int cost;

    public int getAssessedCost() { return assessedCost; }
    public void setAssessedCost(Integer assessedCost) { this.assessedCost = assessedCost; }

    public int getCost() { return cost; }
    public void setCost(int cost) { this.cost = cost; }
}
