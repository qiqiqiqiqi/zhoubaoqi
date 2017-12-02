package com.qi.wechatclient.view;

import android.annotation.SuppressLint;
import android.content.ClipboardManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.Editable;
import android.text.InputFilter;
import android.text.InputType;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.ContextMenu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;


import com.qi.wechatclient.R;
import com.qi.wechatclient.common.WechatApplication;
import com.qi.wechatclient.utils.LogUtil;
import com.qi.wechatclient.utils.StringUtil;

import java.nio.charset.Charset;

/**
 * Created by 13324 on 2016/11/9.
 */
@SuppressLint("AppCompatCustomView")
public class EditTextWithCompound extends EditText implements TextWatcher, View.OnFocusChangeListener, MenuItem.OnMenuItemClickListener {
    private String TAG = EditTextWithCompound.class.getSimpleName();
    protected OnInputListener onInputListener;
    private Drawable leftDrawable;
    /**
     * the right drawable of EditText, default drawable is R.drawable.edit_text_delete_icon on resource.
     */
    private Drawable rightDrawable;

    private Drawable rightfulBackgroundDrawable;
    private Drawable unlawfulBackgroundDrawable;
    private int paddingLeft, paddingRight;

    /**
     * define type of this EditText One of {@link #TYPE_NORMAL}, {@link #TYPE_PHONE}, {@link #TYPE_EMAIL}, or {@link #TYPE_PHONE_EMAIL}.
     */
    private int type = TYPE_NORMAL;
    public static final int TYPE_NORMAL = 0;//默認的輸入類型
    public static final int TYPE_PHONE = 1;//手機號碼
    public static final int TYPE_EMAIL = 2;//email
    public static final int TYPE_PHONE_EMAIL = 3;//手機和email

    private int minLength = 1;
    private int maxLength = Integer.MAX_VALUE;

    private boolean isIntact = false;
    private boolean pwdEyeEnable = false;
    private boolean autoChange = false;
    private CharSequence charSequence;
    private boolean needFilter = true;//是否需要过滤特殊字符,默认需要过滤
    private boolean needRestrict = true;//是否需要限制特殊,默认需要限制

    public static final int MAX_TEXT_LENGTH = 32;
    public static final int MAX_TEXT_LENGTH_NORMAL = 16;//原有的一些名称长度为16的

    public EditTextWithCompound(Context context) {
        super(context);
        init();
    }

