package com.sushko.projectcalcofcostofgoods.DataAddressRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface RetrofitAPI
{
    @POST("users")
    Call<AddressData> createPost(@Body AddressData addressData);
}
