package com.qi.wechatclient.utils;

import android.content.Context;
import android.os.Handler;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.qi.wechatclient.R;
import com.qi.wechatclient.common.WechatApplication;
import com.qi.wechatclient.config.Constants;


/**
 * @created by baoqi 2016/11/10.
 */
public class ToastUtil {
    private static final String TAG = "ToastUtil";
    private static Toast toast;
    /**
     * 字体大小
     */
    private final static int FONT_SIZE = 16;

    private final static Context context = WechatApplication.getWechatApplication();

    public static void showToast(String text) {
        showToast(text, Constants.ToastType.NULL, Constants.ToastType.SHORT);
    }

    public static void showToast(int textId) {
        showToast(textId, Constants.ToastType.NULL, Constants.ToastType.SHORT);
    }

    public static void showToast(int textId, int LONG) {
        showToast(textId, Constants.ToastType.NULL, LONG);
    }

//    public static void showCenterToast(String text) {
//        showCenterToast(text, Constants.ToastType.RIGHT, Constants.ToastType.SHORT);
//    }
//
//    public static void showCenterToast(int textId) {
//        showCenterToast(textId, Constants.ToastType.RIGHT, Constants.ToastType.SHORT);
//    }
//
//    public static void showCenterToast(int textId, int LONG) {
//        showCenterToast(textId, Constants.ToastType.NULL, LONG);
//    }

    /**
     * @param textId
     * @param LONG   0默认2s，1持续时间3.5s
     */
   /* public static void showCenterToast(int textId, int imgType,
                                       int LONG) {
        String text = context.getResources().getString(textId);
        showCenterToast(text, imgType, LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }*/

    /**
     * @param LONG 0默认2s，1持续时间3.5s
     */
  /*  public static void showCenterToast(String text, int imgType,
                                       int LONG) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = new Toast(context);
        }
        //  toast.setGravity(Gravity.BOTTOM, 0, 0);
        showCenter(text, imgType, LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }*/

    /**
     * @param textId
     * @param LONG   0默认2s，1持续时间3.5s
     */
    public static void showToast(int textId, int imgType,
                                 int LONG) {
        String text = context.getResources().getString(textId);
        showToast(text, imgType, LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    /**
     * @param LONG 0默认2s，1持续时间3.5s
     */
    public static void showToast(String text, int imgType,
                                 int LONG) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = new Toast(context);
        }
        //  toast.setGravity(Gravity.BOTTOM, 0, 0);
        show(text, imgType, LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
    }

    private static void show(String text, int imgType, int LONG) {
        //    LogUtil.d(TAG, "show()-toast " + text);
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.customtoast, null);
        ImageView image = (ImageView) layout.findViewById(R.id.warningToast_iv);
        if (imgType == Constants.ToastType.RIGHT) {
            image.setImageResource(R.drawable.icon_right_normal);
        } else if (imgType == Constants.ToastType.ERROR) {
            image.setVisibility(View.GONE);
        } else if (imgType == Constants.ToastType.WAIT) {

        } else if (imgType == Constants.ToastType.NULL) {
            image.setVisibility(View.GONE);
        }
        TextView tView = (TextView) layout.findViewById(R.id.textToast_tv);
        tView.setText(text);
        tView.setTextSize(FONT_SIZE);
        toast.setGravity(Gravity.BOTTOM, 0,context.getResources().getDimensionPixelOffset(R.dimen.margin_x10) );
        toast.setDuration(LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

   /* private static void showCenter(String text, int imgType, int LONG) {
        //    LogUtil.d(TAG, "show()-toast " + text);
        LayoutInflater inflater = LayoutInflater.from(context);
        View layout = inflater.inflate(R.layout.customcentertoast, null);
        ImageView image = (ImageView) layout.findViewById(R.id.warningToast_iv);
        if (imgType == Constants.ToastType.RIGHT) {
            image.setImageResource(R.drawable.task_list_icon_succeed);
        } else if (imgType == Constants.ToastType.ERROR) {
            image.setVisibility(View.GONE);
        } else if (imgType == Constants.ToastType.WAIT) {

        } else if (imgType == Constants.ToastType.NULL) {
            image.setVisibility(View.GONE);
        }
        TextView tView = (TextView) layout.findViewById(R.id.textToast_tv);
        tView.setText(text);
        tView.setTextSize(FONT_SIZE);
        toast.setDuration(LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }*/

    /**
     * @param textId
     * @param imgType
     * @param LONG    0默认2s，1持续时间3.5s
     */
    public static void showToast(Handler mHandler,
                                 int textId, final int imgType, final int LONG) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = new Toast(context);
        }
        final String text = context.getResources().getString(textId);
        if (mHandler != null) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    showToast(text, imgType, LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
                }
            });
        }
    }

    /**
     * 在子綫程中調用
     *
     * @param text
     * @param imgType
     * @param LONG    0默认2s，1持续时间3.5s
     */
    public static void showToast(Handler mHandler,
                                 final String text, final int imgType, final int LONG) {
        if (context == null) {
            return;
        }
        if (toast == null) {
            toast = new Toast(context);
        }
        if (mHandler != null) {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    showToast(text, imgType, LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
                }
            });
        }
    }

    private static void showToast(TextView textView, int LONG) {
        if (toast == null) {
            toast = new Toast(context);
        }
        toast.setView(textView);
        toast.setDuration(LONG == 0 ? Toast.LENGTH_SHORT : Toast.LENGTH_LONG);
        toast.show();
    }


    /**
     * 消失提示
     */
    public static void dismissToast() {
        if (toast != null) {
            toast.cancel();
        }
    }

    /**
     * 在UI线程中消失
     *
     * @param mHandler
     */
    public static void dismiss(Handler mHandler) {
        if (mHandler != null) {
            mHandler.post(new Runnable() {

                @Override
                public void run() {
                    if (toast != null) {
                        toast.cancel();
                    }
                }
            });
        }
    }
}
