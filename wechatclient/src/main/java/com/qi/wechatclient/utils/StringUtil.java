package com.qi.wechatclient.utils;

import android.content.Context;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by 13324 on 2016/11/10.
 */
public class StringUtil {

    //判断手机格式是否正确
    public static boolean isPhone(String phone) {
        if (TextUtils.isEmpty(phone)) {
            return false;
        }
        if (phone.startsWith("+") && phone.length() > 3) {
            phone = phone.substring(3, phone.length()).trim();
        }
        Pattern p = Pattern.compile("^((13[0-9])|(14[0-9])|(15[0-9])|(17[0-9])|(18[0-9]))\\d{8}$");
        Matcher m = p.matcher(phone);
        return m.matches();
    }

    //判断email格式是否正确
    public static boolean isEmail(String email) {
        if (TextUtils.isEmpty(email)) {
            return false;
        }
//        String str = "^([a-z0-9A-Z]+[-|_|\\.]?)+@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";//太复杂，匹配耗时很长，会导致明显卡顿
//        String str = "([a-z0-9A-Z]+[-|_|\\.]?)+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";//不支持xxx.xx@xx.xx
//        String str = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
//        Pattern p = Pattern.compile(str);
//        Matcher m = p.matcher(email);
//        return m.matches();
        String regex = "\\w+([-+.]\\w+)*@\\w+([-.]\\w+)*\\.\\w+([-.]\\w+)*";
        return email.matches(regex);
    }

    public static boolean isEmpty(String string) {
        if (string == null || TextUtils.isEmpty(string)) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是数字
     *
     * @param text
     * @return
     */
    public static boolean isNumber(String text) {
        Pattern p = Pattern.compile("[0-9]*");
        Matcher m = p.matcher(text);
        if (m.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 设置字体的颜色
     *
     * @param mContext
     * @param textView
     * @param start
     * @param colorId
     */
    public static  void setTextColor(Context mContext, TextView textView, int start, int colorId) {
        CharSequence text = textView.getText();
        Spannable WordtoSpan = new SpannableString(text);
//        //设置字体大小
//        WordtoSpan.setSpan(new AbsoluteSizeSpan(normalSize), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        WordtoSpan.setSpan(new AbsoluteSizeSpan(bigSize), start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体颜色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(colorId));
        WordtoSpan.setSpan(redSpan, start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(WordtoSpan);

    }
    /**
     * 设置字体的颜色
     *
     * @param mContext
     * @param textView
     * @param start
     * @param end
     * @param colorId
     */
    public static  void setTextColor(Context mContext, TextView textView, int start,int end ,int colorId) {
        CharSequence text = textView.getText();
        Spannable WordtoSpan = new SpannableString(text);
//        //设置字体大小
//        WordtoSpan.setSpan(new AbsoluteSizeSpan(normalSize), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        WordtoSpan.setSpan(new AbsoluteSizeSpan(bigSize), start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        //设置字体颜色
        ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(colorId));
        WordtoSpan.setSpan(redSpan, start, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(WordtoSpan);

    }
    /**
     * 设置字体的大小
     * @param mContext
     * @param textView
     * @param normalSize
     * @param bigSize
     * @param start
     * @param end
     */
    public static  void setTextSize(Context mContext, TextView textView, int normalSize, int bigSize, int start,int end) {
        CharSequence text = textView.getText();
        Spannable WordtoSpan = new SpannableString(text);
        //设置字体大小
        WordtoSpan.setSpan(new AbsoluteSizeSpan(normalSize), 0, end, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        WordtoSpan.setSpan(new AbsoluteSizeSpan(bigSize), start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
//        //设置字体颜色
//        ForegroundColorSpan redSpan = new ForegroundColorSpan(mContext.getResources().getColor(colorId));
//        WordtoSpan.setSpan(redSpan, start, text.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        textView.setText(WordtoSpan);

    }
}
