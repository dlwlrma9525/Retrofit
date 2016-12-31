package com.kitkat.android.retrofit;

import com.kitkat.android.retrofit.domain.Data;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/** HTTP API To Java Interface for Retrofit
 *
 */
public interface OpenApiService {
    @GET("666569554d63686f36356b6f5a615a/json/SearchParkingInfoRealtime/{start}/{end}/{gu}") // HTTP Method Annotation
    Call<Data> get(@Path("gu") String gu, @Path("start") int start, @Path("end") int end); // Call<Data> : 전체 JSON String
}
