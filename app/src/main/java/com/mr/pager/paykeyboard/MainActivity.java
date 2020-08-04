package com.mr.pager.paykeyboard;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PayPopWindow payPopWindow = new PayPopWindow(MainActivity.this);
                payPopWindow.setOnPasswordEditListener(new PayPopWindow.OnPasswordEditListener() {
                    @Override
                    public void onEditFinish(String password) {
                        //在此校验输入的密码
                        Toast.makeText(MainActivity.this, "输入完成", Toast.LENGTH_SHORT).show();

                    }
                });
                payPopWindow.show();
            }
        });
    }


}
