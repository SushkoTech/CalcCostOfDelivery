package com.sushko.projectcalcofcostofgoods.DataPriceRequest;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class PriceData
{
    public PriceData(String modelName, String calledMethod, String apiKey, String RefSender, String RefRecipient, int Weight, String ServiceType, String CargoType, int cost, int seatsAmount, int volumetricWidth, int volumetricLength, int volumetricHeight)
    {
        this.modelName = modelName;
        this.calledMethod = calledMethod;
        this.apiKey = apiKey;
        this.methodProperties = new MethodProperties(RefSender, RefRecipient, Weight, ServiceType, CargoType, cost, seatsAmount);
        OptionsSeat =  new ArrayList<OptionsSeatum>();
        OptionsSeat.add(new OptionsSeatum(volumetricWidth, volumetricLength, volumetricHeight, Weight));
    }

    @SerializedName("modelName")
    @Expose
    private String modelName;

    @SerializedName("calledMethod")
    @Expose
    private String calledMethod;

    @SerializedName("methodProperties")
    @Expose
    private MethodProperties methodProperties;

    @SerializedName("apiKey")
    @Expose
    private String apiKey;

    @SerializedName("OptionsSeat")
    @Expose
    private ArrayList<OptionsSeatum> OptionsSeat = null;


    @SerializedName("success")
    @Expose
    private Boolean success;

    @SerializedName("data")
    @Expose
    private List<Datum> data = null;

    public String getModelName() { return modelName; }
    public void setModelName(String modelName) { this.modelName = modelName; }

    public String getCalledMethod() { return calledMethod; }
    public void setCalledMethod(String calledMethod) { this.calledMethod = calledMethod; }

    public MethodProperties getMethodProperties() { return methodProperties; }
    public void setMethodProperties(MethodProperties methodProperties) { this.methodProperties = methodProperties; }

    public String getApiKey() { return apiKey; }
    public void setApiKey(String apiKey) { this.apiKey = apiKey; }

    public ArrayList<OptionsSeatum> getOptionsSeat() { return OptionsSeat; }
    public void setOptionsSeat(ArrayList<OptionsSeatum> optionsSeat) { OptionsSeat = optionsSeat; }


    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }

    public List<Datum> getData() { return data; }
    public void setData(List<Datum> data) { this.data = data; }

}

