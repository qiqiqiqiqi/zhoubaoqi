package study.qi.com.tantan.checkAdInfoUpdate;

import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Message;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.SocketTimeoutException;
import java.net.URL;

import study.qi.com.tantan.ADInfo;


/**
 * Created by feng on 2017/4/25.
 */

public class CheckUpdateTask extends AsyncTask<String, Void, ADInfo> {
    private static final String TAG = CheckUpdateTask.class.getSimpleName();
    private OnCheckUpdateListener mOnCheckUpdateListener;

    public CheckUpdateTask() {
    }

    @Override
    protected ADInfo doInBackground(String... strings) {

        ADInfo adInfo = new ADInfo();
        try {
            InputStream inputStream = requestInputStream(strings[0]);
            if (inputStream != null) {
                BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));
                String line = null;
                StringBuilder result = new StringBuilder();
                while ((line = br.readLine()) != null) {
                    result.append(line);
                }
                Log.d(TAG, "result =" + result);
                JSONObject jsonObject = new JSONObject(result.toString());
                String picUrl = jsonObject.getString("picUrl");
                String videoUrl = jsonObject.getString("videoUrl");
                boolean video = Boolean.parseBoolean(jsonObject.getString("video"));
                int date = Integer.parseInt(jsonObject.getString("date"));
                adInfo.setPicUrl(picUrl);
                adInfo.setVideo(video);
                adInfo.setVideoUrl(videoUrl);
                adInfo.setDate(date);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return adInfo;
    }

    @Override
    protected void onPostExecute(ADInfo adInfo) {
        super.onPostExecute(adInfo);
        Log.d(TAG, "onPostExecute():adInfo=" + adInfo);
        if (mOnCheckUpdateListener != null) {
            mOnCheckUpdateListener.onCheckUpdate(adInfo);
        }
    }

    private InputStream requestInputStream(String url) {
        InputStream inputStream = null;
        try {
            URL netUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) netUrl.openConnection();
            connection.setReadTimeout(10 * 1000);
            connection.setConnectTimeout(10 * 1000);
            connection.connect();
            int responseCode = connection.getResponseCode();
            if (responseCode == 200) {
                inputStream = connection.getInputStream();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return inputStream;
    }

    public void checkADInfo(String url) {
        this.execute(url);
    }

    public interface OnCheckUpdateListener {
        void onCheckUpdate(ADInfo adInfo);
    }

    public void setOnCheckUpdateListener(OnCheckUpdateListener onCheckUpdateListener) {
        mOnCheckUpdateListener = onCheckUpdateListener;
    }


}
