package com.kitkat.android.retrofit;

import android.app.ProgressDialog;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.kitkat.android.retrofit.domain.Data;
import com.kitkat.android.retrofit.domain.Row;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/** Retrofit
 *  http://square.github.io/retrofit/
 *  A Type-Safe HTTP Client for Android and Java
 *
 *  Retrofit turns your HTTP API into a Java interface.
 *
 *      public interface GitHubService {
 *          @GET("users/{user}/repos")
 *          Call<List<Repo>> listRepos(@Path("user") String user);
 *      }
 *
 *  The Retrofit class Instantiate an implementation (구현체) of the Java interface (HTTP API).
 *
 *      Retrofit retrofit = new Retrofit.Builder()
 *          .baseUrl("https://api.github.com/")
 *          .build();
 *
 *      GitHubService service = retrofit.create(GitHubService.class);
 *
 *  Each Call Instance from the created interface
 *  can make a synchronous or asynchronous HTTP request to the remote webserver.
 *  → Retrofit is Support Sub Threading!
 *
 *      Call<List<Repo>> repos = service.listRepos("octocat");
 *
 *  Use Annotations to describe the HTTP request Method
 *
 *      - URL parameter replacement and query parameter support
 *        (URL 상의 Parameter 와 Query String 에 대한 지원)
 *
 *      - Object conversion to request body (e.g., JSON, protocol buffers)
 *
 *      - Multipart request body and file upload
 *
 *  API Declaration
 *      Annotations on the interface methods and its parameters indicate how a request will be handled.
 *
 *  Request Method
 *      Every method must have an HTTP annotation that provides the request method and relative URL.
 *      There are five built-in annotations: GET, POST, PUT, DELETE, and HEAD. The relative URL of the resource is specified in the annotation.
 **
 *      @GET("users/list")
 *
 *      You can also specify query parameters in the URL.
 *
 *      @GET("users/list?sort=desc")
 *
 *  URL Manipulation (URL 조작)
 *      A request URL can be updated dynamically using replacement blocks and parameters on the method.
 *      A replacement block is an alphanumeric string surrounded by { and }.
 *      A corresponding parameter must be annotated with @Path using the same string.
 *
 *      @GET("group/{id}/users")
 *      Call<List<User>> groupList(@Path("id") int groupId);
 *
 *      Query parameters can also be added.
 *
 *      @GET("group/{id}/users")
 *      Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
 *
 *      For complex query parameter combinations a Map can be used.
 *
 *      @GET("group/{id}/users")
 *      Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
 *
 *  Request Body
 *      An object can be specified for use as an HTTP request body with the @Body annotation.
 *
 *      @POST("users/new")
 *      Call<User> createUser(@Body User user);
 *
 *      The object will also be converted using a converter specified on the Retrofit instance. If no converter is added, only RequestBody can be used.
 *
 *  Form Encoded and Multipart
 *      Methods can also be declared to send form-encoded and multipart data.
 *      Form-encoded data is sent when @FormUrlEncoded is present on the method.
 *      Each key-value pair is annotated with @Field containing the name and the object providing the value.
 *
 *      @FormUrlEncoded
 *      @POST("user/edit")
 *      Call<User> updateUser(@Field("first_name") String first, @Field("last_name") String last);
 *      Multipart requests are used when @Multipart is present on the method. Parts are declared using the @Part annotation.
 *
 *      @Multipart
 *      @PUT("user/photo")
 *      Call<User> updateUser(@Part("photo") RequestBody photo, @Part("description") RequestBody description);
 *      Multipart parts use one of Retrofit's converters or they can implement RequestBody to handle their own serialization.
 *
 *  Header Manipulation
 *      You can set static headers for a method using the @Headers annotation.
 *
 *      @Headers("Cache-Control: max-age=640000")
 *      @GET("widget/list")
 *      Call<List<Widget>> widgetList();
 *
 *      @Headers({
 *          "Accept: application/vnd.github.v3.full+json",
 *          "User-Agent: Retrofit-Sample-App"
 *      })
 *      @GET("users/{username}")
 *      Call<User> getUser(@Path("username") String username);
 *
 *      Note that headers do not overwrite each other. All headers with the same name will be included in the request.
 *      A request Header can be updated dynamically using the @Header annotation. A corresponding parameter must be provided to the @Header.
 *
 *      @GET("user")
 *      Call<User> getUser(@Header("Authorization") String authorization)
 *
 *      Headers that need to be added to every request can be specified using an OkHttp interceptor.
 *
 *  Synchronous vs. Asynchronous
 *      Call instances can be executed either synchronously or asynchronously.
 *      Each instance can only be used once, but calling clone() will create a new instance that can be used.
 *
 *      On Android, callbacks will be executed on the main thread.
 *      On the JVM, callbacks will happen on the same thread that executed the HTTP request.
 *
 *  Retrofit Configuration
 *      Retrofit is the class through which your API interfaces are turned into callable objects.
 *      By default, Retrofit will give you sane defaults for your platform but it allows for customization.
 *
 *  Converters
 *      By default, Retrofit can only deserialize HTTP bodies into OkHttp's ResponseBody type and it can only accept its RequestBody type for @Body.
 *      Converters can be added to support other types. Six sibling modules adapt popular serialization libraries for your convenience.
 *
 *      Gson: com.squareup.retrofit2:converter-gson
 *      Jackson: com.squareup.retrofit2:converter-jackson
 *      Moshi: com.squareup.retrofit2:converter-moshi
 *      Protobuf: com.squareup.retrofit2:converter-protobuf
 *      Wire: com.squareup.retrofit2:converter-wire
 *      Simple XML: com.squareup.retrofit2:converter-simplexml
 *      Scalars (primitives, boxed, and String): com.squareup.retrofit2:converter-scalars
 *
 *  Gson
 *  https://sites.google.com/site/gson/gson-user-guide (사용법 참고)
 *
 *      Gson is a Java library that can be used to convert Java Objects into their JSON representation.
 *      It can also be used to convert a JSON string to an equivalent Java object.
 *      Gson is an open-source project hosted at http://code.google.com/p/google-gson.
 *      Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.
 *
 *  Goals for Gson
 *      - Provide easy to use mechanisms like toString() and constructor (factory method) to convert Java to JSON and vice-versa
 *      - Allow pre-existing unmodifiable objects to be converted to and from JSON
 *      - Allow custom representations for objects
 *      - Support arbitrarily complex object
 *      - Generate compact and readability JSON output
 */

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {
    private GoogleMap mMap;
    public static final String baseUrl = "http://openapi.seoul.go.kr:8088/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Move the Camera Seoul
        LatLng seoul = new LatLng(37.566696, 126.977942);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(seoul, 10f));

        retrofit(baseUrl);
    }

    public void retrofit(String baseUrl) {
        final ProgressDialog dialog = new ProgressDialog(this);
        dialog.setMessage("Retrofit Loading..");
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.show();

        // 1. Retrofit Class Instantiate
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(baseUrl) // Set Base Url
                .addConverterFactory(GsonConverterFactory.create()) // Set GSON Converter
                .build();

        // 2. Retrofit Instance 로 Runtime 시 HTTP API Java Interface 구현체 인스턴스화
        OpenApiService openApiService = retrofit.create(OpenApiService.class);

        // 3. HTTP API Java Interface 구현체의 Method 로  Web Server 로 Request 및 Response
        Call<Data> result = openApiService.get("중구", 1, 1000);

        // 4. Call Instance 의 비동기 Callback Method 로 JSON String 반환
        // Android 에서는 비동기  Callback Method 가 Main Thread 로 동작.
        // interface 구현체의 각 Call Instance 는 Remote Web Server 로 동기식 또는 비동기식 HTTP Request 가능.
        result.enqueue(new Callback<Data>() {
            // Data Request 성공 시 호출되는 Callback Method
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                // Response Message is Successful
                if(response.isSuccessful()) {
                    Log.i("Retrofit", "Response is Successful");

                    // 5. Response Instance 의 JSON String 으로 부터 Java Instance 반환
                    // JSON String 을 Java Instance 로 변환하는 라이브러리인 GSON Converter 적용.
                    Data data = response.body();

                    List<Integer> parkingList = new ArrayList<>();
                    for(Row row : data.getSearchParkingInfoRealtime().getRow()) {
                        int parkingCode = Integer.parseInt(row.getPARKING_CODE());

                        if(parkingList.contains(parkingCode))
                            continue;

                        parkingList.add(parkingCode);

                        double lat = Double.parseDouble(row.getLAT());
                        double lng = Double.parseDouble(row.getLNG());

                        double capa = Double.parseDouble(row.getCAPACITY());
                        double cur = Double.parseDouble(row.getCUR_PARKING());
                        double remain = capa - cur;

                        LatLng parking = new LatLng(lat, lng);
                        mMap.addMarker(new MarkerOptions().position(parking).title((int)remain + " / " + (int)capa));
                    }

                    dialog.dismiss();

                } else {
                    // Response Message is Error
                    Log.e("Retrofit", response.message());
                }
            }

            // Data Request 실패 시 호출되는 Callback Method
            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }
}
