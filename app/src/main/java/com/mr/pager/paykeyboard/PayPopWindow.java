package com.mr.pager.paykeyboard;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.mr.pager.paykeyboard.divider.SpacesItemDecoration;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 支付密码输入
 */
public class PayPopWindow extends Dialog {
    //一共几位密码
    public final int PASSWORD_SIZE = 6;
    Context mContext;
    RecyclerView recyclerEdit;
    List<EditAdapter.EditBean> editList = new ArrayList<>();
    List<String> saveEditList = new ArrayList<>();
    EditAdapter editAdapter;
    RecyclerView recyclerKey;
    KeyAdapter keyAdapter;
    List<String> keyList = new ArrayList<>();
    OnPasswordEditListener onPasswordEditListener;

    public void setOnPasswordEditListener(OnPasswordEditListener onPasswordEditListener) {
        this.onPasswordEditListener = onPasswordEditListener;
    }


    public PayPopWindow(Context context) {
        this(context, R.style.Dialog);
        mContext = context;
    }


    public PayPopWindow( Context context, int themeResId) {
        super(context, themeResId);
        mContext = context;
    }

    public PayPopWindow( Context context, boolean cancelable,  DialogInterface.OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
        mContext = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_pay);
        recyclerEdit = findViewById(R.id.recycler_edit);
        recyclerKey = findViewById(R.id.recycler_key);
        initView();

    }

    public static int dip2px(Context context, float dipValue) {
        float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dipValue * scale + 0.5F);
    }

    private void initView() {
        initWindow();
        LinearLayoutManager editLayoutMananger = new LinearLayoutManager(mContext);
        editLayoutMananger.setOrientation(LinearLayoutManager.HORIZONTAL);
        recyclerEdit.setLayoutManager(editLayoutMananger);
        recyclerEdit.addItemDecoration(new SpacesItemDecoration(dip2px(mContext, 4), 0));
        editList.clear();
        for (int i = 0; i < PASSWORD_SIZE; i++) {
            editList.add(new EditAdapter.EditBean());
        }

        editAdapter = new EditAdapter(editList);
        recyclerEdit.setAdapter(editAdapter);
        editAdapter.setNewData(editList);


        String[] keyArray = new String[]{"1", "2", "3", "4", "5", "6", "7", "8", "9", "", "0", "返回"};
        keyList = Arrays.asList(keyArray);
        GridLayoutManager keyLayoutMananger = new GridLayoutManager(mContext, 3);
        recyclerKey.setLayoutManager(keyLayoutMananger);
        keyAdapter = new KeyAdapter(keyList);
        recyclerKey.setAdapter(keyAdapter);
        /**
         * 键盘点击事件
         */

        keyAdapter.setOnKeyClickListener(new KeyAdapter.OnKeyClickListener() {
            @Override
            public void onKeyClick(String key) {

                if (key.equals("返回")) {
                    if (saveEditList.size() > 0) {
                        editList.get(saveEditList.size() - 1).setTitle("");
                        saveEditList.remove(saveEditList.size() - 1);
                        editAdapter.notifyDataSetChanged();
                    }
                    return;
                }

                if (saveEditList.size() < PASSWORD_SIZE) {
                    editList.get(saveEditList.size()).setTitle(key);
                    saveEditList.add(key);
                    editAdapter.notifyDataSetChanged();

                    if (saveEditList.size() == PASSWORD_SIZE) {
                        //输入满了
                        String password = "";
                        for (String pw : saveEditList) {
                            password += pw;
                        }
                        if (onPasswordEditListener != null) {
                            onPasswordEditListener.onEditFinish(password);
                        }
                        dismiss();
                    }

                }
            }
        });

    }


    private void initWindow() {
        Window window = this.getWindow();
        if (window != null) {
            window.setWindowAnimations(R.style.ActionSheetDialogAnimation);
            window.setGravity(Gravity.BOTTOM);
            WindowManager.LayoutParams lp = window.getAttributes();
            lp.width = WindowManager.LayoutParams.MATCH_PARENT;
            lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
            window.setAttributes(lp);
        }
    }



    public interface OnPasswordEditListener {
        void onEditFinish(String password);
    }

}
