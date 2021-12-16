package com.sushko.projectcalcofcostofgoods.DataAddressRequest;

import java.util.List;

public class AddressData
{
    private String modelName;
    private String calledMethod;
    private String apiKey;
    private MethodProperties methodProperties;

    private boolean success;
    private List<Data> data = null;

    public AddressData(String modelName, String calledMethod, String apiKey, String FindByString)
    {
        this.modelName = modelName;
        this.calledMethod = calledMethod;
        this.apiKey = apiKey;
        methodProperties = new MethodProperties(FindByString);
    }

    public String getModelName()
    {
        return modelName;
    }

    public void setModelName(String modelName) { this.modelName = modelName; }


    public String getCalledMethod()
    {
        return calledMethod;
    }

    public void setCalledMethod(String CalledMethod) { this.calledMethod = CalledMethod; }


    public String getApiKey()
    {
        return apiKey;
    }

    public void setApiKey(String ApiKey) { this.apiKey = ApiKey; }


    public String getMethodProperties() { return methodProperties.getFindByString(); }

    public void setMethodProperties(String FindByString) { methodProperties.setFindByString(FindByString); }


    //Response
    public boolean getSuccess()
    {
        return success;
    }

    public void setSuccess(boolean Success) { this.success = Success; }


    public List<Data> getData()
    {
        return data;
    }

    public void setData(List<Data> data)
    {
        this.data = data;
    }

}

class MethodProperties
{
    private String FindByString;

    public MethodProperties(String FindByString)
    {
        this.FindByString = FindByString;
    }

    public String getFindByString() { return FindByString; }

    public void setFindByString(String FindByString) { this.FindByString = FindByString; }
}