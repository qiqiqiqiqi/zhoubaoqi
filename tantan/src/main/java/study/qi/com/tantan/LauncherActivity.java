package study.qi.com.tantan;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


import study.qi.com.tantan.checkAdInfoUpdate.CheckUpdateTask;
import study.qi.com.tantan.view.CustomVideoView;

public class LauncherActivity extends Activity implements View.OnClickListener, CheckUpdateTask.OnCheckUpdateListener {
    private static final String TAG = LauncherActivity.class.getSimpleName();
    private static final int WHAT_COUNT_DOWN = 0;
    private static final int WHAT_ERROR = 1;
    private static String IMG_PATH = null;
    private static final String FILENAME = "tantandemo";
    private CustomVideoView videoview;
    private TextView btn_start;
    private int mDuration;

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int what = msg.what;
            switch (what) {
                case WHAT_COUNT_DOWN:
                    Integer second = (Integer) msg.obj;
                    Log.d(TAG, "second=" + second);
                    if (second == 0) {
                        btn_start.setText("跳过" + second + "s");
                        return;
                    } else {
                        btn_start.setText("跳过" + second + "s");
                        LauncherActivity.this.sendMessage(--mDuration, 1000);
                    }
                    break;
                case WHAT_ERROR:
                    toMain();
                    break;
            }

        }
    };
    private CheckUpdateTask mCheckUpdateTask;
    private ImageView mAdPic;
    private RelativeLayout mAdVideo;
    private Bitmap bitmapToShow;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launcher);
        initView();
        init();
    }

    private void init() {
        IMG_PATH = Environment.getExternalStorageDirectory()
                .getAbsolutePath() + File.separator + FILENAME;
        mCheckUpdateTask = new CheckUpdateTask();
        mCheckUpdateTask.setOnCheckUpdateListener(this);
        mCheckUpdateTask.checkADInfo("http://192.168.1.117:8080/smarthotel/smarthotel.json");
    }

    /**
     * 初始化
     */
    private void initView() {
        btn_start = (TextView) findViewById(R.id.btn_start);
        btn_start.setOnClickListener(this);
        videoview = (CustomVideoView) findViewById(R.id.videoview);
        mAdPic = (ImageView) findViewById(R.id.ad_pic);
        mAdVideo = (RelativeLayout) findViewById(R.id.ad_video);
        int random = (int) (Math.random() * 10);
        if (random % 2 == 0 && isExistsLocalSplashImageData()) {
            mAdVideo.setVisibility(View.GONE);
            mAdPic.setVisibility(View.VISIBLE);
            bitmapToShow = BitmapFactory.decodeFile(IMG_PATH + "/splash_img.jpg");
            mAdPic.setImageBitmap(bitmapToShow);
            mHandler.sendEmptyMessageDelayed(WHAT_ERROR, 1000);
        } else if (random % 2 == 1 && isExistsLocalSplashVideoData()) {
            mAdVideo.setVisibility(View.VISIBLE);
            mAdPic.setVisibility(View.GONE);
            //设置播放加载路径
            //videoview.setVideoURI(Uri.parse("android.resource://" + getPackageName() + "/" + R.raw.media));
            videoview.setVideoPath(IMG_PATH + File.separator + "splash_video.mp4");
            videoview.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
                @Override
                public void onPrepared(MediaPlayer mediaPlayer) {
                    videoview.start();
                    mDuration = mediaPlayer.getDuration() / 1000;
                    Log.d(TAG, "maxProgress=" + mDuration);
                    sendMessage(mDuration, 0);

                }
            });
            videoview.setOnErrorListener(new MediaPlayer.OnErrorListener() {
                @Override
                public boolean onError(MediaPlayer mediaPlayer, int i, int i1) {
                    Log.d(TAG, "onError():");
                    videoview.stopPlayback();
                    mAdVideo.setVisibility(View.GONE);
                    mAdPic.setVisibility(View.VISIBLE);
                    bitmapToShow = BitmapFactory.decodeResource(getResources(), R.drawable.img_avatar_01);
                    mAdPic.setImageBitmap(bitmapToShow);
                    mHandler.sendEmptyMessageDelayed(WHAT_ERROR, 1000);
                    return true;
                }
            });
            videoview.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
                @Override
                public void onCompletion(MediaPlayer mediaPlayer) {
                    toMain();
                }
            });
        } else {
            mAdVideo.setVisibility(View.GONE);
            mAdPic.setVisibility(View.VISIBLE);
            bitmapToShow = BitmapFactory.decodeResource(getResources(), R.drawable.img_avatar_01);
            mAdPic.setImageBitmap(bitmapToShow);
            mHandler.sendEmptyMessageDelayed(WHAT_ERROR, 1000);
        }
        Log.d(TAG, "initView():isExistsLocalSplashImageData=" + isExistsLocalSplashImageData() + ",isExistsLocalSplashVideoData=" + isExistsLocalSplashVideoData());


    }

    private void toMain() {
        Intent toMainIntent = new Intent(LauncherActivity.this, MainActivity.class);
        startActivity(toMainIntent);
        finish();
    }

    private void sendMessage(int second, long delay) {
        Message message = mHandler.obtainMessage();
        message.obj = second;
        message.what = WHAT_COUNT_DOWN;
        mHandler.sendMessageDelayed(message, delay);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_start:
                Toast.makeText(this, "进入了主页", Toast.LENGTH_SHORT).show();
                break;
        }
    }


    private static void getAndSaveNetWorkBitmap(final String urlString) {
        Runnable getAndSaveImageRunnable = new Runnable() {
            @Override
            public void run() {
                URL imgUrl = null;
                Bitmap bitmap = null;
                try {
                    imgUrl = new URL(urlString);
                    HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.connect();
                    InputStream is = urlConn.getInputStream();
                    bitmap = BitmapFactory.decodeStream(is);
                    is.close();
                    saveBitmapFile(bitmap, IMG_PATH);
                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(getAndSaveImageRunnable).start();
    }

    private static void getAndSaveNetWorkVideo(final String urlString) {
        Runnable getAndSaveVideoRunnable = new Runnable() {
            @Override
            public void run() {
                URL imgUrl = null;
                try {
                    imgUrl = new URL(urlString);
                    HttpURLConnection urlConn = (HttpURLConnection) imgUrl.openConnection();
                    urlConn.setDoInput(true);
                    urlConn.connect();
                    InputStream is = urlConn.getInputStream();
                    BufferedInputStream bufferedInputStream = new BufferedInputStream(is);
                    saveVideoFile(bufferedInputStream, IMG_PATH);

                } catch (MalformedURLException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        };
        new Thread(getAndSaveVideoRunnable).start();
    }

    private static void saveBitmapFile(Bitmap bm, String filePath) throws IOException {
        File myCaptureFile = new File(filePath);
        if (!myCaptureFile.exists()) {
            myCaptureFile.mkdirs();
        }
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(myCaptureFile, "splash_img.jpg")));
        bm.compress(Bitmap.CompressFormat.JPEG, 80, bos);
        bos.flush();
        bos.close();
    }

    private static void saveVideoFile(BufferedInputStream bis, String filePath) throws IOException {
        File myCaptureFile = new File(filePath);
        if (!myCaptureFile.exists()) {
            myCaptureFile.mkdirs();
        }
        byte[] bytes = new byte[1024 * 7];
        BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(new File(myCaptureFile, "splash_video.mp4")));
        try {
            int len = 0;
            while ((len = bis.read(bytes)) != -1) {
                bos.write(bytes, 0, len);
            }
        } catch (Exception e) {
            e.printStackTrace();
            bis.close();
            bos.close();
        }
        bis.close();
        bos.close();
    }

    private static boolean isExistsLocalSplashImageData() {
        return isFileExist(IMG_PATH, "splash_img.jpg");
    }

    private static boolean isExistsLocalSplashVideoData() {
        return isFileExist(IMG_PATH, "splash_video.mp4");
    }

    public static boolean isFileExist(String filePath, String filename) {
        if (TextUtils.isEmpty(filePath)) {
            return false;
        } else {
            File file = new File(filePath, filename);
            return file.exists() && file.isFile();
        }
    }

    @Override
    protected void onDestroy() {
        if (videoview != null) {
            videoview.destroyDrawingCache();
        }
        super.onDestroy();
        if (mHandler != null) {
            mHandler.removeCallbacksAndMessages(null);
            mHandler = null;
        }
        bitmapToShow = null;
    }

    @Override
    public void onCheckUpdate(ADInfo adInfo) {
        Log.d(TAG, "onCheckUpdate():adInfo=" + adInfo);
        getAndSaveNetWorkBitmap(adInfo.getPicUrl());
        getAndSaveNetWorkVideo(adInfo.getVideoUrl());
    }

}
