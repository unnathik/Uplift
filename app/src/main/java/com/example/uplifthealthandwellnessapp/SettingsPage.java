package com.example.uplifthealthandwellnessapp;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.example.uplifthealthandwellnessapp.Database.YogaDB;

import java.util.Calendar;
import java.util.Date;

public class SettingsPage extends AppCompatActivity {

    Button btnSave;
    RadioButton rdiEasy, rdiMedium, rdiHard;
    RadioGroup rdiGroup;
    YogaDB yogaDB;
    ToggleButton switchAlarm;
    TimePicker timePicker;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_page);

        btnSave = (Button) findViewById(R.id.btnSave);

        rdiGroup = (RadioGroup) findViewById(R.id.rdiGroup);
        rdiEasy = (RadioButton) findViewById(R.id.rdiEasy);
        rdiMedium = (RadioButton) findViewById(R.id.rdiMedium);
        rdiHard = (RadioButton) findViewById(R.id.rdiHard);

        switchAlarm = (ToggleButton) findViewById(R.id.switchAlarm);

        timePicker = (TimePicker) findViewById(R.id.timePicker);

        yogaDB = new YogaDB(this);

        int mode = yogaDB.getSettingMode();
        setRadioButton(mode);

        btnSave.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.M)
            @Override
            public void onClick(View v) {
                saveWorkoutMode();
                saveAlarm(switchAlarm.isChecked());
                Toast.makeText(SettingsPage.this, "Saved", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void saveAlarm(boolean checked) {
        if (checked)
        {
            int Hour, Minute;

            Intent intent;
            PendingIntent pendingIntent ;

            intent = new Intent(SettingsPage.this,AlarmNotificationReceiver.class);
            pendingIntent = PendingIntent.getBroadcast(this,0,intent,0);

            AlarmManager manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);

            if (Build.VERSION.SDK_INT >= 23 ) {

                Hour =  timePicker.getHour();
                Minute = timePicker.getMinute();

            }  else{

                Minute = timePicker.getCurrentMinute();
                Hour = timePicker.getCurrentHour();
            }


            Date dat = new Date();
            Calendar cal_alarm = Calendar.getInstance();
            Calendar cal_now = Calendar.getInstance();
            cal_now.setTime(dat);
            cal_alarm.setTime(dat);
            cal_alarm.set(Calendar.HOUR_OF_DAY,Hour);
            cal_alarm.set(Calendar.MINUTE,Minute);
            cal_alarm.set(Calendar.SECOND,10);
            if(cal_alarm.before(cal_now)){
                cal_alarm.add(Calendar.DATE,1);
            }
            manager.set(AlarmManager.RTC_WAKEUP,cal_alarm.getTimeInMillis(), pendingIntent);

            Log.d("DEBUG", "Alarm will ring at: "+timePicker.getHour()+":"+timePicker.getMinute());
        }

        else
        {
            Intent intent = new Intent(SettingsPage.this, AlarmNotificationReceiver.class);
            PendingIntent pendingIntent = PendingIntent.getBroadcast(this, 0, intent, 0);
            AlarmManager manager = (AlarmManager) getSystemService(Context.ALARM_SERVICE);
            manager.cancel(pendingIntent);
        }
    }

    private void saveWorkoutMode() {
        int selectedID = rdiGroup.getCheckedRadioButtonId();
        if (selectedID == rdiEasy.getId())
            yogaDB.saveSettingMode(0);
        else if (selectedID == rdiMedium.getId())
            yogaDB.saveSettingMode(1);
        else if (selectedID == rdiHard.getId())
            yogaDB.saveSettingMode(2);
    }

    private void setRadioButton(int mode) {
        if (mode == 0)
            rdiGroup.check(R.id.rdiEasy);
        else if (mode == 1)
            rdiGroup.check(R.id.rdiMedium);
        else if (mode == 2)
            rdiGroup.check(R.id.rdiHard);
    }
}