package com.qi.wechatclient.common;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Matrix;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.qi.wechatclient.R;
import com.qi.wechatclient.bo.Account;
import com.qi.wechatclient.cache.LoginCache;
import com.qi.wechatclient.cache.UserCache;
import com.qi.wechatclient.data.ErrorCode;
import com.qi.wechatclient.model.request.LoginRequest;
import com.qi.wechatclient.model.request.RegisterRequest;
import com.qi.wechatclient.service.readTableService.ReadTableService;
import com.qi.wechatclient.service.readTableService.ReadTableServiceConnection;
import com.qi.wechatclient.utils.DisplayUtils;
import com.qi.wechatclient.utils.LogUtil;
import com.qi.wechatclient.utils.ToastUtil;
import com.qi.wechatclient.view.DialogFragmentProgresss;
import com.qi.wechatclient.view.EditTextWithCompound;




public class LoginActivity extends Activity implements View.OnClickListener {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private Button mSendButton;
    private RegisterRequest mRegisterRequest;
    private ImageView mBgLoginImageView;
    private TranslateX mTranslateX;
    private int mTranslateRange;
    private int mIntrinsicWidth;
    private int mIntrinsicHeight;
    private int mScreenHeight;
    private int mScreenWidth;
    private LoginRequest mLoginRequest;
    private EditTextWithCompound mAccountEditText;
    private EditTextWithCompound mPasswordEditText;
    private ImageView mArrowImageView;
    private DialogFragmentProgresss mDialogFragmentProgresss;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initData();
    }

    private void initData() {
        mRegisterRequest = new RegisterRequest() {
            @Override
            public void onRegisterRequestResult(int result, int messageType, Account account) {
                LogUtil.d(TAG, "onReadTableRequestResult():result=" + result + ",account=" + account);
                if (result == ErrorCode.SUCCESS) {
                    Toast.makeText(LoginActivity.this, "注册成功", Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(LoginActivity.this, "注册失败", Toast.LENGTH_LONG).show();
                }

            }
        };

        mLoginRequest = new LoginRequest() {
            @Override
            public void onLoginRequestResult(int result, int messageType) {
                if (result == ErrorCode.SUCCESS) {
                    LoginCache.setLoginCache(true);
                    Toast.makeText(LoginActivity.this, "登录成功", Toast.LENGTH_LONG).show();
                    readTable();
                    enterMain();


                } else {
                    Toast.makeText(LoginActivity.this, "登录失败", Toast.LENGTH_LONG).show();

                }

            }
        };

    }

    public void readTable() {
        Intent intent = new Intent(this, ReadTableService.class);
        ReadTableServiceConnection readTableServiceConnection = new ReadTableServiceConnection();
        bindService(intent, readTableServiceConnection, BIND_AUTO_CREATE);
    }


    private void enterMain() {
        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }

    private void initView() {
        mSendButton = (Button) findViewById(R.id.login);
        mBgLoginImageView = (ImageView) findViewById(R.id.bg_login);
        mAccountEditText = (EditTextWithCompound) findViewById(R.id.login_account);
        mPasswordEditText = (EditTextWithCompound) findViewById(R.id.login_password);
        mArrowImageView = (ImageView) findViewById(R.id.arrow);
        Drawable drawable = mBgLoginImageView.getDrawable();
        mIntrinsicWidth = drawable.getIntrinsicWidth();
        mIntrinsicHeight = drawable.getIntrinsicHeight();
        mScreenHeight = DisplayUtils.getScreenHeight(this);
        mScreenWidth = DisplayUtils.getScreenWidth(this);
        mTranslateRange = mScreenWidth / 2;
        LogUtil.d(TAG, "initView():intrinsicWidth=" + mIntrinsicWidth + ",intrinsicHeight=" + mIntrinsicHeight + ",screenWidth=" + mScreenWidth + ",screenHeight=" + mScreenHeight);
        mSendButton.setOnClickListener(this);

        mTranslateX = new TranslateX();
        mTranslateX.setDuration(10000);
        mTranslateX.setRepeatMode(Animation.REVERSE);
        mTranslateX.setRepeatCount(Animation.INFINITE);
        mBgLoginImageView.startAnimation(mTranslateX);
        initLoginLoadingDialog();

        mAccountEditText.setText("zhoubaoqi1615878");
        mPasswordEditText.setText("123456");
    }

    private void initLoginLoadingDialog() {

        mDialogFragmentProgresss = new DialogFragmentProgresss();
    }

    @Override
    public void onClick(View view) {

        String accountName = mAccountEditText.getText().toString().trim();
        String accountPassword = mPasswordEditText.getText().toString().trim();
        if (TextUtils.isEmpty(accountName) || TextUtils.isEmpty(accountPassword)) {
            ToastUtil.showToast(R.string.login_account_password_empty_tips);
            return;
        } else {
            mDialogFragmentProgresss.show(getFragmentManager(), null);
            mLoginRequest.startLoginRequest(accountName, accountPassword);
        }
    }



    class TranslateX extends Animation {

        float pre = 0.0f;
        int mWidth;
        int mHeight;

        @Override
        public void initialize(int width, int height, int parentWidth, int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
            mWidth = width;
            mHeight = height;
            LogUtil.d("TranslateX", "initialize():width=" + width + ",height=" + height);
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {
            //   LogUtil.d(TAG, "applyTransformation():interpolatedTime=" + interpolatedTime + ",pre=" + pre);
            Matrix matrix = t.getMatrix();
            matrix.setTranslate(0, 0);
            matrix.postScale(1.5f, (mScreenHeight + 0.0f) / mIntrinsicHeight);
            matrix.postTranslate(-interpolatedTime * mTranslateRange, 0);
        }

    }


}
