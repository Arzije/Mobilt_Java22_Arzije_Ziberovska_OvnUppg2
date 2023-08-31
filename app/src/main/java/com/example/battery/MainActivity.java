package com.example.battery;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView batteryLevelTextView;
   // private ImageView batteryImageView;
    private TextView chargingStatusTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       // ImageView batteryImageView = findViewById(R.id.imgBattery);
        batteryLevelTextView = findViewById(R.id.batteryLevelTextView);
        chargingStatusTextView = findViewById(R.id.chargingStatusTextView);

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = this.registerReceiver(null, ifilter);

        int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
        boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                status == BatteryManager.BATTERY_STATUS_FULL;

        int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
        int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);

        float batteryPct = level * 100 / (float)scale;

        String batteryLevelText = getString(R.string.battery_level, batteryPct);
        batteryLevelTextView.setText(batteryLevelText);

        if (isCharging) {
            chargingStatusTextView.setText(R.string.charging);
        } else {
            chargingStatusTextView.setText(R.string.not_charging);
        }
    }
}
