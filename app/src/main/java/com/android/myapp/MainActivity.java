package com.android.myapp;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.android.myapp.api.apidemo.ITnutService;
import com.android.myapp.api.requesthelper.RequestHelper;
import com.android.myapp.api.requesthelper.ResponseAPI;
import okhttp3.ResponseBody;
import retrofit2.Call;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button btnClick;
    private TextView txtView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnClick = findViewById(R.id.btnClick);
        txtView = findViewById(R.id.txtView);
        btnClick.setOnClickListener(this);
    }

    private void click(){
        final String[] text = {""};
        Call<ResponseBody> bodyCall = ITnutService.SERVICE.getInfo("counter_online");
        RequestHelper.executeASyncRequest(bodyCall, new ResponseAPI<ResponseBody>() {
            @Override
            public void onSuccess(ResponseBody response, int code) {
                Log.e("", "alow");
                try {
                    text[0] = response.string();
                    txtView.setText("code: "+ code +" content: " + text[0]);
                } catch (Exception e) {
                    text[0] = e.getMessage();
                    txtView.setText("code: "+ code +" content: " + text[0]);
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int code, String message) {
                text[0] = "code " + code + " - mess:" + message;
                Log.e("", "alo");
                txtView.setText(text[0]);
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.btnClick:
                click();
                break;
        }
    }
}