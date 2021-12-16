package com.sushko.projectcalcofcostofgoods.DataPriceRequest;

import com.sushko.projectcalcofcostofgoods.DataAddressRequest.AddressData;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface RetrofitAPI_2
{
    @POST("users")
    Call<PriceData> createPost(@Body PriceData priceData);
}
