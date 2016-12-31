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
 *  A type-safe HTTP client for Android and Java
 *
 *  Retrofit turns your HTTP API into a Java interface.
 *      -> Retrofit 은 HTTP API 를 Java Interface 로 교체해준다.
 *
 *      public interface GitHubService {
 *          @GET("users/{user}/repos")
 *          Call<List<Repo>> listRepos(@Path("user") String user);
 *      }
 *
 *  The Retrofit class generates an implementation of the Java interface (HTTP API).
 *      -> Retrofit Class 는 HTTP API 의 Java 인터페이스 구현체를 생성한다.
 *
 *      Retrofit retrofit = new Retrofit.Builder()
 *          .baseUrl("https://api.github.com/")
 *          .build();
 *
 *      GitHubService service = retrofit.create(GitHubService.class);
 *
 *  Each Call from the created interface can make a synchronous or asynchronous HTTP request to the remote webserver.
 *      -> 생성 된 interface 구현체의 각 Call 인스턴스는 원격 웹 서버에 대한 동기식 또는 비동기식 HTTP 요청을 만들 수 있다.
 *      -> Retrofit 은 Sub Thread 지원 OK!
 *
 *      Call<List<Repo>> repos = service.listRepos("octocat");
 *
 *  Use annotations to describe the HTTP request:
 *  (HTTP Method 를 어노테이션을 사용하여 기술 가능)
 *
 *      - URL parameter replacement and query parameter support
 *        (URL 상의 Parameter 와 Query String 에 대한 지원)
 *
 *      - Object conversion to request body (e.g., JSON, protocol buffers)
 *        (Request Body 로 부터 객체 변환 지원)
 *
 *      - Multipart request body and file upload
 *
 *  API Declaration
 *      Annotations on the interface methods and its parameters indicate how a request will be handled.
 *
 *  Request Method
 *      Every method must have an HTTP annotation that provides the request method and relative URL.
 *      There are five built-in annotations: GET, POST, PUT, DELETE, and HEAD. The relative URL of the resource is specified in the annotation.
 *
 *      모든 메소드에는 HTTP 어노테이션이 있어야 한다. (HTTP Request Method + 상대 URL)
 *      GET, POST, PUT, DELETE 및 HEAD와 같은 5 가지 기본 제공 주석이 있다. 리소스의 상대 URL 은 주석에 지정.
 *
 *      @GET("users/list")
 *
 *      You can also specify query parameters in the URL.
 *      URL 주소에 Query String 명시 가능
 *
 *      @GET("users/list?sort=desc")
 *
 *  URL Manipulation (URL 조작)
 *      A request URL can be updated dynamically using replacement blocks and parameters on the method.
 *      A replacement block is an alphanumeric string surrounded by { and }.
 *      A corresponding parameter must be annotated with @Path using the same string.
 *
 *      메서드의 {} 대체 블록 및 파리미터를 사용하여 요청 URL을 동적으로 업데이트 할 수 있다.
 *      대체 블록은 {}로 감싼다. 대응하는 매개 변수는 동일한 문자열을 사용하여 @Path 어노테이션을 사용해야 한다.
 *      경로의 변화가 생길 경우 {} 안의 주소값과 매핑해주는 어노테이션
 *
 *      @GET("group/{id}/users")
 *      Call<List<User>> groupList(@Path("id") int groupId);
 *
 *      Query parameters can also be added.
 *      쿼리 파라미터도 추가 할 수 있다.
 *
 *      @GET("group/{id}/users")
 *      Call<List<User>> groupList(@Path("id") int groupId, @Query("sort") String sort);
 *
 *      For complex query parameter combinations a Map can be used.
 *      복잡한 쿼리 파라미터 조합의 경우 Map을 사용할 수 있다.
 *
 *      @GET("group/{id}/users")
 *      Call<List<User>> groupList(@Path("id") int groupId, @QueryMap Map<String, String> options);
 *
 *  Request Body
 *      An object can be specified for use as an HTTP request body with the @Body annotation.
 *      @Body 어노테이션을 사용하여 HTTP Request Body 로 사용할 객체를 지정할 수 있다.
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
 *      @Headers 어노테이션을 사용하여 메소드에 정적 헤더를 설정할 수 있다.
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
 *      헤더는 서로 겹쳐 쓰지 않는다. 같은 이름의 모든 헤더가 요청에 포함
 *
 *      A request Header can be updated dynamically using the @Header annotation. A corresponding parameter must be provided to the @Header.
 *      요청 헤더는 @Header 어노테이션을 사용하여 동적으로 업데이트 할 수 있다. 해당 매개 변수를 @Header 에 제공해야 한다.
 *
 *      @GET("user")
 *      Call<User> getUser(@Header("Authorization") String authorization)
 *
 *      Headers that need to be added to every request can be specified using an OkHttp interceptor.
 *      모든 요청에 ​​추가해야하는 헤더는 OkHttp 인터셉터를 사용하여 지정할 수 있다.
 *
 *  Synchronous vs. Asynchronous
