package com.xolo.scanzxing;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.xolo.scanzxing.zxing.CaptureActivity;
import com.xolo.scanzxing.utils.QRCodeUtil;

public class MainActivity extends AppCompatActivity {

    private Button btn_sweepCode;
    private TextView tv_codeReturn;
    private Button btn_generateCode;
    private ImageView iv_code;
    private Button btn_generateCodeImage;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();

        //点击扫码
        btn_sweepCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, CaptureActivity.class);
                startActivityForResult(intent,1001);
            }
        });

        //点击生成普通二维码
        btn_generateCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String generateCode = tv_codeReturn.getText().toString();
                QRCodeUtil.createQRcodeImage(generateCode, iv_code);
            }
        });

        //点击生成中间带图片的二维码
        btn_generateCodeImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String generateCode = tv_codeReturn.getText().toString();
                QRCodeUtil.createQRcodeImage(MainActivity.this,generateCode, iv_code);
            }
        });
    }

    public void init(){
        btn_sweepCode = findViewById(R.id.btn_sweepCode);
        tv_codeReturn = findViewById(R.id.tv_codeReturn);
        btn_generateCode = findViewById(R.id.btn_generateCode);
        iv_code = findViewById(R.id.iv_code);
        btn_generateCodeImage = findViewById(R.id.btn_generateCodeImage);
    }


    //扫码回掉
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1001 && resultCode== Activity.RESULT_OK) {
            String result = data.getStringExtra(CaptureActivity.KEY_DATA);
            tv_codeReturn.setText(result);//显示在输入框中
            Toast.makeText(this, result, Toast.LENGTH_SHORT).show();
        }
    }
}