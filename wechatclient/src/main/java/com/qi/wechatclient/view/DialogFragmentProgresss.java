package com.qi.wechatclient.view;

import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.qi.wechatclient.R;


/**
 * Created by feng on 2017/1/11.
 */
public class DialogFragmentProgresss extends DialogFragment {
    private TextView  contentTextView;
    private String  content;
    private DialogInterface.OnCancelListener onCancelListener;
    private ProgressBar mProgressBar;

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View view = View.inflate(getActivity(), R.layout.dialog_fragment_progress, null);
        Dialog dialog = new Dialog(getActivity(), R.style.DialogFragmentProgress);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(false);
        Window window = dialog.getWindow();
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        window.setLayout(displayMetrics.widthPixels * 2 / 5, WindowManager.LayoutParams.WRAP_CONTENT);
        findViews(view);
        return dialog;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        init();
    }

    private void findViews(View view) {
        mProgressBar = (ProgressBar) view.findViewById(R.id.progress);
        contentTextView = (TextView) view.findViewById(R.id.content);
    }

    private void init() {
        if (!TextUtils.isEmpty(content)) {
            contentTextView.setText(content);
            contentTextView.setVisibility(View.VISIBLE);
        }
    }


    @Override
    public void onCancel(DialogInterface dialog) {
        super.onCancel(dialog);
        if (onCancelListener != null) {
            onCancelListener.onCancel(dialog);
        }
    }

    public void setOnCancelListener(DialogInterface.OnCancelListener onCancelListener) {
        this.onCancelListener = onCancelListener;
    }


    public void setContent(String content) {
        this.content = content;
        if (contentTextView != null) {
            contentTextView.setText(content);
            contentTextView.setVisibility(View.VISIBLE);
        }
    }


}