*       Call instances can be executed either synchronously or asynchronously.
 *      Call 인스턴스는 동 기적 또는 비동기 적으로 실행될 수 있습니다
 *
 *      Each instance can only be used once, but calling clone() will create a new instance that can be used.
 *      각 Call 인스턴스는 한 번만 사용할 수 있지만 clone ()을 호출하면 사용할 수 있는 새 인스턴스가 만들어진다.

 *      On Android, callbacks will be executed on the main thread.
 *      On the JVM, callbacks will happen on the same thread that executed the HTTP request.
 *      Android 에서는 콜백이 주 스레드에서 실행.
 *      JVM 에서 콜백은 HTTP 요청을 실행 한 동일한 스레드에서 발생.
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
 *      It can also be used to convert a JSON string to an equivalent Java object. Gson is an open-source project hosted at http://code.google.com/p/google-gson.
 *      Gson can work with arbitrary Java objects including pre-existing objects that you do not have source-code of.
 *
 *      Gson 은 Java 객체를 JSON 문자열 표현으로 변환하는 데 사용할 수 있는 Java 라이브러리.
 *      또한  JSON 문자열을 동등한 Java 객체로 변환하는 데 사용할 수 있다.
 *      Gson 은 http://code.google.com/p/google-gson에서 호스팅되는 오픈 소스 프로젝트.
 *      Gson 은 소스 코드가 없는 기존 객체를 포함하여 임의의 Java 객체에 대해 작업 할 수 있다.
 *
 *      Goals for Gson
 *          - Provide easy to use mechanisms like toString() and constructor (factory method) to convert Java to JSON and vice-versa
 *            toString() 및 JSON 으로 또는 그 반대로 변환하는 생성자 (팩토리 메서드)와 같은 사용하기 쉬운 메커니즘 제공
 *
 *          - Allow pre-existing unmodifiable objects to be converted to and from JSON
 *            기존의 수정 불가능한 객체가 JSON 과 JSON 간에 변환되도록 허용
 *
 *          - Allow custom representations for objects
 *            객체에 대한 사용자 정의 표현 허용
 *
 *          - Support arbitrarily complex object
 *            임의로 복잡한 객체 지원
 *
 *          - Generate compact and readability JSON output
 *            작고 가독성있는 JSON 출력 생성
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

        // move the Camera Seoul
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

        // 2. retrofit 인스턴스로 부터 런타임 시 HTTP API Java 인터페이스 구현체 생성
        OpenApiService openApiService = retrofit.create(OpenApiService.class);

        // 3. HTTP API Java 인터페이스 구현체의 메소드로 웹 서버에 요청 및 응답
        Call<Data> result = openApiService.get("중구", 1, 1000);

        // 4. Call 인스턴스의 비동기 콜백으로 JSON String 추출
        // Android 에서는 비동기 콜백이 주 스레드에서 실행.
        // 생성 된 interface 구현체의 각 Call 인스턴스는 원격 웹 서버에 대한 동기식 또는 비동기식 HTTP 요청을 만들 수 있다.
        result.enqueue(new Callback<Data>() {
            // 데이터 응답 성공 시 호출되는 콜백 메소드
            @Override
            public void onResponse(Call<Data> call, Response<Data> response) {
                // 응답 메시지가 성공인 경우
                if(response.isSuccessful()) {
                    Log.i("Retrofit", "Response is Successful");

                    // 5. 응답 객체의 JSON String 으로 부터 Java 객체 반환
                    // 이 때, JSON String 을 Java 객체로 변환하는 라이브러리인 GSON Converter 가 필요하다.
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
                    // 응답 메시지가 Error 인 경우
                    Log.e("Retrofit", response.message());
                }
            }

            // 데이터 응답 실패 시 호출되는 콜백 메소드
            @Override
            public void onFailure(Call<Data> call, Throwable t) {

            }
        });
    }
}
