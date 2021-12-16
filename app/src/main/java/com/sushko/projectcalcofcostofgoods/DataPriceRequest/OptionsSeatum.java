package com.sushko.projectcalcofcostofgoods.DataPriceRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OptionsSeatum
{

    public OptionsSeatum(int volumetricWidth, int volumetricLength, int volumetricHeight, int weight)
    {
        this.weight = weight;
        this.volumetricLength = volumetricLength;
        this.volumetricWidth = volumetricWidth;
        this.volumetricHeight = volumetricHeight;
    }

    @SerializedName("weight")
    @Expose
    private int weight;

    @SerializedName("volumetricWidth")
    @Expose
    private int volumetricWidth;

    @SerializedName("volumetricLength")
    @Expose
    private int volumetricLength;

    @SerializedName("volumetricHeight")
    @Expose
    private int volumetricHeight;

    public int getVolumetricWidth() { return volumetricWidth; }
    public void setVolumetricWidth(int volumetricWidth) { this.volumetricWidth = volumetricWidth; }

    public int getVolumetricLength() { return volumetricLength; }
    public void setVolumetricLength(int volumetricLength) { this.volumetricLength = volumetricLength; }

    public int getVolumetricHeight() { return volumetricHeight; }
    public void setVolumetricHeight(int volumetricHeight) { this.volumetricHeight = volumetricHeight; }

    public int getWeight() { return weight; }
    public void setWeight(int weight) { this.weight = weight; }
}
