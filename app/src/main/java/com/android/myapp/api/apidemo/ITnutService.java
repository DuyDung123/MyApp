package com.android.myapp.api.apidemo;

import com.android.myapp.api.requesthelper.RetrofitClient;
import com.android.myapp.utils.Constants;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ITnutService {

    ITnutService SERVICE = RetrofitClient.getInstance().getService(Constants.BASE_URL_TNUT, ITnutService.class);

    @GET("/api.aspx")
    Call<ResponseBody> getInfo(@Query("action") String action);
}
