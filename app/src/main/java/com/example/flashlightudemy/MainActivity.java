package com.example.flashlightudemy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.pm.PackageManager;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button flashlightBtn;
    boolean flag = true;
    CameraManager cameraManager;
    String cameraId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boolean isFlashAvailable = getApplicationContext().getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_FLASH);

        flashlightBtn = findViewById(R.id.flashBtn);


        cameraManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
        try {
            cameraId = cameraManager.getCameraIdList()[0];
        } catch (CameraAccessException e) {
            e.printStackTrace();
        }


        flashlightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (isFlashAvailable) {
                    if (flag) {
                        flag = false;
                        flashlightBtn.setText("TURN ON FLASHLIGHT");
                    } else {
                        flag = true;
                        flashlightBtn.setText("TURN Off FLASHLIGHT");
                    }
                    switchFlash(flag);
                } else {
                    Toast.makeText(MainActivity.this, "No Flash Available!", Toast.LENGTH_SHORT);
                }

            }
        });
    }

    public void switchFlash(boolean status) {
        try {
            cameraManager.setTorchMode(cameraId, status);
        } catch (Exception e) {

        }
    }
}