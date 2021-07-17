package com.example.getwithparam;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIInterface {

    @GET("register_service.php")
    Call<ParamModel> getStatus(@Query("first_name") String firstname, @Query("last_name") String lastname, @Query("email") String email,
                               @Query("mobile_number") String mobile,  @Query("city") String city,  @Query("password") String password);
}