    public EditTextWithCompound(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public EditTextWithCompound(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        leftDrawable = getCompoundDrawables()[0];
        rightDrawable = getCompoundDrawables()[2];
        rightfulBackgroundDrawable = getResources().getDrawable(R.drawable.bg_bottom_line);
        unlawfulBackgroundDrawable = getResources().getDrawable(R.drawable.bg_bottom_line);
        if (rightDrawable == null) {
            rightDrawable = getResources().getDrawable(R.drawable.login_delete);
        }
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();
        setDrawable();
        addTextChangedListener(this);
        setOnFocusChangeListener(this);
        setInputType(InputType.TYPE_TEXT_FLAG_NO_SUGGESTIONS);
    }

    @Override
    public void setSelection(int index) {
        try {
            String content = getText().toString();
            if (index >= content.length()) {
                index = content.length();
            }
            super.setSelection(index);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onFocusChange(View v, boolean hasFocus) {
        setDrawable();
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        LogUtil.d(TAG, "beforeTextChanged()-s:" + s + " start:" + start + " count:" + count + " after:" + after);

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        LogUtil.d(TAG, "onTextChanged()-s:" + s + " start:" + start + " count:" + count + " before:" + before);
        if (isIntact) {
            charSequence = s.subSequence(start, start + count);
//            LogUtil.d(TAG, "onTextChanged()-charSequence:" + charSequence);
        }

    }

    @Override
    public void afterTextChanged(Editable editable) {
        LogUtil.d(TAG, "afterTextChanged() isIntact:" + isIntact + "autoChange:" + autoChange);
        if (isIntact) {
            isIntact = false;
            editable.replace(0, editable.toString().length(), charSequence);
//            setText(charSequence);
            try {
                setSelection(length());
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            if (autoChange) { // skip execution if triggered by code
                autoChange = false; // next change is not triggered by code
                setDrawable();
                if (isRightful()) {
                    onRightful();
                } else {
                    onUnlawful();
                }
                return;
            }
            int startSelection = getSelectionStart();
            String strOld = editable.toString();
            String str = editable.toString();
            int byteLength = str.getBytes(Charset.forName("GBK")).length;
            while (byteLength > maxLength) {
                int currentLength = str.length();
                str = str.substring(0, currentLength - 1);
                byteLength = str.getBytes(Charset.forName("GBK")).length;
            }
            if (!strOld.equals(str)) {
                if (editable.toString().length() != 0 && str.length() != 0) {
                    autoChange = true;
                }
                editable.replace(0, editable.toString().length(), str);
            }
            try {
                setSelection(Math.min(str.length(), startSelection));
            } catch (Exception e) {
                e.printStackTrace();
            }
            setRightful(rightfulBackgroundDrawable);
            if (isRightful()) {
                onRightful();
            } else {
                onUnlawful();
            }

            if (needRestrict && !StringUtil.isEmpty(str)) {
                //  ToastUtil.showToast(R.string.SPECIAL_CHAR_ERROR);
            }
        }
    }

    public void isNeedFilter(boolean isNeedFilter) {//是否需要过滤特殊字符
        needFilter = isNeedFilter;
    }

    /**
     * 是否限制特殊字符，默认限制
     * true 限制；false不限制
     *
     * @param needRestrict
     */
    public void setNeedRestrict(boolean needRestrict) {
        this.needRestrict = needRestrict;
    }

    /**
     * 判斷輸入的長度是否滿足規定
     *
     * @return
     */
    public boolean isRightful() {
        switch (type) {
            case TYPE_NORMAL:
                return length() >= minLength && length() <= maxLength;
            case TYPE_PHONE:
                return StringUtil.isPhone(getText().toString()) && length() >= minLength && length() <= maxLength;
            case TYPE_EMAIL:
                return StringUtil.isEmail(getText().toString()) && length() >= minLength && length() <= maxLength;
            case TYPE_PHONE_EMAIL:
                return (StringUtil.isPhone(getText().toString()) || StringUtil.isEmail(getText().toString())) && length() >= minLength && length() <= maxLength;
        }
        return false;
    }


    private void onRightful() {
        if (onInputListener != null) {
            onInputListener.onRightful();
        }
    }

    private void onUnlawful() {
        if (onInputListener != null) {
            onInputListener.onUnlawful();
        }
    }

    /**
     * 設置輸入框的背景，可以自定義，否則使用默認的背景
     *
     * @param drawable
     */
    public void setRightful(Drawable drawable) {
//        LogUtil.d(TAG,"setRightful()");
//        if (drawable != null)
        rightfulBackgroundDrawable = drawable;
        setBackgroundDrawable(rightfulBackgroundDrawable);
        setDrawable();
    }

    public void setUnlawful() {
        setBackgroundDrawable(unlawfulBackgroundDrawable);
        setDrawable();
    }

    public void hideDeleteDrawable() {
        rightDrawable = null;
        setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null,
                rightDrawable, null);
    }

    public void showDeleteDrawable() {
        rightDrawable = WechatApplication.getWechatApplication().getResources().getDrawable(
                R.drawable.login_delete);
        setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null,
                rightDrawable, null);
    }

    public void setRightfulBackgroundDrawable(Drawable rightfulBackgroundDrawable) {
        this.rightfulBackgroundDrawable = rightfulBackgroundDrawable;
        setDrawable();
    }

    public void setDrawable() {
//        LogUtil.d(TAG,"setDrawable()- length():" + length() + " isFocused:" + isFocused());
        if (length() == 0 || !isFocused()) {
            super.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, null, null);
        } else {
            super.setCompoundDrawablesWithIntrinsicBounds(leftDrawable, null, rightDrawable, null);
        }
        setPadding(paddingLeft, 0, paddingRight, 0);
    }


    public void setPaddingLeft(int paddingLeft) {
        this.paddingLeft = paddingLeft;
        setDrawable();
    }

    public void setPaddingRight(int paddingRight) {
        this.paddingRight = paddingRight;
        setDrawable();
    }

    public void setMinLength(int minLength) {
        this.minLength = minLength;
    }

    public void setMaxLength(int maxLength) {
        this.maxLength = maxLength;
        setFilters(new InputFilter[]{new InputFilter.LengthFilter(maxLength)});
    }

    public void setIntactText(String intactText) {
        isIntact = true;
        removeTextChangedListener(this);//因为监听是异步的线程，所以把监听去掉
        setText(intactText);
        try {
            setSelection(length());
        } catch (Exception e) {
            e.printStackTrace();
        }
        setDrawable();
        addTextChangedListener(this);//设置好文字后，再加上监听
    }

    @Override
    public boolean onTextContextMenuItem(int id) {
        if (id == android.R.id.paste) {//粘貼的時候
            if (type == TYPE_PHONE || type == TYPE_PHONE_EMAIL) {
                ClipboardManager clip = (ClipboardManager) getContext().getSystemService(Context.CLIPBOARD_SERVICE);
                String text = clip.getText().toString().trim();
                if (!TextUtils.isEmpty(text)) {
                    if (!text.contains("@")) {
                        if (text.startsWith("+86 ")) {
                            text = text.substring(4);
                        }
                        if (text.startsWith("+86")) {
                            text = text.substring(3);
                        }
                        if (text.contains("-")) {
                            text = text.replace("-", "");
                        }
                        text = text.replaceAll(" ", "");//  // 当联系人的号码是在通讯录中粘贴过来时会出现空格，正则会匹配不上
                        setText(text);
                        try {
                            setSelection(length());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        return true;
                    }
                }
            }
        }
        return super.onTextContextMenuItem(id);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (isFocused() && rightDrawable != null && event.getX() > (getWidth() - getPaddingRight() - rightDrawable.getIntrinsicWidth())
                    && (event.getX() < ((getWidth() - getPaddingRight())))) {
                if (pwdEyeEnable) {
                    if (getTransformationMethod() instanceof PasswordTransformationMethod) {
                        setTransformationMethod(null);
                        rightDrawable = getResources().getDrawable(R.drawable.login_invisible);
                    } else {
                        setTransformationMethod(PasswordTransformationMethod.getInstance());
                        rightDrawable = getResources().getDrawable(R.drawable.login_visible);
                    }
                } else {
                    this.setText("");
                    if (onInputListener != null) {
                        onInputListener.onClearText();
                    }
                }
                setDrawable();
            }
        }
        return super.onTouchEvent(event);
    }

    public void setOnInputListener(OnInputListener l) {
        onInputListener = l;
    }

    public void setType(int type) {
        this.type = type;
        switch (type) {
            case TYPE_PHONE:
                setInputType(InputType.TYPE_CLASS_PHONE);
                break;
            case TYPE_EMAIL:
//                setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                setInputType(InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
                break;
            case TYPE_PHONE_EMAIL:
            case TYPE_NORMAL:
                setInputType(InputType.TYPE_CLASS_TEXT);
                break;
        }
    }

    @Override
    protected void onCreateContextMenu(ContextMenu menu) {
        super.onCreateContextMenu(menu);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        return onTextContextMenuItem(item.getItemId());
    }

    public interface OnInputListener {
        void onRightful();

        void onUnlawful();

        void onClearText();
    }


}
