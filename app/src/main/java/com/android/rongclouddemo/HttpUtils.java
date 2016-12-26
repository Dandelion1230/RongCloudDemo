package com.android.rongclouddemo;

import android.util.Log;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Administrator on 2016/12/9.
 */

public class HttpUtils {
    private String url = "http://api.cn.ronghub.com/user/getToken.json";
    private String RC_App_Key = "ik1qhw09ifjyp";
    private String RC_Nonce = "10001";
    private String App_Secret = "yoIUUbj3XrAaS";
    private String userId = "123456";
    private String name = "123456";
    private String portraitUri = "http://v1.qzone.cc/avatar/201503/15/13/08/550513b64bcbf041.jpg!200x200.jpg";
    private OkHttpClient client = new OkHttpClient();

    public void getToken() {
        FormBody.Builder builder = new FormBody.Builder()
                .add("userId", userId)
                .add("name", name)
                .add("portraitUri", portraitUri);
        Request request = new Request.Builder()
                .url(url)
                .addHeader("RC-App-Key", RC_App_Key)
                .addHeader("RC-Nonce", RC_Nonce)
                .addHeader("RC-Timestamp", getTime())
                .addHeader("RC-Signature", sha1(App_Secret+RC_Nonce+getTime()))
                .post(builder.build())
                .build();
        client.newCall(request).enqueue(new Callback(){

            @Override
            public void onFailure(Call call, IOException e) {
                String error = e.getMessage();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response.isSuccessful()) {
                    String token = response.body().string();
                    Log.e("TAG", token);
                }
            }
        });

    }



    public String sha1(String data) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA1");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(data.getBytes());
        StringBuffer buf = new StringBuffer();
        byte[] bits = md.digest();
        for(int i=0;i<bits.length;i++){
            int a = bits[i];
            if(a<0) a+=256;
            if(a<16) buf.append("0");
            buf.append(Integer.toHexString(a));
        }
        return buf.toString();
    }

    private String getTime() {
        long time = System.currentTimeMillis();
        return time+"";
    }

}
